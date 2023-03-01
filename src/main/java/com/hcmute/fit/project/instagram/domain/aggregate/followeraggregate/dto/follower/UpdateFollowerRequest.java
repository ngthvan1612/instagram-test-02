package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateFollowerRequest {
  @JsonIgnore
  private Integer followerId;
  
  
  private Integer userId;
  private Integer followId;
}
