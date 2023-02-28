package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.CreateCommentRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.GetCommentResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.ListCommentResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment.UpdateCommentRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface CommentService {
    SuccessfulResponse createComment(CreateCommentRequest request);

    GetCommentResponse getCommentById(Integer id);

    ListCommentResponse searchComments(Map<String, String> queries);

    SuccessfulResponse updateComment(UpdateCommentRequest request);

    SuccessfulResponse deleteComment(Integer id);


}
