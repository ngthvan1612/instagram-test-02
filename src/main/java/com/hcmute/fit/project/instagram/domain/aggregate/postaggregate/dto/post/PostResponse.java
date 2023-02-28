package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.PostPrivacy;
import lombok.Data;

import java.util.Date;

/**
  * Data transfer object (DTO) for Post
*/
@Data
public class PostResponse {
  
  private Integer id;
  
  private Date createdAt;
  
  private Date lastUpdatedAt;
  
  private Date deletedAt;
  
  private String content;
  
  private PostPrivacy privacy;
  
  private Integer authorId;
  

  public PostResponse(Post post) {
    
    this.id = post.getId();
    this.createdAt = post.getCreatedAt();
    this.lastUpdatedAt = post.getLastUpdatedAt();
    this.deletedAt = post.getDeletedAt();
    this.content = post.getContent();
    this.privacy = post.getPrivacy();
    if (post.getAuthor() != null) {
      this.authorId = post.getAuthor().getId();
    }
    
  }
}