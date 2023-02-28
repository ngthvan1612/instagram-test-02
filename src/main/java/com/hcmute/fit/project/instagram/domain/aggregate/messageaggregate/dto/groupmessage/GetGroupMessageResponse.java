package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetGroupMessageResponse extends SuccessfulResponse {
  public GetGroupMessageResponse(GroupMessageResponse groupMessageResponse) {
    super();
    this.setData(groupMessageResponse);
  }
}
