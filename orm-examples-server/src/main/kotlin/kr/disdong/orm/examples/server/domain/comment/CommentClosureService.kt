package kr.disdong.orm.examples.server.domain.comment

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import kr.disdong.orm.examples.domain.comment.model.Comment
import kr.disdong.orm.examples.domain.comment.model.impl.PlainCommentImpl
import kr.disdong.orm.examples.domain.comment.repository.CommentRepository
import kr.disdong.orm.examples.domain.post.model.Post
import kr.disdong.orm.examples.domain.post.model.impl.PlainPostImpl
import kr.disdong.orm.examples.domain.post.repository.PostRepository
import kr.disdong.orm.examples.domain.user.model.User
import kr.disdong.orm.examples.domain.user.model.impl.PlainUserImpl
import kr.disdong.orm.examples.domain.user.repository.UserRepository
import kr.disdong.orm.examples.jpa.domain.comment.repository.CommentJpaRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class CommentClosureService(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    @Qualifier("commentClosureRepositoryImpl")
    private val commentRepository: CommentRepository,
    private val commentJpaRepository: CommentJpaRepository,
) {

    fun comments(postId: Long) {
        val p1 = seed()
        val om = ObjectMapper()
        om.registerModules(JavaTimeModule())
        println(om.writeValueAsString(commentRepository.findCommentViewsByPostId(p1.id)))
        println(om.writeValueAsString(commentRepository.findCommentViewsByPostId(p1.id, 10, null)))
        // TODO 바로 하위의 댓글만 조회된다.
        //  넣을때 1-2, 2-3 이 연결되어 있으면 path 에는 1-1, 1-2, 1-3, 2-2, 2-3, 3-3 이렇게 전부 넣어야 한다.
        //  지금은 1-1, 1-2, 2-2, 2-3, 3-3 만 넣고 있어서 1로 조회 했을 때 2만 나온다.
        //  다른 방법으로는 계층으로 하지 말고 단순히 1-2 1-3 으로 플랫하게 할 수 있을것같다. 유튜브처럼.
        //  replycount 는 컬럼을 만드는게 낫지 않을까?
        println(om.writeValueAsString(commentRepository.findCommentViewRepliesById(p1.id, 10, null)))
    }

    private fun seed(): Post {
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

        return p1
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
