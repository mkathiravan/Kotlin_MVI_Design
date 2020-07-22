package net.kathir.kotlinmvi.model

data class Users(var id: Int, var login: String, var avatar_url: String, var type: String, var site_admin: Boolean) {
}