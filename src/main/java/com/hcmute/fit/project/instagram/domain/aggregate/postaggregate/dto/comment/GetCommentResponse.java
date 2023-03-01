package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetCommentResponse extends SuccessfulResponse {
  public GetCommentResponse(CommentResponse commentResponse) {
    super();
    this.setData(commentResponse);
  }
}
