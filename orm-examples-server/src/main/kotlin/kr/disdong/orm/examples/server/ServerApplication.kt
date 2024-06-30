package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.common.logger.logger
import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.domain.comment.model.impl.PlainCommentImpl
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.post.model.impl.PlainPostImpl
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.domain.user.model.impl.PlainUserImpl
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.jpa.JpaApplication
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(DomainApplication::class, JpaApplication::class)
class ServerApplication(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) : ApplicationRunner {
    private val logger = logger<ServerApplication>()

    override fun run(args: ApplicationArguments?) {
        logger.info("run...")
        logger.info("${userRepository.findByUserId(1)}")

        val user = userRepository.save(PlainUserImpl(name = "111", phone = "0101111"))
        val post = postRepository.save(
            PlainPostImpl(
                authorId = user.id,
                title = "first",
                content = "first content",
            )
        )
        val comment = commentRepository.save(
            PlainCommentImpl(
                postId = post.id,
                userId = user.id,
                content = "first comment"
            )
        )

        logger.info("${postRepository.findAllByUserId(userId = user.id)}")
        logger.info("${commentRepository.findAllByPostId(postId = post.id)}")
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
