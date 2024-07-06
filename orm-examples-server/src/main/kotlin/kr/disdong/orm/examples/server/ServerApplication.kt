package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.common.logger.logger
import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.domain.post.model.impl.PlainPostImpl
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.exposed.ExposedApplication
import kr.disdong.orm.examples.jpa.JpaApplication
import kr.disdong.orm.examples.server.domain.comment.CommentClosureService
import kr.disdong.orm.examples.server.domain.comment.CommentService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@Import(DomainApplication::class, JpaApplication::class, ExposedApplication::class)
class ServerApplication(
    private val commentService: CommentService,
    private val commentClosureService: CommentClosureService,
    @Qualifier("userExposedRepositoryImpl")
    private val userRepository: UserRepository,
    @Qualifier("postExposedRepositoryImpl")
    private val postRepository: PostRepository,
) : ApplicationRunner {
    private val logger = logger<ServerApplication>()

    @Transactional
    override fun run(args: ApplicationArguments?) {
        logger.info("run...")

        // commentService.comments(1)
        // commentClosureService.comments(1)

        // val user = userRepository.save(PlainUserImpl(name = "123", phone = "123"))
        println(userRepository.findByUserId(1000))
        println(postRepository.save(PlainPostImpl(authorId = 1, title = "123", content = "1234")))
        println(postRepository.findAllByUserId(1))
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
