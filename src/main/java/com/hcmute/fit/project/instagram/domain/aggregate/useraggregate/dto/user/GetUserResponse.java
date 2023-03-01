package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetUserResponse extends SuccessfulResponse {
  public GetUserResponse(UserResponse userResponse) {
    super();
    this.setData(userResponse);
  }
}
