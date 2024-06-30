package kr.disdong.orm.examples.common.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

/**
 *
 * @property message
 */
abstract class TemplateException(
    override val message: String,
) : RuntimeException(message) {

    abstract fun getCode(): HttpStatus
}

open class NotFoundException(
    override val message: String,
) : TemplateException(message) {
    override fun getCode(): HttpStatus {
        return HttpStatus.NOT_FOUND
    }
}
