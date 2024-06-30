package kr.disdong.springboot.template.domain.user.model

interface PlainUser : UserData

interface User : UserData {
    fun updateName(name: String)
}

interface UserData {
    val id: Long
    var name: String
    val phone: String
}
