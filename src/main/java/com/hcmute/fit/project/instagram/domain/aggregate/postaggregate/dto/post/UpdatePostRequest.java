package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

@Data
public class UpdatePostRequest {
  @JsonIgnore
  private Integer postId;
  
  private String content;
  private PostPrivacy privacy;
  
  private Integer authorId;
}
