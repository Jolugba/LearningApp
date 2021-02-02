package com.tinuade.learningapp.di

import com.google.gson.GsonBuilder
import com.tinuade.learningapp.BuildConfig
import com.tinuade.learningapp.data.remote.LearningRemoteDataSource
import com.tinuade.learningapp.data.remote.LearningServices
import com.tinuade.learningapp.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(learningService: LearningServices) =
        LearningRemoteDataSource(learningService)


    @Provides
    @Singleton
    fun provideLearningService(retrofit: Retrofit): LearningServices =
        retrofit.create(LearningServices::class.java)

    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO


}