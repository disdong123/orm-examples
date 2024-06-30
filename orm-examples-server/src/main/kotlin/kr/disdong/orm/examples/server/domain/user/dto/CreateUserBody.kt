package kr.disdong.orm.examples.server.domain.user.dto

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.model.impl.PlainUserImpl

class CreateUserBody(
    val name: String,
    val phone: String,
) {

    fun toUser(): PlainUser {
        return PlainUserImpl(
            name = name,
            phone = phone,
        )
    }
}
