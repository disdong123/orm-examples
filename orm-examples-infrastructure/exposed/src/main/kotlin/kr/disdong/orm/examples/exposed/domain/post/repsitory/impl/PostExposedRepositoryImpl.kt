package kr.disdong.orm.examples.exposed.domain.post.repsitory.impl

import kotlinx.datetime.toKotlinLocalDateTime
import kr.disdong.orm.examples.domain.post.model.PlainPost
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.exposed.domain.post.model.PostDao
import kr.disdong.orm.examples.exposed.domain.post.model.PostEntity
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class PostExposedRepositoryImpl : PostRepository {
    override fun findAllByUserId(userId: Long): List<Post> {
        return transaction {
            PostDao.select(PostDao.id, PostDao.authorId, PostDao.title, PostDao.content, PostDao.createdAt, PostDao.updatedAt)
                .where {
                    PostDao.authorId eq userId
                }
                .map {
                    PostEntity.wrapRow(it)
                }
        }.map {
            it.toPost()
        }
    }

    override fun save(post: PlainPost): Post {
        return transaction {
            val id = PostDao.insertAndGetId {
                it[authorId] = post.authorId
                it[title] = post.title
                it[content] = post.content
                it[createdAt] = post.createdAt.toKotlinLocalDateTime()
                it[updatedAt] = post.updatedAt.toKotlinLocalDateTime()
            }

            findByPostId(id.value)!!
        }
    }

    private fun findByPostId(id: Long): Post? {
        return transaction {
            PostDao.select(PostDao.id, PostDao.authorId, PostDao.title, PostDao.content, PostDao.createdAt, PostDao.updatedAt)
                .where {
                    PostDao.id eq id
                }
                .firstOrNull()?.let {
                    PostEntity.wrapRow(it)
                }?.toPost()
        }
    }
}
