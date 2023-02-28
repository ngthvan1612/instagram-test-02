package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateMessageRequest {
    @JsonIgnore
    private Integer messageId;

    private String content;

    private Integer senderId;
    private Integer receiverId;
    private Integer groupId;
}
