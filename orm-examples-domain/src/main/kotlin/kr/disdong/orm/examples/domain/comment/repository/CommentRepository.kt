package kr.disdong.orm.examples.domain.comment.repository

import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.PlainComment

interface CommentRepository {
    fun save(comment: PlainComment): Comment
}
