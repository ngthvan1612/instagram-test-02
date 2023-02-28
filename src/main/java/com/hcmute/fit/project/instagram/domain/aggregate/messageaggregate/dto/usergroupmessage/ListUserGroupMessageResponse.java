package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListUserGroupMessageResponse extends SuccessfulResponse {
  public ListUserGroupMessageResponse(List<UserGroupMessageResponse> userGroupMessageResponses) {
    super();
    this.setData(userGroupMessageResponses);
  }
}
