package com.redcarpet.data.repository.pattern_repository

import com.redcarpet.data.model.OriginalPattern

interface PatternRepository {

    suspend fun getAllPatterns(): List<OriginalPattern>

    suspend fun getPatternById(id: String): OriginalPattern?

    suspend fun insertPattern(pattern: OriginalPattern)

    suspend fun updatePattern(pattern: OriginalPattern)

    suspend fun deletePatternById(id: String)

}