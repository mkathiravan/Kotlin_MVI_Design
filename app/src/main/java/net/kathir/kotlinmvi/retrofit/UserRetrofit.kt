package net.kathir.kotlinmvi.retrofit

import retrofit2.http.GET

interface UserRetrofit {

    @GET("users")
    suspend fun get() : List<UserNetworkEntity>
}