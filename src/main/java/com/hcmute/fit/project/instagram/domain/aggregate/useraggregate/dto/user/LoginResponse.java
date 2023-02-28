package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginResponse extends SuccessfulResponse {
    public LoginResponse(UserResponse userResponse, String jwtToken) {
        super();
        Map<String, Object> result = new HashMap<>();
        result.put("user", userResponse);
        result.put("accessToken", jwtToken);
        this.setData(result);
    }
}
