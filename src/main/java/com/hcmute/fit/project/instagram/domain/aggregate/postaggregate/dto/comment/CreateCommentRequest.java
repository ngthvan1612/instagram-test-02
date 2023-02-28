package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.comment;

import lombok.Data;

@Data
public class CreateCommentRequest {
  
  
  private String content;
  
  private Integer userId;
  private Integer postId;
  private Integer parentId;
  
}