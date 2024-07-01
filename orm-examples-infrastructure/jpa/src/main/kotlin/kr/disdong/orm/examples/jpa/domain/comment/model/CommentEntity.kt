package kr.disdong.orm.examples.jpa.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.jpa.common.model.BaseEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentImpl

@Entity(name = "comment")
class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column
    val postId: Long,
    @Column
    val userId: Long,
    @Column
    val parentId: Long?,
    @Column
    val content: String,
) : BaseEntity() {
    companion object {
        fun of(comment: PlainComment): CommentEntity {
            return CommentEntity(
                postId = comment.postId,
                userId = comment.userId,
                parentId = comment.parentId,
                content = comment.content,
            )
        }

        /**
         *
         */
        fun tree(entities: List<CommentEntity>): List<Comment> {
            val commentMap = entities.associateBy { it.id }
                .mapValues { (_, entity) ->
                    CommentImpl(entity)
                }

            val rootComments = mutableListOf<Comment>()

            commentMap.values.forEach { comment ->
                if (comment.parentId == null) {
                    rootComments.add(comment)
                } else {
                    commentMap[comment.parentId]?.replies?.add(comment)
                }
            }

            return rootComments
        }
    }

    fun toComment(): Comment {
        return CommentImpl(this)
    }
}
