package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody PostDto postDto) {

        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @Valid @RequestBody PostDto postDto) {

        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }


}
