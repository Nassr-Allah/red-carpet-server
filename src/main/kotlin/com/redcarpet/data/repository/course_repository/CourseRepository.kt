package com.redcarpet.data.repository.course_repository

import com.redcarpet.data.model.Course
import com.redcarpet.data.model.CourseCategory

interface CourseRepository {

    suspend fun getAllCourses(): List<Course>

    suspend fun getCourseById(id: String): Course?

    suspend fun insertCourse(course: Course)

    suspend fun updateCourse(course: Course)

    suspend fun deleteCourseById(id: String)

}