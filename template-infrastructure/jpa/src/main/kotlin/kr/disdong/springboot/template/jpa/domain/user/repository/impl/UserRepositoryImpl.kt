package kr.disdong.springboot.template.jpa.domain.user.repository.impl

import kr.disdong.springboot.template.domain.user.model.PlainUser
import kr.disdong.springboot.template.domain.user.model.User
import kr.disdong.springboot.template.domain.user.repository.UserRepository
import kr.disdong.springboot.template.jpa.domain.user.model.UserEntity
import kr.disdong.springboot.template.jpa.domain.user.repository.UserJpaRepository
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
