package kr.disdong.orm.examples.jpa.domain.user.model.impl

import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.jpa.domain.user.model.UserEntity

class UserImpl(
    private val entity: UserEntity,
) : User {

    override val id: Long
        get() = entity.id
    override var name: String = entity.name
        get() = entity.name
    override val phone: String
        get() = entity.phone

    override fun updateName(name: String) {
        entity.name = name
    }
}
