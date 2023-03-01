package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListPostResponse extends SuccessfulResponse {
  public ListPostResponse(List<PostResponse> postResponses) {
    super();
    this.setData(postResponses);
  }
}
