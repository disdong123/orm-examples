package kr.disdong.orm.examples.server.domain.user.service

import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.server.domain.user.dto.CreateUserBody
import kr.disdong.orm.examples.server.domain.user.exception.UserNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun getByUserId(userId: Long) =
        userRepository.findByUserId(userId)
            ?: throw UserNotFound(userId)

    @Transactional
    fun create(request: CreateUserBody): User {
        return userRepository.save(request.toUser())
    }
}
