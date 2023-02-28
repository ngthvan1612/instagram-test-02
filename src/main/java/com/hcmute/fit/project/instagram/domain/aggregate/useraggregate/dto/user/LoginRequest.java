package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
