package kr.disdong.orm.examples.domain.user.model.impl

import kr.disdong.orm.examples.domain.user.model.PlainUser

class PlainUserImpl(
    override val id: Long = 0,
    override var name: String,
    override val phone: String
) : PlainUser
