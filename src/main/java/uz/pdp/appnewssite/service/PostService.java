package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    final PostRepository postRepository;
    final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public ApiResponse addPost(PostDto postDto) {

        boolean existsByTitle = postRepository.existsByTitle(postDto.getTitle());
        if (existsByTitle)
            return new ApiResponse("Such post already exists", false);

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());

        postRepository.save(post);
        return new ApiResponse("Post added", true);
    }

    public ApiResponse editPost(Long id, PostDto postDto) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (!optionalPost.isPresent())
            return new ApiResponse("Such post not found", false);

        Post editingPost = optionalPost.get();
        editingPost.setText(postDto.getText());
        editingPost.setTitle(postDto.getTitle());
        editingPost.setUrl(postDto.getUrl());

        postRepository.save(editingPost);
        return new ApiResponse("Post edited", true);

    }

    public ApiResponse deletePost(Long id) {

        // METHOD 1

//        Optional<Post> optionalPost = postRepository.findById(id);
//        if (!optionalPost.isPresent())
//            return new ApiResponse("Such post not found", false);
//
//        Post post = optionalPost.get();
//
//        postRepository.delete(post);
//        commentRepository.deleteCommentsByPostTitle(post.getTitle());
//        return new ApiResponse("Post deleted", true);


        // METHOD 2

        boolean existsById = postRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Such post not found", false);

        postRepository.deleteById(id);

        List<Comment> commentList = commentRepository.findCommentByPostId(id);
        for (Comment comment : commentList) {
            commentRepository.delete(comment);
        }
        return new ApiResponse("Post deleted", true);
    }
}
