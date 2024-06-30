package kr.disdong.orm.examples.domain.post.model

import java.time.LocalDateTime

interface PlainPost : PostData
interface Post : PostData

interface PostData {
    val id: Long
    val authorId: Long
    val title: String
    val content: String
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
