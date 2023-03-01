package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListCommentResponse extends SuccessfulResponse {
  public ListCommentResponse(List<CommentResponse> commentResponses) {
    super();
    this.setData(commentResponses);
  }
}
