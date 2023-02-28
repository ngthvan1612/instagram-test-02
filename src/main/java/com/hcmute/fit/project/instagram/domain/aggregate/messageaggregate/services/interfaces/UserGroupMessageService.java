package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage.CreateUserGroupMessageRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage.GetUserGroupMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage.ListUserGroupMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage.UpdateUserGroupMessageRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserGroupMessageService {
    SuccessfulResponse createUserGroupMessage(CreateUserGroupMessageRequest request);

    GetUserGroupMessageResponse getUserGroupMessageById(Integer id);

    ListUserGroupMessageResponse searchUserGroupMessages(Map<String, String> queries);

    SuccessfulResponse updateUserGroupMessage(UpdateUserGroupMessageRequest request);

    SuccessfulResponse deleteUserGroupMessage(Integer id);


}
