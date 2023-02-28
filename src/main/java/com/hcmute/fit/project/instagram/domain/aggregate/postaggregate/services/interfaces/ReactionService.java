package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface ReactionService {
    SuccessfulResponse createReaction(CreateReactionRequest request);

    GetReactionResponse getReactionById(Integer id);

    ListReactionResponse searchReactions(Map<String, String> queries);

    SuccessfulResponse updateReaction(UpdateReactionRequest request);

    SuccessfulResponse deleteReaction(Integer id);


}
