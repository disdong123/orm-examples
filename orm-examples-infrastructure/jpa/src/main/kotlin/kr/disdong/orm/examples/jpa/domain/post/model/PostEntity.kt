package kr.disdong.orm.examples.jpa.domain.post.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import kr.disdong.orm.examples.domain.post.model.PlainPost
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.jpa.common.model.BaseEntity
import kr.disdong.orm.examples.jpa.domain.post.model.impl.PostImpl

@Entity(name = "post")
class PostEntity(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @Column
    val authorId: Long,

    @Column
    val title: String,
    @Column
    val content: String,
) : BaseEntity() {
    companion object {
        fun of(post: PlainPost): PostEntity {
            return PostEntity(
                authorId = post.authorId,
                title = post.title,
                content = post.content,
            )
        }
    }

    fun toPost(): Post {
        return PostImpl(
            entity = this
        )
    }
}
