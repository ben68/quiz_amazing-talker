package com.amazingtalker.assessment

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.PeriodRepository
import com.amazingtalker.assessment.data.source.remote.ApiService
import com.amazingtalker.assessment.domain.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModules = module {
    factory {
        provideHttpClient()
    }
    single {
        provideRetrofit(get())
    }
    factory {
        ApiService.newInstance(get())
    }
    factory {
        PeriodRepository(get())
    }
    factory {
        CalendarRepository()
    }
}

val domainModules = module {
    factory {
        PickPeriodUseCase(get())
    }
    factory {
        ShowDaysByWeekUseCase(get())
    }
    factory {
        ShowNextWeekUseCase(get())
    }
    factory {
        ShowPeriodsByDayUseCase(get())
    }
    factory {
        ShowWeekUseCase(get())
    }
}

val moduleList = listOf(dataModules, domainModules)

fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .client(httpClient)
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

fun provideHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()