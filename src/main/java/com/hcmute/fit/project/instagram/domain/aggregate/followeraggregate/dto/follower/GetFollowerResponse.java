package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetFollowerResponse extends SuccessfulResponse {
    public GetFollowerResponse(FollowerResponse followerResponse) {
        super();
        this.setData(followerResponse);
    }
}
