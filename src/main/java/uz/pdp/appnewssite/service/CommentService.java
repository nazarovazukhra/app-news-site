package uz.pdp.appnewssite.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    final CommentRepository commentRepository;
    final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ApiResponse addComment(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setText(commentDto.getText());

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Such post not found", false);

        Post post = optionalPost.get();

        comment.setPost(post);
        commentRepository.save(comment);
        return new ApiResponse("Comment added", true);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();

        boolean existsById = commentRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Such comment not found", false);

        Optional<Comment> optionalComment = commentRepository.getComment(id, userId);
        if (!optionalComment.isPresent())
            return new ApiResponse("Such user has not such comment", false);

        Comment editingComment = optionalComment.get();
        editingComment.setText(commentDto.getText());
        commentRepository.save(editingComment);
        return new ApiResponse("Comment edited", true);
    }

    public ApiResponse deleteComment(Long id) {

        boolean existsById = commentRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Such comment not found", false);

        commentRepository.deleteById(id);
        return new ApiResponse("Comment deleted", true);

    }

    public ApiResponse deleteMyComment(Long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();

        boolean existsById = commentRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Such comment not found", false);

        Optional<Comment> optionalComment = commentRepository.getComment(id, userId);
        if (!optionalComment.isPresent())
            return new ApiResponse("Such user has not such comment", false);

        Comment comment = optionalComment.get();
        commentRepository.delete(comment);
        return new ApiResponse("My comment deleted", true);
    }
}
