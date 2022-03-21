package com.amazingtalker.assessment

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.PeriodRepository
import com.amazingtalker.assessment.data.source.remote.ApiService
import com.amazingtalker.assessment.domain.*
import com.amazingtalker.assessment.ui.CalendarViewModel
import com.amazingtalker.assessment.ui.DayViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
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
    single {
        PeriodRepository(get())
    }
    single {
        CalendarRepository()
    }
}

val domainModules = module {
    factory {
        PickPeriodUseCase(get())
    }
    factory {
        ShowTimeZoneUseCase()
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

val viewModules = module {
    viewModel {
        CalendarViewModel(get(), get(), get(), get())
    }
    viewModel {
        DayViewModel(get())
    }
}

val moduleList = listOf(dataModules, domainModules, viewModules)

fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .client(httpClient)
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

fun provideHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()