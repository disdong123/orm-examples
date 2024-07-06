package kr.disdong.orm.examples.exposed.domain.user.model

import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.exposed.domain.user.model.impl.UserImpl
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object UserDao : LongIdTable("user", "id") {
    val name = varchar("name", length = 50)
    val phone = varchar("phone", length = 50)
}

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UserDao)
    var name by UserDao.name
    var phone by UserDao.phone

    fun toUser(): User {
        return UserImpl(id = this.id.value, name = this.name, phone = this.phone)
    }
}
