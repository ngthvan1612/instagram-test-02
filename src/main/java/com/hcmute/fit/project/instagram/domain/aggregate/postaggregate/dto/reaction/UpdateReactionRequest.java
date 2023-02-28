package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.ReactionType;
import lombok.Data;

@Data
public class UpdateReactionRequest {
    @JsonIgnore
    private Integer reactionId;

    private ReactionType reaction;

    private Integer userId;
    private Integer postId;
}
