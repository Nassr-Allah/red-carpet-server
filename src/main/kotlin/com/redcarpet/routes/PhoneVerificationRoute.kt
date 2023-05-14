package com.redcarpet.routes

import com.redcarpet.data.model.PhoneVerificationRequest
import com.redcarpet.data.model.PhoneVerificationResponse
import com.telesign.MessagingClient
import com.telesign.RestClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun Route.phoneVerificationRoutes(httpClient: HttpClient) {
    route("/verify-phone") {
        post("/{phoneNumber}") {
            //val request = call.receive<PhoneVerificationRequest>()
            val phoneNumber = "213778184314"

            val customerId = "1A849583-75A1-4120-8A33-8DD57720A546"
            val apiKey = "NLGpcmragvwj1MnSuxxdXkMkpLGfKF8qAxLJ1WSk/MJQn3pm8gE7W2VFeCiSA271j9xJcSovRrq9BcL7E3i8Wg=="
            val apiUrl = "https://rest-api.telesign.com/v1/verify/sms"

            val verificationCode = 123456
            val message = "Your verification code is: $verificationCode"
            val messageType = "ARN"

            try {
                val messagingClient = MessagingClient(customerId, apiKey)
                val response = messagingClient.message(phoneNumber, message, messageType, null)
                if (response.statusCode == 200) {
                    call.respondText(
                        text = "response: $response, code: $verificationCode",
                        status = HttpStatusCode.OK
                    )
                } else {
                    call.respondText(
                        text = "Error occured: ${response.json}",
                        status = HttpStatusCode.InternalServerError
                    )
                }
            } catch (e: Exception) {
                call.respondText(
                    text = e.localizedMessage,
                    status = HttpStatusCode.InternalServerError
                )
            }

            /*
            try {
                println("started call")
                val response = httpClient.post(apiUrl) {
                    header("Authorization", generateAuthorizationHeader(customerId, apiKey))
                    parameter("phoneNumber", phoneNumber)
                    parameter("verificationCodeLength", "6")
                }
                if (response.status.value == 200) {
                    println("success")
                    val phoneAuthResponse = response.body<PhoneVerificationResponse>()
                    call.respondText(
                        text = phoneAuthResponse.verificationCode ?: "empty",
                        status = HttpStatusCode.OK
                    )
                } else {
                    println("not success")
                    call.respondText(
                        text = response.status.description,
                        status = response.status
                    )
                }
            } catch (e: Exception) {
                println("exception")
                call.respondText(
                    text = e.localizedMessage,
                    status = HttpStatusCode.InternalServerError
                )
            }

             */
        }
    }
}


fun generateAuthorizationHeader(customerId: String, apiKey: String): String {
    val date = Date().toInstant().epochSecond.toString()
    val nonce = Random().nextInt().toString()

    val signature = Base64.getEncoder().encodeToString(
        Mac.getInstance("HmacSHA256")
            .apply { init(SecretKeySpec(apiKey.toByteArray(), "HmacSHA256")) }
            .doFinal("$nonce$date$apiKey".toByteArray())
    )

    return "TSA $customerId:$signature"
}