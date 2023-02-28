package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.entities.Follower;
import lombok.Data;

import java.util.Date;

/**
  * Data transfer object (DTO) for Follower
*/
@Data
public class FollowerResponse {
  
  private Integer id;
  
  private Date createdAt;
  
  private Date lastUpdatedAt;
  
  private Date deletedAt;
  
  private Integer userId;
  
  private Integer followId;
  

  public FollowerResponse(Follower follower) {
    
    this.id = follower.getId();
    this.createdAt = follower.getCreatedAt();
    this.lastUpdatedAt = follower.getLastUpdatedAt();
    this.deletedAt = follower.getDeletedAt();
    if (follower.getUser() != null) {
      this.userId = follower.getUser().getId();
    }
    
    if (follower.getFollow() != null) {
      this.followId = follower.getFollow().getId();
    }
    
  }
}