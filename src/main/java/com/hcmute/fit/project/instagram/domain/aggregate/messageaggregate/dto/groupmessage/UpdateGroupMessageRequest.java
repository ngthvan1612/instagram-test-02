package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateGroupMessageRequest {
    @JsonIgnore
    private Integer groupMessageId;

    private String displayName;

    private Integer adminId;
}
