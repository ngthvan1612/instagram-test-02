package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.CreateGroupMessageRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.GetGroupMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.ListGroupMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.UpdateGroupMessageRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface GroupMessageService {
  SuccessfulResponse createGroupMessage(CreateGroupMessageRequest request);
  GetGroupMessageResponse getGroupMessageById(Integer id);
  ListGroupMessageResponse searchGroupMessages(Map<String, String> queries);
  SuccessfulResponse updateGroupMessage(UpdateGroupMessageRequest request);
  SuccessfulResponse deleteGroupMessage(Integer id);


}
