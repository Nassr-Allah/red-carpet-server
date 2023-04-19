package com.redcarpet.data.repository.registration_repository

import com.redcarpet.data.model.Registration
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class RegistrationRepositoryImpl(private val database: CoroutineDatabase): RegistrationRepository {

    private val registrations = database.getCollection<Registration>()

    override suspend fun getAllRegistrations(): List<Registration> {
        return registrations.find().toList()
    }

    override suspend fun getClientRegistrations(clientId: String): List<Registration> {
        return registrations.find(Registration::clientId eq clientId).toList()
    }

    override suspend fun getRegistrationById(id: String): Registration? {
        return registrations.findOneById(id)
    }

    override suspend fun insertRegistration(registration: Registration) {
        registrations.insertOne(registration)
    }

    override suspend fun updateRegistration(registration: Registration) {
        registrations.updateOneById(registration.id, registration)
    }

    override suspend fun deleteRegistrationById(id: String) {
        registrations.deleteOneById(id)
    }
}