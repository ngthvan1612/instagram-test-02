package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListGroupMessageResponse extends SuccessfulResponse {
  public ListGroupMessageResponse(List<GroupMessageResponse> groupMessageResponses) {
    super();
    this.setData(groupMessageResponses);
  }
}
