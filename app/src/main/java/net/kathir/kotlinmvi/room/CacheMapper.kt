package net.kathir.kotlinmvi.room

import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<UserCacheEntity, Users> {

    override fun mapFromEntity(entity: UserCacheEntity): Users {
        return Users(id = entity.id, login = entity.login, avatar_url = entity.avatar_url, type = entity.type, site_admin = entity.site_admin)
    }

    override fun mapToEntity(domainModel: Users): UserCacheEntity {
        return UserCacheEntity(id = domainModel.id, login = domainModel.login, avatar_url = domainModel.avatar_url, type = domainModel.type, site_admin = domainModel.site_admin)
    }


    fun mapFromEntityList(entities : List<UserCacheEntity>) : List<Users>
    {
        return entities.map { mapFromEntity(it) }
    }
}