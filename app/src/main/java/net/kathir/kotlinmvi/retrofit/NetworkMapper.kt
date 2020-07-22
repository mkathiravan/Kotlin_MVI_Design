package net.kathir.kotlinmvi.retrofit

import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.util.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<UserNetworkEntity, Users>
{
    override fun mapFromEntity(entity: UserNetworkEntity): Users {
        return Users(id = entity.id, login = entity.login, avatar_url = entity.avatar_url, type = entity.type, site_admin = entity.site_admin)
    }

    override fun mapToEntity(domainModel: Users): UserNetworkEntity {
       return UserNetworkEntity(id = domainModel.id, login = domainModel.login, avatar_url = domainModel.avatar_url, type = domainModel.type, site_admin = domainModel.site_admin)
    }

    fun mapFromEntityList(entities: List<UserNetworkEntity>) : List<Users>
    {
        return entities.map { mapFromEntity(it) }
    }
}