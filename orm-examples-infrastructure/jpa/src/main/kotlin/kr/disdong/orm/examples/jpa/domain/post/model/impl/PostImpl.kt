package kr.disdong.orm.examples.jpa.domain.post.model.impl

import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.jpa.domain.post.model.PostEntity
import java.time.LocalDateTime

class PostImpl(
    private val entity: PostEntity
) : Post {
    override val id: Long
        get() = entity.id
    override val authorId: Long
        get() = entity.authorId
    override val title: String
        get() = entity.title
    override val content: String
        get() = entity.content
    override val createdAt: LocalDateTime
        get() = entity.createdAt
    override val updatedAt: LocalDateTime
        get() = entity.updatedAt
}
