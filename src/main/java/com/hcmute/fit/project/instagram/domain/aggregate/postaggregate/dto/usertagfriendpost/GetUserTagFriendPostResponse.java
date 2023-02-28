package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetUserTagFriendPostResponse extends SuccessfulResponse {
  public GetUserTagFriendPostResponse(UserTagFriendPostResponse userTagFriendPostResponse) {
    super();
    this.setData(userTagFriendPostResponse);
  }
}
