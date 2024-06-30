package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.common.logger.logger
import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.impl.PlainCommentImpl
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.domain.post.model.impl.PlainPostImpl
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.model.impl.PlainUserImpl
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.jpa.JpaApplication
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@Import(DomainApplication::class, JpaApplication::class)
class ServerApplication(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) : ApplicationRunner {
    private val logger = logger<ServerApplication>()

    @Transactional
    override fun run(args: ApplicationArguments?) {
        logger.info("run...")

        seed()
    }

    private fun seed() {
        val u1 = createUser()
        val u2 = createUser()
        val u3 = createUser()
        val u4 = createUser()
        val u5 = createUser()
        val u6 = createUser()

        val p1 = createPost(u1.id)
        val p2 = createPost(u1.id)
        val p3 = createPost(u2.id)
        val p4 = createPost(u3.id)

        val c1 = createComments(u1.id, p1.id, content = "1")
        val c2 = createComments(u1.id, p1.id, c1.id, content = "1 - 1")
        val c3 = createComments(u1.id, p1.id, c2.id, content = "1 - 2")
        val c4 = createComments(u2.id, p1.id, content = "2")
        val c5 = createComments(u3.id, p1.id, content = "3")
        val c6 = createComments(u4.id, p1.id, content = "4")
        val c7 = createComments(u5.id, p1.id, c4.id, content = "2 - 1")
        val c8 = createComments(u6.id, p1.id, c3.id, content = "1 - 3")
        val c9 = createComments(u3.id, p1.id, c8.id, content = "1 - 4")
        val c10 = createComments(u2.id, p1.id, c4.id, content = "2 - 1")
    }

    private fun createUser(): User {
        return userRepository.save(PlainUserImpl(name = "111", phone = "0101111"))
    }

    private fun createPost(userId: Long): Post {
        return postRepository.save(
            PlainPostImpl(
                authorId = userId,
                title = "first",
                content = "first content",
            )
        )
    }

    private fun createComments(userId: Long, postId: Long, parentId: Long? = null, content: String): Comment {
        return commentRepository.save(
            PlainCommentImpl(
                postId = postId,
                userId = userId,
                parentId = parentId,
                content = content
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
