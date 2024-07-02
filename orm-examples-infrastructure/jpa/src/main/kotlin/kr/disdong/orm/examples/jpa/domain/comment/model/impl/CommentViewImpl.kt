package kr.disdong.orm.examples.jpa.domain.comment.model.impl

import com.querydsl.core.annotations.QueryProjection
import kr.disdong.orm.examples.domain.comment.model.CommentView
import java.time.LocalDateTime

class CommentViewImpl(
    override val id: Long,
    override val postId: Long,
    override val userId: Long,
    override val name: String,
    override val content: String,
    override val replies: MutableList<CommentView> = mutableListOf(),
    override val createdAt: LocalDateTime,
) : CommentView

data class CommentViewQuery @QueryProjection constructor(
    val id: Long,
    val ancestor: Long,
    val postId: Long,
    val userId: Long,
    val name: String,
    val content: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun toCommentViews(views: List<CommentViewQuery>): List<CommentView> {
            val commentMap = mutableMapOf<Long, CommentViewImpl>()

            for (view in views) {
                commentMap[view.id] = CommentViewImpl(
                    id = view.id,
                    postId = view.postId,
                    userId = view.userId,
                    name = view.name,
                    content = view.content,
                    createdAt = view.createdAt
                )
            }

            for (view in views) {
                if (view.ancestor != view.id) {
                    val parent = commentMap[view.ancestor]
                    val child = commentMap[view.id]
                    if (parent != null && child != null) {
                        parent.replies += child
                    }
                }
            }

            return commentMap.values.filter { it.replies.isNotEmpty() }
        }
    }

    fun toCommentView(): CommentViewImpl {
        return CommentViewImpl(
            id = this.id,
            postId = this.postId,
            userId = this.userId,
            name = this.name,
            content = this.content,
            createdAt = this.createdAt
        )
    }
}
