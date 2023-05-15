package com.redcarpet.data.repository.course_category_reposiotry

import com.redcarpet.data.model.CourseCategory

interface CourseCategoryRepository {

    suspend fun getAllCategories(): List<CourseCategory>

    suspend fun getCategoryById(id: String): CourseCategory?

    suspend fun creatCategory(courseCategory: CourseCategory)

    suspend fun updateCategory(courseCategory: CourseCategory)

    suspend fun deleteCategoryById(id: String)

}