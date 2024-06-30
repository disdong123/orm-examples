package kr.disdong.orm.examples.server

import kr.disdong.orm.examples.domain.DomainApplication
import kr.disdong.orm.examples.jpa.JpaApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(DomainApplication::class, JpaApplication::class) // scanBasePackages 설정은 지워도 됩니다.
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
