package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetMessageResponse extends SuccessfulResponse {
    public GetMessageResponse(MessageResponse messageResponse) {
        super();
        this.setData(messageResponse);
    }
}
