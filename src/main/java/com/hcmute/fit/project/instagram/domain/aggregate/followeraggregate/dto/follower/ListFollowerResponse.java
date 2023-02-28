package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListFollowerResponse extends SuccessfulResponse {
    public ListFollowerResponse(List<FollowerResponse> followerResponses) {
        super();
        this.setData(followerResponses);
    }
}
