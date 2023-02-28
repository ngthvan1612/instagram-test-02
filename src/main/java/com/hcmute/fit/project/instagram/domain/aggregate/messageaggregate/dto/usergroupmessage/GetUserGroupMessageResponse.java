package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetUserGroupMessageResponse extends SuccessfulResponse {
    public GetUserGroupMessageResponse(UserGroupMessageResponse userGroupMessageResponse) {
        super();
        this.setData(userGroupMessageResponse);
    }
}
