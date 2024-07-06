package kr.disdong.orm.examples.exposed.domain.post.model.impl

import kr.disdong.orm.examples.domain.post.model.Post
import java.time.LocalDateTime

data class PostImpl(override val id: Long, override var authorId: Long, override val title: String, override val content: String, override val createdAt: LocalDateTime, override val updatedAt: LocalDateTime) : Post
