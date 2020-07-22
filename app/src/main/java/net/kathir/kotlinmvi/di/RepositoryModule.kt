package net.kathir.kotlinmvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.kathir.kotlinmvi.repository.MainRepository
import net.kathir.kotlinmvi.retrofit.NetworkMapper
import net.kathir.kotlinmvi.retrofit.UserRetrofit
import net.kathir.kotlinmvi.room.CacheMapper
import net.kathir.kotlinmvi.room.UserDao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        userDao: UserDao,
        retrofit: UserRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper): MainRepository {
        return MainRepository(userDao,retrofit,cacheMapper,networkMapper)
    }
}