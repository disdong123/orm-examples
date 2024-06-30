package kr.disdong.springboot.template.domain.user.model.impl

import kr.disdong.springboot.template.domain.user.model.PlainUser

class PlainUserImpl(
    override val id: Long = 0,
    override var name: String,
    override val phone: String
) : PlainUser
