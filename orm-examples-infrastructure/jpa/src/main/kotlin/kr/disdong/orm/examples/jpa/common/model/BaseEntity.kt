package kr.disdong.orm.examples.jpa.common.model

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @Column
    val isDeleted: Boolean = false,

    @Column
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
