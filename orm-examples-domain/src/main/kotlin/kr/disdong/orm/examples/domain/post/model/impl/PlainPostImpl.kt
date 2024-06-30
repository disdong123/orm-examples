package kr.disdong.orm.examples.domain.post.model.impl

import kr.disdong.orm.examples.domain.post.model.PlainPost
import java.time.LocalDateTime

class PlainPostImpl(
    override val id: Long = 0,
    override val authorId: Long,
    override val title: String,
    override val content: String,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
) : PlainPost
