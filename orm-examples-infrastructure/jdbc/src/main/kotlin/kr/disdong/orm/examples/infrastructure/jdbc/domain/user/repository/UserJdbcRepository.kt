package kr.disdong.orm.examples.infrastructure.jdbc.domain.user.repository

import kr.disdong.orm.examples.infrastructure.jdbc.domain.user.model.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserJdbcRepository : CrudRepository<UserEntity, Long>
