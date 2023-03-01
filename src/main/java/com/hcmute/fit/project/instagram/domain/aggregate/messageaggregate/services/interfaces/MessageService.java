package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.CreateMessageRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.GetMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.ListMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.UpdateMessageRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface MessageService {
  SuccessfulResponse createMessage(CreateMessageRequest request);
  GetMessageResponse getMessageById(Integer id);
  ListMessageResponse searchMessages(Map<String, String> queries);
  SuccessfulResponse updateMessage(UpdateMessageRequest request);
  SuccessfulResponse deleteMessage(Integer id);


}
