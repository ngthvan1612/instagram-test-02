package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.enums.UserGender;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.enums.UserRole;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserRequest {
  @JsonIgnore
  private Integer userId;
  
  private String username;
  private String password;
  private String displayName;
  private Date birthday;
  private String avatar;
  private String profile;
  private UserGender gender;
  private UserRole role;
  
}
