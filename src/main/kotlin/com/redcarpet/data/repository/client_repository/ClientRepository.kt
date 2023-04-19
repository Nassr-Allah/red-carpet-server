package com.redcarpet.data.repository.client_repository

import com.redcarpet.data.model.Client

interface ClientRepository {

    suspend fun getClientByPhone(phoneNumber: String): Client?

    suspend fun getClientById(id: String): Client?

    suspend fun getAllClients(): List<Client>

    suspend fun insertClient(client: Client)

    suspend fun updateClient(client: Client)

    suspend fun deleteClientById(id: String)

}