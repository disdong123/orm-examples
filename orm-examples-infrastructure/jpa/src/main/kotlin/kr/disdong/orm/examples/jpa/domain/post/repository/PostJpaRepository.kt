package kr.disdong.orm.examples.jpa.domain.post.repository

import kr.disdong.orm.examples.jpa.domain.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostEntity, Long>
