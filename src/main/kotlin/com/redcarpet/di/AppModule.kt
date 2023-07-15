package com.redcarpet.di

import com.redcarpet.data.repository.client_repository.ClientRepository
import com.redcarpet.data.repository.client_repository.ClientRepositoryImpl
import com.redcarpet.data.repository.collection_repository.CollectionRepository
import com.redcarpet.data.repository.collection_repository.CollectionRepositoryImpl
import com.redcarpet.data.repository.course_repository.CourseRepository
import com.redcarpet.data.repository.course_repository.CourseRepositoryImpl
import com.redcarpet.data.repository.custom_order_repository.CustomOrderRepository
import com.redcarpet.data.repository.custom_order_repository.CustomOrderRepositoryImpl
import com.redcarpet.data.repository.design_repository.DesignRepository
import com.redcarpet.data.repository.design_repository.DesignRepositoryImpl
import com.redcarpet.data.repository.manager_repository.ManagerRepository
import com.redcarpet.data.repository.manager_repository.ManagerRepositoryImpl
import com.redcarpet.data.repository.order_repository.OrderRepository
import com.redcarpet.data.repository.order_repository.OrderRepositoryImpl
import com.redcarpet.data.repository.pattern_repository.PatternRepository
import com.redcarpet.data.repository.pattern_repository.PatternRepositoryImpl
import com.redcarpet.data.repository.payment_repository.PaymentRepository
import com.redcarpet.data.repository.payment_repository.PaymentRepositoryImpl
import com.redcarpet.data.repository.registration_repository.RegistrationRepository
import com.redcarpet.data.repository.registration_repository.RegistrationRepositoryImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val appModule = module {

    single {
        KMongo.createClient("mongodb+srv://redcarpet:0ZotgPZHOhooNjtn@maincluster.xeaea9k.mongodb.net/test")
            .coroutine
            .getDatabase("redcarpet_db")
    }


    single<ClientRepository> {
        ClientRepositoryImpl(get())
    }

    single<CollectionRepository> {
        CollectionRepositoryImpl(get())
    }

    single<CourseRepository> {
        CourseRepositoryImpl(get())
    }

    single<CustomOrderRepository> {
        CustomOrderRepositoryImpl(get())
    }

    single<DesignRepository> {
        DesignRepositoryImpl(get())
    }

    single<OrderRepository> {
        OrderRepositoryImpl(get())
    }

    single<PatternRepository> {
        PatternRepositoryImpl(get())
    }

    single<PaymentRepository> {
        PaymentRepositoryImpl(get())
    }

    single<RegistrationRepository> {
        RegistrationRepositoryImpl(get())
    }

    single<ManagerRepository> {
        ManagerRepositoryImpl(get())
    }

}