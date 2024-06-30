package kr.disdong.orm.examples.jpa.domain.user.repository.impl

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.jpa.domain.user.model.UserEntity
import kr.disdong.orm.examples.jpa.domain.user.repository.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun findByUserId(userId: Long): User? {
        return userJpaRepository.findByUserId(userId)?.toUser()
    }

    override fun save(user: PlainUser): User {
        return userJpaRepository.save(UserEntity.of(user)).toUser()
    }
}
