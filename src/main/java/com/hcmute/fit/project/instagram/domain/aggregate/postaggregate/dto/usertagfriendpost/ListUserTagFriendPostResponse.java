package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.usertagfriendpost;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserTagFriendPostResponse extends SuccessfulResponse {
  public ListUserTagFriendPostResponse(List<UserTagFriendPostResponse> userTagFriendPostResponses) {
    super();
    this.setData(userTagFriendPostResponses);
  }
}
