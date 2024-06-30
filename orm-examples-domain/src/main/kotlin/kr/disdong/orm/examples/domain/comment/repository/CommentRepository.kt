package kr.disdong.orm.examples.domain.comment.repository

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.PlainComment

interface CommentRepository {
    fun findAllByPostId(postId: Long): List<Comment>
    fun save(comment: PlainComment): Comment
    fun saveAll(comments: List<PlainComment>): List<Comment>
}
