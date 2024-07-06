package kr.disdong.orm.examples.exposed.domain.user.repository.impl

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.exposed.domain.user.model.UserDao
import kr.disdong.orm.examples.exposed.domain.user.model.UserEntity
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserExposedRepositoryImpl : UserRepository {
    override fun findByUserId(userId: Long): User? {
        return transaction {
            UserDao.select(UserDao.id, UserDao.name, UserDao.phone)
                .where {
                    UserDao.id eq userId
                }
                .firstOrNull()?.let {
                    UserEntity.wrapRow(it)
                }?.toUser()
        }
    }

    override fun save(user: PlainUser): User {
        return transaction {
            val id = UserDao.insertAndGetId {
                it[name] = user.name
                it[phone] = user.phone
            }

            findByUserId(id.value)!!
        }
    }
}
