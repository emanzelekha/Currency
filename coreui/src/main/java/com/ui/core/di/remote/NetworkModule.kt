package com.ui.core.di.remote

import com.domain.core.di.annotations.qualifiers.AppApiKey
import com.domain.core.di.annotations.qualifiers.AppRemoteUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ui.core.di.ApplicationModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module(includes = [OkHttpClientModule::class, ApplicationModule::class])
class NetworkModule {

    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @AppRemoteUrl baseUrl: String,
        @AppApiKey apiKey: String
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build())
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setLenient()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


}