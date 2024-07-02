package kr.disdong.orm.examples.jpa.domain.comment.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentClosureEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentClosurePathEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.CommentEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.QCommentClosureEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.QCommentClosurePathEntity
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.CommentViewQuery
import kr.disdong.orm.examples.jpa.domain.comment.model.impl.QCommentViewQuery
import kr.disdong.orm.examples.jpa.domain.user.model.QUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface CommentJpaRepository : JpaRepository<CommentEntity, Long>

interface CommentClosureJpaRepository : JpaRepository<CommentClosureEntity, Long>, CommentClosureCustomRepository

interface CommentClosurePathJpaRepository : JpaRepository<CommentClosurePathEntity, Long>

interface CommentClosureCustomRepository {
    fun findCommentViewsByPostId(postId: Long): List<CommentViewQuery>
    fun findCommentViewsByPostId(postId: Long, pageSize: Int, lastId: Long?): List<CommentViewQuery>
    fun findCommentViewRepliesById(commentId: Long, pageSize: Int, lastId: Long?): List<CommentViewQuery>
}

@Repository
class CommentClosureCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : CommentClosureCustomRepository {

    private val commentClosureEntity = QCommentClosureEntity.commentClosureEntity
    private val commentClosurePathEntity = QCommentClosurePathEntity.commentClosurePathEntity
    private val userEntity = QUserEntity.userEntity

    override fun findCommentViewsByPostId(postId: Long): List<CommentViewQuery> {
        return jpaQueryFactory.select(
            QCommentViewQuery(
                commentClosureEntity.id,
                commentClosurePathEntity.ancestor,
                commentClosureEntity.postId,
                commentClosureEntity.userId,
                userEntity.name,
                commentClosureEntity.content,
                commentClosureEntity.createdAt,
            )
        )
            .from(commentClosureEntity)
            .innerJoin(commentClosurePathEntity).on(commentClosureEntity.id.eq(commentClosurePathEntity.descendant))
            .innerJoin(userEntity).on(commentClosureEntity.userId.eq(userEntity.id))
            .where(commentClosureEntity.postId.eq(postId))
            .orderBy(commentClosureEntity.createdAt.asc())
            .fetch()
    }

    override fun findCommentViewsByPostId(postId: Long, pageSize: Int, lastId: Long?): List<CommentViewQuery> {
        return jpaQueryFactory.select(
            QCommentViewQuery(
                commentClosureEntity.id,
                commentClosurePathEntity.ancestor,
                commentClosureEntity.postId,
                commentClosureEntity.userId,
                userEntity.name,
                commentClosureEntity.content,
                commentClosureEntity.createdAt,
            )
        )
            .from(commentClosureEntity)
            .innerJoin(commentClosurePathEntity).on(commentClosureEntity.id.eq(commentClosurePathEntity.descendant))
            .innerJoin(userEntity).on(commentClosureEntity.userId.eq(userEntity.id))
            .where(
                commentClosureEntity.postId.eq(postId),
                commentClosurePathEntity.ancestor.eq(commentClosureEntity.id),
                lastIdCondition(lastId)
            )
            .limit(pageSize.toLong() + 1)
            .orderBy(commentClosureEntity.createdAt.asc())
            .fetch()
    }

    override fun findCommentViewRepliesById(commentId: Long, pageSize: Int, lastId: Long?): List<CommentViewQuery> {
        return jpaQueryFactory.select(
            QCommentViewQuery(
                commentClosureEntity.id,
                commentClosurePathEntity.ancestor,
                commentClosureEntity.postId,
                commentClosureEntity.userId,
                userEntity.name,
                commentClosureEntity.content,
                commentClosureEntity.createdAt,
            )
        )
            .from(commentClosureEntity)
            .innerJoin(commentClosurePathEntity).on(commentClosureEntity.id.eq(commentClosurePathEntity.descendant))
            .innerJoin(userEntity).on(commentClosureEntity.userId.eq(userEntity.id))
            .where(
                commentClosurePathEntity.ancestor.eq(commentId),
                lastIdCondition(lastId)
            )
            .limit(pageSize.toLong() + 1)
            .orderBy(commentClosureEntity.createdAt.asc())
            .fetch()
    }

    private fun lastIdCondition(lastId: Long?): BooleanExpression? {
        if (lastId == null) {
            return lastId
        }

        return commentClosureEntity.id.gt(lastId)
    }
}
