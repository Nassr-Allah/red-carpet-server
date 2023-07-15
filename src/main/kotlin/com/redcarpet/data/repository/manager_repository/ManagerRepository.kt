package com.redcarpet.data.repository.manager_repository

import com.redcarpet.data.model.Manager

interface ManagerRepository {

    suspend fun getManagersList(): List<Manager>

    suspend fun getManagerById(id: String): Manager?

    suspend fun getManagerByUsername(username: String): Manager?

    suspend fun addManager(manager: Manager)

    suspend fun deleteManagerById(id: String)

}