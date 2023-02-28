package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.ReactionType;
import lombok.Data;

@Data
public class CreateReactionRequest {


    private ReactionType reaction;

    private Integer userId;
    private Integer postId;

}