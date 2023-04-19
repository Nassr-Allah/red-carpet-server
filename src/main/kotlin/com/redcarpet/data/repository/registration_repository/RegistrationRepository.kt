package com.redcarpet.data.repository.registration_repository

import com.redcarpet.data.model.Registration

interface RegistrationRepository {

    suspend fun getAllRegistrations(): List<Registration>

    suspend fun getClientRegistrations(clientId: String): List<Registration>

    suspend fun getRegistrationById(id: String): Registration?

    suspend fun insertRegistration(registration: Registration)

    suspend fun updateRegistration(registration: Registration)

    suspend fun deleteRegistrationById(id: String)

}