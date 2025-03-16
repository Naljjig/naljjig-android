package com.naljjig.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OpenAiApiService {
    @Multipart
    @POST("/v1/images:analyze")
    fun analyzeImage(
        @Part file: MultipartBody.Part
    ): Call<ImageAnalysisResponse>
}

data class ImageAnalysisResponse(
    val subject: String,
    val date: String,
    val time: String
)

object ApiClient {
    private const val BASE_URL = "https://api.openai.com"

    val instance: OpenAiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAiApiService::class.java)
    }
}