package kr.disdong.orm.examples.domain.comment.repository

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.CommentView
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.domain.paging.PageResponse

interface CommentRepository {
    fun findAllByPostId(postId: Long): List<Comment>
    fun findCommentViewsByPostId(postId: Long): List<CommentView>
    fun findCommentViewsByPostId(postId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView>
    fun findCommentViewRepliesById(commentId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView>
    fun save(comment: PlainComment): Comment
    fun saveAll(comments: List<PlainComment>): List<Comment>
}
