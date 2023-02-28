package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetReactionResponse extends SuccessfulResponse {
    public GetReactionResponse(ReactionResponse reactionResponse) {
        super();
        this.setData(reactionResponse);
    }
}
