package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.common.logger.logger
import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.jpa.JpaApplication
import kr.disdong.orm.examples.server.domain.comment.CommentService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
@Import(DomainApplication::class, JpaApplication::class)
class ServerApplication(
    private val commentService: CommentService,
) : ApplicationRunner {
    private val logger = logger<ServerApplication>()

    @Transactional
    override fun run(args: ApplicationArguments?) {
        logger.info("run...")

        commentService.comments(1)
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
