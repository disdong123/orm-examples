package kr.disdong.orm.examples.jpa.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.jpa.common.model.BaseEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentImpl

@Entity(name = "comment")
class CommentEntity(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Column
    val postId: Long,
    @Column
    val userId: Long,
    @Column
    val content: String,
) : BaseEntity() {
    companion object {
        fun of(comment: PlainComment): CommentEntity {
            return CommentEntity(
                postId = comment.postId,
                userId = comment.userId,
                content = comment.content,
            )
        }
    }

    fun toComment(): Comment {
        return CommentImpl(this)
    }
}
