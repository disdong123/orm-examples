package kr.disdong.orm.examples.domain.paging

class PageResponse <T>(
    val values: List<T>,
    val hasNext: Boolean,
) {

    companion object {
        fun <T> of(values: List<T>, pageSize: Int): PageResponse<T> {
            val hasNext = values.size > pageSize
            val resultValues = if (hasNext) values.subList(0, pageSize) else values

            return PageResponse(
                values = resultValues,
                hasNext = hasNext
            )
        }
    }
}

fun <T> List<T>.toPageResponse(pageSize: Int): PageResponse<T> {
    return PageResponse.of(this, pageSize)
}
