package kr.disdong.orm.examples.infrastructure.jdbc.domain.user.model

import kr.disdong.orm.examples.domain.user.model.PlainUser
import kr.disdong.orm.examples.infrastructure.jdbc.domain.user.model.impl.UserImpl
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("user")
class UserEntity(
    @Id
    val id: Long,
    @Column
    val name: String,
    @Column
    val phone: String,
) {

    companion object {
        fun of(user: PlainUser): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                phone = user.phone,
            )
        }
    }

    fun toUser(): UserImpl {
        return UserImpl(
            id = this.id,
            name = this.name,
            phone = this.phone,
        )
    }
}
