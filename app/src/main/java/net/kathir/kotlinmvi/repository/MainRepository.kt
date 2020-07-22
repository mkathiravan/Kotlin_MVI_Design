package net.kathir.kotlinmvi.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.retrofit.NetworkMapper
import net.kathir.kotlinmvi.retrofit.UserRetrofit
import net.kathir.kotlinmvi.room.CacheMapper
import net.kathir.kotlinmvi.room.UserDao
import net.kathir.kotlinmvi.util.DataState
import java.lang.Exception

class MainRepository
constructor(
       private val userDao: UserDao,
       private val userRetrofit: UserRetrofit,
       private val cacheMapper: CacheMapper,
       private val networkMapper: NetworkMapper
) {
    suspend fun getUsers() : Flow<DataState<List<Users>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkUsers = userRetrofit.get()
            val users = networkMapper.mapFromEntityList(networkUsers)
            for(user in users)
            {
                userDao.insert(cacheMapper.mapToEntity(user))
            }
            val cachedUsers = userDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedUsers)))

        }catch (e: Exception)
        {

        }
    }
}