package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListMessageResponse extends SuccessfulResponse {
  public ListMessageResponse(List<MessageResponse> messageResponses) {
    super();
    this.setData(messageResponses);
  }
}
