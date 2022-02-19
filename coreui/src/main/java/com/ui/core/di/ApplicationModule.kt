package com.ui.core.di

import android.app.Application
import android.content.Context
import com.domain.core.di.annotations.qualifiers.AppApiKey
import com.domain.core.di.annotations.qualifiers.AppContext
import com.domain.core.di.annotations.qualifiers.AppPreferenceName
import com.domain.core.di.annotations.qualifiers.AppRemoteUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @AppRemoteUrl
    @Provides
    fun serviceURl(): String {
        val DOMAIN = "BuildConfig.DOMAIN"
//        return "$DOMAIN/api/v1/"
      return  "https://free.currconv.com/api/v7/"
//        return "https://open-api.xyz/api/"
    }


    @AppPreferenceName
    @Provides
    fun setPreferenceName(): String {
        return "PreferenceCurrency"
    }

    @AppApiKey
    @Provides
    fun setApiKey(): String {
        return "79b6aec4e79420c2cba1"
    }


    @AppContext
    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

}