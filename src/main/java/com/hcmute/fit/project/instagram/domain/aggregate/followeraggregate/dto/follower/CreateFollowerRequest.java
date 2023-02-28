package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower;

import lombok.Data;

@Data
public class CreateFollowerRequest {
  
  private Integer userId;
  private Integer followId;
  
}