package com.redcarpet.data.repository.design_repository

import com.redcarpet.data.model.Design

interface DesignRepository {

    suspend fun getAllDesigns(): List<Design>

    suspend fun getDesignById(id: String): Design?

    suspend fun insertDesign(design: Design)

    suspend fun updateDesign(design: Design)

    suspend fun deleteDesignById(id: String)

}