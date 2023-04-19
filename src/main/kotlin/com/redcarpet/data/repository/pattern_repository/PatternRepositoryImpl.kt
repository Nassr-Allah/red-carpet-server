package com.redcarpet.data.repository.pattern_repository

import com.redcarpet.data.model.OriginalPattern
import org.litote.kmongo.coroutine.CoroutineDatabase

class PatternRepositoryImpl(private val database: CoroutineDatabase): PatternRepository {

    private val patterns = database.getCollection<OriginalPattern>()

    override suspend fun getAllPatterns(): List<OriginalPattern> {
        return patterns.find().toList()
    }

    override suspend fun getPatternById(id: String): OriginalPattern? {
        return patterns.findOneById(id)
    }

    override suspend fun insertPattern(pattern: OriginalPattern) {
        patterns.insertOne(pattern)
    }

    override suspend fun updatePattern(pattern: OriginalPattern) {
        patterns.updateOneById(pattern.id, pattern)
    }

    override suspend fun deletePatternById(id: String) {
        patterns.deleteOneById(id)
    }
}