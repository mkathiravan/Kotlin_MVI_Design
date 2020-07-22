package net.kathir.kotlinmvi.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.retrofit.UserNetworkEntity
import net.kathir.kotlinmvi.retrofit.UserRetrofit
import net.kathir.kotlinmvi.util.EntityMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule
{
    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun providerRetrofit(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create(gson))

    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder) : UserRetrofit
    {
        return retrofit.build().create(UserRetrofit::class.java)
    }
}