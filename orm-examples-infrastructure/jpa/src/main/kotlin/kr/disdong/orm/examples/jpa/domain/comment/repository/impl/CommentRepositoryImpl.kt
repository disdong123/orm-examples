package kr.disdong.orm.examples.jpa.domain.comment.repository.impl

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.CommentView
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.paging.PageResponse
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentEntity.Companion.tree
import kr.disdong.orm.examples.jpa.domain.comment.repository.CommentJpaRepository
import org.springframework.stereotype.Repository

@Repository
class CommentRepositoryImpl(
    private val commentJpaRepository: CommentJpaRepository,
) : CommentRepository {
    override fun findAllByPostId(postId: Long): List<Comment> {
        return tree(commentJpaRepository.findAll())
    }

    override fun findCommentViewsByPostId(postId: Long): List<CommentView> {
        TODO("Not yet implemented")
    }

    override fun findCommentViewsByPostId(postId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView> {
        TODO("Not yet implemented")
    }

    override fun findCommentViewRepliesById(commentId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView> {
        TODO("Not yet implemented")
    }

    override fun save(comment: PlainComment): Comment {
        return commentJpaRepository.save(CommentEntity.of(comment)).toComment()
    }

    override fun saveAll(comments: List<PlainComment>): List<Comment> {
        return commentJpaRepository.saveAll(comments.map { CommentEntity.of(it) }).map { it.toComment() }
    }
}
