package com.redcarpet.data.repository.course_repository

import com.redcarpet.data.model.Course
import org.litote.kmongo.coroutine.CoroutineDatabase

class CourseRepositoryImpl(private val database: CoroutineDatabase): CourseRepository {

    private val courses = database.getCollection<Course>()

    override suspend fun getAllCourses(): List<Course> {
        return courses.find().toList()
    }

    override suspend fun getCourseById(id: String): Course? {
        return courses.findOneById(id)
    }

    override suspend fun insertCourse(course: Course) {
        courses.insertOne(course)
    }

    override suspend fun updateCourse(course: Course) {
        courses.updateOneById(course.id, course)
    }

    override suspend fun deleteCourseById(id: String) {
        courses.deleteOneById(id)
    }
}