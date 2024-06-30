package kr.disdong.orm.examples.server.domain.user.service

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.jpa.domain.user.model.UserEntity
import kr.disdong.orm.examples.jpa.domain.user.model.impl.UserImpl
import kr.disdong.orm.examples.server.domain.user.dto.CreateUserBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UserServiceTest {

    private val sut = UserService(object : UserRepository {
        override fun findByUserId(userId: Long) = UserImpl(
            UserEntity(name = "name", phone = "010")
        )
        override fun save(user: PlainUser) = UserImpl(UserEntity(name = "name", phone = "010"))
    })

    @Test
    fun `샘플 테스트 1`() {
        val response = sut.getByUserId(1)
        assertNotNull(response)
    }

    @Test
    fun `샘플 테스트 2`() {
        // given, when
        val response = sut.create(CreateUserBody("name", "010"))

        // then
        assertEquals("name", response.name)
        assertEquals("010", response.phone)
    }
}
