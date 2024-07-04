package kr.disdong.orm.examples.exposed.domain.user.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UserDao : Table() {
    val id: Column<Long> = long("id").autoIncrement()
    val name = varchar("name", length = 50)
    val phone = varchar("phone", length = 50)
    override val primaryKey = PrimaryKey(id)
}

object A {
    fun a() {
        // thread local 에서 트랜잭션을 들고온다.
        val list = transaction {
            // Query 는 iterable 을 상속. toList() 가능
            UserDao.insert {
                it[name] = "111"
                it[phone] = "010"
            }

            UserDao.selectAll()
                .where {
                    UserDao.name eq "111"
                }
                .toList()
        }

        println("list $list")
    }
}
