package com.redcarpet.data.repository.design_repository

import com.redcarpet.data.model.Design
import org.litote.kmongo.coroutine.CoroutineDatabase

class DesignRepositoryImpl(private val database: CoroutineDatabase): DesignRepository {

    private val designs = database.getCollection<Design>()

    override suspend fun getAllDesigns(): List<Design> {
        return designs.find().toList()
    }

    override suspend fun getDesignById(id: String): Design? {
        return designs.findOneById(id)
    }

    override suspend fun insertDesign(design: Design) {
        designs.insertOne(design)
    }

    override suspend fun updateDesign(design: Design) {
        designs.updateOneById(design.id, design)
    }

    override suspend fun deleteDesignById(id: String) {
        designs.deleteOneById(id)
    }
}