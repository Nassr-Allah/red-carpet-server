package com.redcarpet.data.repository.course_category_reposiotry

import com.redcarpet.data.model.CourseCategory
import org.litote.kmongo.coroutine.CoroutineDatabase

class CourseCategoryImpl(private val database: CoroutineDatabase): CourseCategoryRepository {

    private val categories = database.getCollection<CourseCategory>()

    override suspend fun getAllCategories(): List<CourseCategory> {
        return categories.find().toList()
    }

    override suspend fun getCategoryById(id: String): CourseCategory? {
        return categories.findOneById(id)
    }

    override suspend fun creatCategory(courseCategory: CourseCategory) {
        categories.insertOne(courseCategory)
    }

    override suspend fun updateCategory(courseCategory: CourseCategory) {
        categories.updateOneById(courseCategory.id, courseCategory)
    }

    override suspend fun deleteCategoryById(id: String) {
        categories.deleteOneById(id)
    }

}