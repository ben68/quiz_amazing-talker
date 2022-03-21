package com.amazingtalker.assessment.data.source.remote

import com.amazingtalker.assessment.data.bean.PeriodListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {

        const val BASE_URL: String = "https://tw.amazingtalker.com/v1/"

        fun newInstance(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("guest/teachers/{teacherName}/schedule")
    suspend fun getPeriodList(@Path("teacherName") teacherName: String, @Query("started_at") startAt: String): PeriodListResponse
}