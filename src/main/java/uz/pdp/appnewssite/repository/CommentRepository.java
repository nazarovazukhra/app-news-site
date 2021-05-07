package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appnewssite.entity.Comment;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = " select c from Comment c where c.post.id=:postId")
    List<Comment> findCommentByPostId(Long postId);

    void deleteCommentsByPostTitle(String postTitle);

    @Query(value = "select c from Comment c where c.id=:commentId and c.createdBy=:createdById")
    Optional<Comment> getComment(Long commentId,Long createdById);


}
