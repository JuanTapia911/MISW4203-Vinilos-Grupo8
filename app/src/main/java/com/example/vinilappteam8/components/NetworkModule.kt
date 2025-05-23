package com.example.vinilappteam8.components

import android.content.Context
import android.util.Log
import com.example.vinilappteam8.services.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton
import kotlin.apply
import kotlin.jvm.java

/**
 * Este modulo proporciona las dependencias necesarias para la red, incluyendo OkHttpClient y Retrofit.
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // URL base para las peticiones a la API
    // Se puede configurar a través de una variable de entorno o se usara el valor por defecto del
    // localhost (10.0.2.2 desde el emulador de Android)
    private var BASE_URL = System.getenv("BASE_URL")?: "http://13.218.64.48:3000/"

    /**
     * Proporciona una instancia de OkHttpClient.
     *
     * @param context Contexto de la aplicación.
     * @return Instancia de OkHttpClient.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {

        Log.d("NetworkModule", "provideOkHttpClient called")

        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
        val cacheDir = File(context.cacheDir, "http-cache")
        val cache = Cache(cacheDir, cacheSize)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().header("Cache-Control", "public, max-age=3600").build()
                chain.proceed(newRequest)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Proporciona una instancia de Retrofit.
     *
     * @param okHttpClient Instancia de OkHttpClient.
     * @return Instancia de Retrofit.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Proporciona una instancia de HttpService.
     *
     * @param retrofit Instancia de Retrofit.
     * @return Instancia de HttpService.
     */
    @Provides
    @Singleton
    fun provideHttpService(retrofit: Retrofit): HttpService {
        return retrofit.create(HttpService::class.java)
    }

}