package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetPostResponse extends SuccessfulResponse {
    public GetPostResponse(PostResponse postResponse) {
        super();
        this.setData(postResponse);
    }
}
