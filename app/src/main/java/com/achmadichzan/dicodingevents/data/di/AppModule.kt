package com.achmadichzan.dicodingevents.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.achmadichzan.dicodingevents.BuildConfig
import com.achmadichzan.dicodingevents.data.local.dao.EventDao
import com.achmadichzan.dicodingevents.data.local.dao.FavoriteEventDao
import com.achmadichzan.dicodingevents.data.local.database.EventDatabase
import com.achmadichzan.dicodingevents.data.local.database.FavoriteEventDatabase
import com.achmadichzan.dicodingevents.data.network.EventApiService
import com.achmadichzan.dicodingevents.data.preferences.DarkThemePreferences
import com.achmadichzan.dicodingevents.data.preferences.dataStore
import com.achmadichzan.dicodingevents.data.repository.EventRepositoryImpl
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            defaultRequest {
                url(BuildConfig.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("KtorLogger").d(message)
                    }
                }
                level = LogLevel.ALL
            }
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
    fun provideEventRepository(
        apiService: EventApiService,
        eventDao: EventDao,
        favoriteEventDao: FavoriteEventDao,
        preferences: DarkThemePreferences
    ): EventRepository {
        return EventRepositoryImpl(apiService, eventDao, favoriteEventDao, preferences)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): EventDatabase {
        return Room.databaseBuilder(
            appContext,
            EventDatabase::class.java,
            "event_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideEventDao(appDatabase: EventDatabase): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteEventDatabase(@ApplicationContext appContext: Context): FavoriteEventDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteEventDatabase::class.java,
            "favorite_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideFavoriteEventDao(favDatabase: FavoriteEventDatabase): FavoriteEventDao {
        return favDatabase.favoriteEventDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideSettingPreferences(dataStore: DataStore<Preferences>): DarkThemePreferences {
        return DarkThemePreferences.getInstance(dataStore)
    }
}