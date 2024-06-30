package kr.disdong.orm.examples.jpa.domain.comment.model.impl

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentEntity
import java.time.LocalDateTime

class CommentImpl(
    private val entity: CommentEntity
) : Comment {
    override val id: Long
        get() = entity.id
    override val postId: Long
        get() = entity.postId
    override val userId: Long
        get() = entity.userId
    override val parentId: Long?
        get() = entity.parentId
    override val content: String
        get() = entity.content
    override val createdAt: LocalDateTime
        get() = entity.createdAt
    override val updatedAt: LocalDateTime
        get() = entity.updatedAt
}
