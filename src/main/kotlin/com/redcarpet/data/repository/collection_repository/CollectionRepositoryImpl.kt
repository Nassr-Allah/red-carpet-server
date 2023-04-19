package com.redcarpet.data.repository.collection_repository

import com.redcarpet.data.model.ClientCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CollectionRepositoryImpl(private val database: CoroutineDatabase): CollectionRepository {

    private val collections = database.getCollection<ClientCollection>()

    override suspend fun getAllCollections(): List<ClientCollection> {
        return collections.find().toList()
    }

    override suspend fun getClientCollection(clientId: String): ClientCollection? {
        return collections.findOne(ClientCollection::clientId eq clientId)
    }

    override suspend fun getCollectionById(id: String): ClientCollection? {
        return collections.findOneById(id)
    }

    override suspend fun insertCollection(collection: ClientCollection) {
        collections.insertOne(collection)
    }

    override suspend fun updateCollection(collection: ClientCollection) {
        collections.updateOneById(collection.id, collection)
    }

    override suspend fun deleteCollectionById(id: String) {
        collections.deleteOneById(id)
    }
}