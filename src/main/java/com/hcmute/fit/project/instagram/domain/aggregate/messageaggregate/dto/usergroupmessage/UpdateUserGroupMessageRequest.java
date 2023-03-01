package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.usergroupmessage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateUserGroupMessageRequest {
  @JsonIgnore
  private Integer userGroupMessageId;
  
  
  private Integer groupId;
  private Integer memberId;
}
