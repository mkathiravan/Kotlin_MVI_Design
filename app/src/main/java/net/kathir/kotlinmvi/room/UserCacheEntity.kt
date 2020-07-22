package net.kathir.kotlinmvi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "login")
    var login : String,

    @ColumnInfo(name = "avatar_url")
    var avatar_url : String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "site_admin")
    var site_admin: Boolean
)