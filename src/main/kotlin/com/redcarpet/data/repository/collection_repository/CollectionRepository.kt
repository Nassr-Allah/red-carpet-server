package com.redcarpet.data.repository.collection_repository

import com.redcarpet.data.model.ClientCollection

interface CollectionRepository {

    suspend fun getAllCollections(): List<ClientCollection>

    suspend fun getClientCollection(clientId: String): ClientCollection?

    suspend fun getCollectionById(id: String): ClientCollection?

    suspend fun insertCollection(collection: ClientCollection)

    suspend fun updateCollection(collection: ClientCollection)

    suspend fun deleteCollectionById(id: String)

}