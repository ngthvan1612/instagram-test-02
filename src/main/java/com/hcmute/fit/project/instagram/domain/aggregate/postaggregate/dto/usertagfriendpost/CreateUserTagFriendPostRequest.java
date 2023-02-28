package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.usertagfriendpost;

import lombok.Data;

@Data
public class CreateUserTagFriendPostRequest {

    private Integer postId;
    private Integer friendId;

}