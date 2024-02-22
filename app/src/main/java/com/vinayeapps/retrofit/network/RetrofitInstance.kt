package com.vinayeapps.retrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by vinay on 2024-02-21.
 * Author: Vinay Sebastian
 */
object RetrofitInstance {
    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor).build()

    val api:Api = Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Api.BASE_URL)
        .client(client)
        .build()
        .create(Api::class.java)
}