package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListReactionResponse extends SuccessfulResponse {
    public ListReactionResponse(List<ReactionResponse> reactionResponses) {
        super();
        this.setData(reactionResponses);
    }
}
