package com.achmadichzan.dicodingevents.data.di

import com.achmadichzan.dicodingevents.data.network.EventApiService
import com.achmadichzan.dicodingevents.data.repository.EventRepositoryImpl
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(Logging)
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    @Provides
    @Singleton
    fun provideEventApiService(client: HttpClient): EventApiService {
        return EventApiService(client)
    }

    @Provides
    @Singleton
    fun provideEventRepository(apiService: EventApiService): EventRepository {
        return EventRepositoryImpl(apiService)
    }
}