package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserResponse extends SuccessfulResponse {
  public ListUserResponse(List<UserResponse> userResponses) {
    super();
    this.setData(userResponses);
  }
}
