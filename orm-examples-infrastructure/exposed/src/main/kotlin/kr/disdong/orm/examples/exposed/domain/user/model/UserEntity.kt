package kr.disdong.orm.examples.exposed.domain.user.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UserDao : Table(name = "user") {
    val id: Column<Long> = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val phone = varchar("phone", length = 50)
    override val primaryKey = PrimaryKey(id)
}
object userRepository {
    fun find() {
        println("find")
        val list = transaction {
            UserDao.selectAll()
                .where {
                    UserDao.name eq "111"
                }
                .toList()
        }

        println("list $list")
    }

    fun delete() {
        println("delete")
        transaction {
            UserDao.deleteWhere { UserDao.id eq 176 }
            println("completed$")
        }
    }
}
