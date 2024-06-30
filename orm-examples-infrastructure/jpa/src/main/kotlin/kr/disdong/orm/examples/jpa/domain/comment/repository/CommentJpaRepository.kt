package kr.disdong.orm.examples.jpa.domain.comment.repository

import kr.disdong.orm.examples.jpa.domain.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<CommentEntity, Long>
