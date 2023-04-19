package com.redcarpet.routes

import com.redcarpet.data.model.Course
import com.redcarpet.data.repository.course_repository.CourseRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseRoute(courseRepository: CourseRepository) {
    route("/courses") {

        get {
            val courses = courseRepository.getAllCourses()
            call.respond(courses)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: ""
            val course = courseRepository.getCourseById(id) ?: return@get call.respondText(
                "Course with this id does not exist!",
                status = HttpStatusCode.NotFound
            )
            call.respond(course)
        }

        post {
            val course = call.receive<Course>()
            try {
                courseRepository.insertCourse(course)
                call.respondText(
                    "Course Added Successfully!",
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
            val course = call.receive<Course>()
            try {
                courseRepository.updateCourse(course)
                call.respondText(
                    text = "Course Updated Successfully!",
                    status = HttpStatusCode.OK
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
                courseRepository.deleteCourseById(id)
                call.respondText(
                    text = "Course Deleted Successfully!",
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