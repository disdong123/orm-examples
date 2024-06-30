package kr.disdong.orm.examples.server.domain.user.controller.spec

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.disdong.orm.examples.common.dto.TemplateResponse
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.server.domain.user.dto.CreateUserBody

@Tag(name = "유저")
interface UserSpec {

    @Operation
    fun getByUserId(userId: Long): TemplateResponse<User>

    @Operation
    fun create(body: CreateUserBody): TemplateResponse<User>
}
