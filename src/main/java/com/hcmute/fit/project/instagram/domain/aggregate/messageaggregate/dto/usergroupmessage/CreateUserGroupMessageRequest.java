package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage;

import lombok.Data;

@Data
public class CreateUserGroupMessageRequest {

    private Integer groupId;
    private Integer memberId;

}