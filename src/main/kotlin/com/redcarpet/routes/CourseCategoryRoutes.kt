package com.redcarpet.routes

import com.redcarpet.data.model.Course
import com.redcarpet.data.model.CourseCategory
import com.redcarpet.data.repository.course_category_reposiotry.CourseCategoryRepository
import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseCategoryRoute(courseCategoryRepository: CourseCategoryRepository) {
    route("/course-categories") {

        authenticate {
            get {
                val categories = courseCategoryRepository.getAllCategories()
                call.respond(categories)
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: ""
                val category = courseCategoryRepository.getCategoryById(id) ?: return@get call.respondText(
                    text = "Category does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(category)
            }

            post {
                val category = call.receive<CourseCategory>()
                try {
                    courseCategoryRepository.creatCategory(category)
                    call.respondText(
                        "Category Added Successfully!",
                        status = HttpStatusCode.Created
                    )
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            put {
                val category = call.receive<CourseCategory>()
                try {
                    courseCategoryRepository.updateCategory(category)
                    call.respondText(
                        "Category Updated Successfully!",
                        status = HttpStatusCode.Created
                    )
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: ""
                try {
                    courseCategoryRepository.deleteCategoryById(id)
                    call.respondText(
                        text = "Category Deleted Successfully!",
                        status = HttpStatusCode.OK
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