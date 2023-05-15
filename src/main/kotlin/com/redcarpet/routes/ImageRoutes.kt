package com.redcarpet.routes

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
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
        route("/course") {

            post {
                val multipart = call.receiveMultipart()
                try {
                    val path = "build/resources/main/static/images/course/"
                    var imgUrl = ""
                    var name = ""
                    multipart.forEachPart { part ->
                        if (part is PartData.FileItem) {
                            name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                            part.save(path, name)
                            imgUrl = "https://red-carpet-server-production.up.railway.app/images/course/$name"
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
                    val path = "src/main/resources/static/images/design/"
                    var imgUrl = ""
                    var name = ""
                    multipart.forEachPart { part ->
                        if (part is PartData.FileItem) {
                            name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                            part.save(path, name)
                            imgUrl = "https://red-carpet-server-production.up.railway.app/images/design/$name"
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
                    val path = "src/main/resources/static/images/pattern/"
                    var imgUrl = ""
                    var name = ""
                    multipart.forEachPart { part ->
                        if (part is PartData.FileItem) {
                            name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                            part.save(path, name)
                            imgUrl = "https://red-carpet-server-production.up.railway.app/images/pattern/$name"
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
                    val path = "src/main/resources/static/images/attachment/"
                    var imgUrl = ""
                    var name = ""
                    multipart.forEachPart { part ->
                        if (part is PartData.FileItem) {
                            name = part.originalFileName ?: "${System.currentTimeMillis()}.jpg"
                            part.save(path, name)
                            imgUrl = "https://red-carpet-server-production.up.railway.app/images/attachment/$name"
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