package kr.disdong.orm.examples.infrastructure.jdbc.domain.user.model.impl

import kr.disdong.orm.examples.domain.user.model.User

class UserImpl(
    override val id: Long,
    override var name: String,
    override val phone: String,
) : User {
    override fun updateName(name: String) {
        TODO("Not yet implemented")
    }
}
