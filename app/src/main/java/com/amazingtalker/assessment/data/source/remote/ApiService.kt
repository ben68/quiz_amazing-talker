package com.amazingtalker.assessment.data.source.remote

import com.amazingtalker.assessment.data.bean.PeriodListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {

    companion object {

        const val BASE_URL: String = "https://tw.amazingtalker.com/v1/"

        fun newInstance(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("guest/teachers/{teacherName}/schedule?started_at={startAt}")
    fun getPeriodList(teacherName: String, startAt: String): PeriodListResponse
}