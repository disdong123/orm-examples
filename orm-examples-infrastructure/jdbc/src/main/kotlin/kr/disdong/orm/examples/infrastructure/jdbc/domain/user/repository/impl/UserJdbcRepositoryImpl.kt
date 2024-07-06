package kr.disdong.orm.examples.infrastructure.jdbc.domain.user.repository.impl

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.infrastructure.jdbc.domain.user.model.UserEntity
import kr.disdong.orm.examples.infrastructure.jdbc.domain.user.repository.UserJdbcRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserJdbcRepositoryImpl(
    private val userJdbcRepository: UserJdbcRepository,
) : UserRepository {
    override fun findByUserId(userId: Long): User? {
        return userJdbcRepository.findById(userId).getOrNull()?.toUser()
    }

    override fun save(user: PlainUser): User {
        return userJdbcRepository.save(UserEntity.of(user)).toUser()
    }
}
