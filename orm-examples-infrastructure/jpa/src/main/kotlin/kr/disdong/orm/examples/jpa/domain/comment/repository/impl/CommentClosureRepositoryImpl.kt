package kr.disdong.orm.examples.jpa.domain.comment.repository.impl

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.CommentView
import kr.disdong.orm.examples.domain.comment.model.PlainComment
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.paging.PageResponse
import kr.disdong.orm.examples.domain.paging.toPageResponse
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentClosureEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentClosurePathEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentClosureImpl
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentViewQuery
import kr.disdong.orm.examples.jpa.domain.comment.repository.CommentClosureJpaRepository
import kr.disdong.orm.examples.jpa.domain.comment.repository.CommentClosurePathJpaRepository
import org.springframework.stereotype.Repository

@Repository
class CommentClosureRepositoryImpl(
    private val commentClosureJpaRepository: CommentClosureJpaRepository,
    private val commentClosurePathJpaRepository: CommentClosurePathJpaRepository,
) : CommentRepository {
    override fun findAllByPostId(postId: Long): List<Comment> {
        TODO()
    }

    override fun findCommentViewsByPostId(postId: Long): List<CommentView> {
        val views = commentClosureJpaRepository.findCommentViewsByPostId(postId)
        return CommentViewQuery.toCommentViews(views)
    }

    override fun findCommentViewsByPostId(postId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView> {
        return commentClosureJpaRepository.findCommentViewsByPostId(postId, pageSize, lastId)
            .map { it.toCommentView() }
            .toPageResponse(pageSize)
    }

    override fun findCommentViewRepliesById(commentId: Long, pageSize: Int, lastId: Long?): PageResponse<CommentView> {
        return commentClosureJpaRepository.findCommentViewRepliesById(commentId, pageSize, lastId)
            .map { it.toCommentView() }
            .toPageResponse(pageSize)
    }

    override fun save(comment: PlainComment): Comment {
        val closureEntity = commentClosureJpaRepository.save(CommentClosureEntity.of(comment))
        val closurePathEntity = commentClosurePathJpaRepository.save(CommentClosurePathEntity.of(closureEntity.id, comment))
        return CommentClosureImpl(closureEntity, closurePathEntity)
    }

    override fun saveAll(comments: List<PlainComment>): List<Comment> {
        val entities = commentClosureJpaRepository.saveAll(comments.map { CommentClosureEntity.of(it) })
        val pathEntities = commentClosurePathJpaRepository.saveAll(
            comments.mapIndexed { index, entity ->
                CommentClosurePathEntity.of(entities[index].id, entity)
            }
        )
        return entities.mapIndexed { index, commentClosureEntity ->
            CommentClosureImpl(commentClosureEntity, pathEntities[index])
        }
    }
}
