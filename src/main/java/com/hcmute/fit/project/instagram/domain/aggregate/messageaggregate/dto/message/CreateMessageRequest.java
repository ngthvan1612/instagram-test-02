package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message;

import lombok.Data;

@Data
public class CreateMessageRequest {


    private String content;

    private Integer senderId;
    private Integer receiverId;
    private Integer groupId;

}