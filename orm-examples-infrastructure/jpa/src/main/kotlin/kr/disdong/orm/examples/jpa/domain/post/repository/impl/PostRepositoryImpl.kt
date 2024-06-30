package kr.disdong.orm.examples.jpa.domain.post.repository.impl

import kr.disdong.orm.examples.domain.post.model.PlainPost
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.jpa.domain.post.model.PostEntity
import kr.disdong.orm.examples.jpa.domain.post.repository.PostJpaRepository
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl(
    private val postJpaRepository: PostJpaRepository,
) : PostRepository {
    override fun findAllByUserId(userId: Long): List<Post> {
        return postJpaRepository.findAll().map { it.toPost() }
    }

    override fun save(post: PlainPost): Post {
        return postJpaRepository.save(PostEntity.of(post)).toPost()
    }
}
