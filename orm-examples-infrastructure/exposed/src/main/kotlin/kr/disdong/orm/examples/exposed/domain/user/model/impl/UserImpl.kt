package kr.disdong.orm.examples.exposed.domain.user.model.impl

import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.exposed.domain.user.model.UserDao
import org.jetbrains.exposed.sql.update

data class UserImpl(override val id: Long, override var name: String, override val phone: String) : User {
    override fun updateName(name: String) {
        UserDao.update {
            it[UserDao.name] = name
        }
    }
}
