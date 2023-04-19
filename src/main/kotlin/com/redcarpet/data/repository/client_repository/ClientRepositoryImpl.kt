package com.redcarpet.data.repository.client_repository

import com.redcarpet.data.model.Client
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ClientRepositoryImpl(private val database: CoroutineDatabase): ClientRepository {

    val clients = database.getCollection<Client>()

    override suspend fun getClientByPhone(phoneNumber: String): Client? {
        return clients.findOne(Client::phoneNumber eq phoneNumber)
    }

    override suspend fun getClientById(id: String): Client? {
        return clients.findOneById(id)
    }

    override suspend fun getAllClients(): List<Client> {
        return clients.find().toList()
    }

    override suspend fun insertClient(client: Client) {
        clients.insertOne(client)
    }

    override suspend fun updateClient(client: Client) {
        clients.updateOneById(client.id, client)
    }

    override suspend fun deleteClientById(id: String) {
        clients.deleteOneById(id)
    }
}