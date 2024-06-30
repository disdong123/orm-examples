package kr.disdong.orm.examples.domain.comment.model.impl

import kr.disdong.orm.examples.domain.comment.model.PlainComment
import java.time.LocalDateTime

class PlainCommentImpl(
    override val id: Long = 0,
    override val postId: Long,
    override val userId: Long,
    override val parentId: Long?,
    override val content: String,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
) : PlainComment
