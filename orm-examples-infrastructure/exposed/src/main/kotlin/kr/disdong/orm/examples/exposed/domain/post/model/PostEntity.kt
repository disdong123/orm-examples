package kr.disdong.orm.examples.exposed.domain.post.model

import kotlinx.datetime.toJavaLocalDateTime
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.exposed.domain.post.model.impl.PostImpl
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostDao : LongIdTable("post", "id") {
    val authorId = long("author_id")
    val title = varchar("title", length = 50)
    val content = text("content")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

class PostEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PostEntity>(PostDao)
    var authorId by PostDao.authorId
    var title by PostDao.title
    var content by PostDao.content
    var createdAt by PostDao.createdAt
    var updatedAt by PostDao.updatedAt

    fun toPost(): Post {
        return PostImpl(
            id = this.id.value, authorId = this.authorId, title = this.title, content = this.content,
            createdAt = this.createdAt.toJavaLocalDateTime(), updatedAt = this.updatedAt.toJavaLocalDateTime()
        )
    }
}
