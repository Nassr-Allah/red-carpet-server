package com.redcarpet.routes

import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun PartData.FileItem.save(path: String, fileName: String): String {
    val fileBytes = streamProvider().readBytes()
    val folder = File(path)
    folder.mkdirs()
    File("$path$fileName").writeBytes(fileBytes)
    return folder.absolutePath
}

fun Route.imageRoutes() {
    route("/image") {

        authenticate {
            route("/course") {

                post {
                    val multipart = call.receiveMultipart()
                    try {
                        val path = "uploads/"
                        var imgUrl = ""
                        var name = ""
                        multipart.forEachPart { part ->
                            if (part is PartData.FileItem) {
                                name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                                part.save(path, name)
                                imgUrl = "http://89.116.236.180/uploads/$name"
                            }
                        }
                        call.respondText(
                            text = imgUrl,
                            status = HttpStatusCode.Created
                        )
                    } catch (e: Exception) {
                        call.respondText(
                            text = e.localizedMessage,
                            status = HttpStatusCode.InternalServerError
                        )
                    }
                }

            }

            route("/design") {
                post {
                    val multipart = call.receiveMultipart()
                    try {
                        val path = "uploads/"
                        var imgUrl = ""
                        var name = ""
                        multipart.forEachPart { part ->
                            if (part is PartData.FileItem) {
                                name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                                part.save(path, name)
                                imgUrl = "http://89.116.236.180/uploads/$name"
                            }
                        }
                        call.respondText(
                            text = imgUrl,
                            status = HttpStatusCode.Created
                        )
                    } catch (e: Exception) {
                        call.respondText(
                            text = e.localizedMessage,
                            status = HttpStatusCode.InternalServerError
                        )
                    }
                }
            }

            route("/pattern") {
                post {
                    val multipart = call.receiveMultipart()
                    try {
                        val path = "uploads/"
                        var imgUrl = ""
                        var name = ""
                        multipart.forEachPart { part ->
                            if (part is PartData.FileItem) {
                                name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                                part.save(path, name)
                                imgUrl = "http://89.116.236.180/uploads/$name"
                            }
                        }
                        call.respondText(
                            text = imgUrl,
                            status = HttpStatusCode.Created
                        )
                    } catch (e: Exception) {
                        call.respondText(
                            text = e.localizedMessage,
                            status = HttpStatusCode.InternalServerError
                        )
                    }
                }
            }

            route("/attachment") {
                post {
                    val multipart = call.receiveMultipart()
                    try {
                        val path = "uploads/"
                        var imgUrl = ""
                        var name = ""
                        multipart.forEachPart { part ->
                            if (part is PartData.FileItem) {
                                name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                                part.save(path, name)
                                imgUrl = "http://89.116.236.180/uploads/$name"
                            }
                        }
                        call.respondText(
                            text = imgUrl,
                            status = HttpStatusCode.Created
                        )
                    } catch (e: Exception) {
                        call.respondText(
                            text = e.localizedMessage,
                            status = HttpStatusCode.InternalServerError
                        )
                    }
                }
            }
        }
    }
}