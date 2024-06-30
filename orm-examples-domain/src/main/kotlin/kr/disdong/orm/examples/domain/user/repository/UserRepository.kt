package kr.disdong.orm.examples.domain.user.repository

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.model.User

interface UserRepository {

    fun findByUserId(userId: Long): User?

    fun save(user: PlainUser): User
}
