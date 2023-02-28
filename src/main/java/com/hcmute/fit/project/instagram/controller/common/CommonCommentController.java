package com.hcmute.fit.project.instagram.controller.common;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.CommentService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/comment")
public class CommonCommentController {

    @Autowired
    private CommentService commentService;

    public CommonCommentController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchComment(
            @RequestParam Map<String, String> queries
    ) {
        ListCommentResponse listCommentResponse = this.commentService.searchComments(queries);
        return listCommentResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getComment(
            @PathVariable Integer id
    ) {
        GetCommentResponse getCommentResponse = this.commentService.getCommentById(id);
        return getCommentResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createComment(
            @RequestBody @Valid CreateCommentRequest request
    ) {
        SuccessfulResponse createCommentResponse = this.commentService.createComment(request);
        return createCommentResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateComment(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateCommentRequest request
    ) {
        request.setCommentId(id);
        SuccessfulResponse updateCommentResponse = this.commentService.updateComment(request);
        return updateCommentResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteComment(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateCommentResponse = this.commentService.deleteComment(id);
        return updateCommentResponse;
    }
}
