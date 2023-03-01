package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

@Data
public class CreatePostRequest {
  
  
  private String content;
  
  
  private PostPrivacy privacy;
  
  private Integer authorId;
  
}