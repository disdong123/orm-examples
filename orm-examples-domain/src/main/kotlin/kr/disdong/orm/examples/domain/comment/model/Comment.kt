package kr.disdong.orm.examples.domain.comment.model

import java.time.LocalDateTime

interface PlainComment : CommentData
interface Comment : CommentData

interface CommentData {
    val id: Long
    val postId: Long
    val userId: Long
    val parentId: Long?
    val content: String
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
