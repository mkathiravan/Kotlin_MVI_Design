package net.kathir.kotlinmvi.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserNetworkEntity(
      @SerializedName("id")
      @Expose
      var id: Int,

      @SerializedName("login")
      @Expose
      var login: String,


      @SerializedName("avatar_url")
      @Expose
      var avatar_url: String,

      @SerializedName("type")
      @Expose
      var type: String,

      @SerializedName("site_admin")
      @Expose
      var site_admin: Boolean
)