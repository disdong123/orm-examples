package kr.disdong.orm.examples.jpa.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.jpa.common.model.BaseEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentClosureImpl

@Entity(name = "comment_closure")
class CommentClosureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val postId: Long,
    @Column
    val userId: Long,
    @Column
    val content: String,
) : BaseEntity() {
    companion object {
        fun of(comment: PlainComment): CommentClosureEntity {
            return CommentClosureEntity(
                postId = comment.postId,
                userId = comment.userId,
                content = comment.content,
            )
        }
    }

    fun toComment(pathEntity: CommentClosurePathEntity): Comment {
        return CommentClosureImpl(
            entity = this,
            pathEntity = pathEntity
        )
    }
}

@Entity(name = "comment_closure_path")
class CommentClosurePathEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val ancestor: Long,
    @Column
    val descendant: Long,
) {
    companion object {
        fun of(descendant: Long, comment: PlainComment): CommentClosurePathEntity {
            return CommentClosurePathEntity(
                ancestor = comment.parentId ?: descendant,
                descendant = descendant,
            )
        }
    }
}
