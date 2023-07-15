package com.redcarpet.data.repository.manager_repository

import com.redcarpet.data.model.Manager
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ManagerRepositoryImpl(private val database: CoroutineDatabase): ManagerRepository {

    private val managers = database.getCollection<Manager>()

    override suspend fun getManagersList(): List<Manager> {
        return managers.find().toList()
    }

    override suspend fun getManagerById(id: String): Manager? {
        return managers.findOneById(id)
    }

    override suspend fun getManagerByUsername(username: String): Manager? {
        return managers.findOne(Manager::username eq username)
    }

    override suspend fun addManager(manager: Manager) {
        managers.insertOne(manager)
    }

    override suspend fun deleteManagerById(id: String) {
        managers.deleteOneById(id)
    }
}