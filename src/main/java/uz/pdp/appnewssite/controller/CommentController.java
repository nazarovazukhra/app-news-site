package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CommentDto commentDto) {

        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @Valid @RequestBody CommentDto commentDto) {

        ApiResponse apiResponse = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @PreAuthorize(value = "hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/myComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id) {

        ApiResponse apiResponse = commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }


}
