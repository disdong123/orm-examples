package kr.disdong.springboot.template.domain.user.repository

import kr.disdong.springboot.template.domain.user.model.PlainUser
import kr.disdong.springboot.template.domain.user.model.User

interface UserRepository {

    fun findByUserId(userId: Long): User?

    fun save(user: PlainUser): User
}
