package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface UserService {
  SuccessfulResponse createUser(CreateUserRequest request);
  GetUserResponse getUserById(Integer id);
  ListUserResponse searchUsers(Map<String, String> queries);
  SuccessfulResponse updateUser(UpdateUserRequest request);
  SuccessfulResponse deleteUser(Integer id);

  SuccessfulResponse updateAvatarById(UpdateUserAvatarRequest request);


  LoginResponse authenticate(LoginRequest request);

}
