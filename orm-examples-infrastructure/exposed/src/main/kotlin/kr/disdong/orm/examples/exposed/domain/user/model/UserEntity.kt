package kr.disdong.orm.examples.exposed.domain.user.model

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserEntity : Table() {
    val id: Column<Long> = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val phone = varchar("phone", length = 50)
    override val primaryKey = PrimaryKey(id)
}