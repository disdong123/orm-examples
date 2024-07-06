// package kr.disdong.orm.examples.jooq.domain.user.repository.impl
//
// import kr.disdong.orm.examples.domain.user.model.PlainUser
// import kr.disdong.orm.examples.domain.user.model.User
// import kr.disdong.orm.examples.domain.user.repository.UserRepository
// import kr.disdong.orm.examples.jooq.domain.user.model.UserImpl
// import kr.disdong.orm.examples.jooq.orm_examples.tables.User.Companion.USER
// import kr.disdong.orm.examples.jooq.orm_examples.tables.records.UserRecord
// import org.jooq.DSLContext
// import org.jooq.Record1
// import org.jooq.impl.DSL
// import org.springframework.stereotype.Repository
//
// fun Record1<UserRecord>.toUser(): User {
//     return UserImpl(
//         id = this.get(USER.ID)!!,
//         name = this.get(USER.NAME)!!,
//         phone = this.get(USER.PHONE)!!,
//     )
// }
//
// @Repository
// class UserJooqRepositoryImpl(
//     private val dsl: DSLContext
// ) : UserRepository {
//     override fun findByUserId(userId: Long): User? {
//         return dsl.select(USER).from(USER)
//             .where(USER.ID.eq(userId))
//             .fetchOne()?.toUser()
//     }
//
//     override fun save(user: PlainUser): User {
//         val userId = dsl.insertInto(DSL.table("user"))
//             .set(DSL.field("name"), user.name)
//             .set(DSL.field("phone"), user.phone)
//             .returningResult(DSL.field("id"))
//             .fetchOne()
//             ?.getValue(DSL.field("id"), Long::class.java)
//             ?: throw Exception("Failed to insert user")
//
//         return findByUserId(userId)!!
//     }
// }
