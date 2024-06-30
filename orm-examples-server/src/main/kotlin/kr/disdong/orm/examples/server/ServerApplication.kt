package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.common.logger.logger
import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.post.repository.PostRepository
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
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
