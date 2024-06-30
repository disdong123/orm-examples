package kr.disdong.orm.examples.domain.post.repository

import kr.disdong.orm.examples.domain.post.model.PlainPost
import kr.disdong.orm.examples.domain.post.model.Post

interface PostRepository {
    fun findAllByUserId(userId: Long): List<Post>
    fun save(post: PlainPost): Post
}
