package net.kathir.kotlinmvi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.kathir.kotlinmvi.room.UserDao
import net.kathir.kotlinmvi.room.UserDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providerUserDb(@ApplicationContext context: Context) : UserDatabase{
        return Room.databaseBuilder(context, UserDatabase::class.java, UserDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    fun provideUserDAO(userDatabase: UserDatabase) : UserDao {
        return userDatabase.userDao()
    }
}