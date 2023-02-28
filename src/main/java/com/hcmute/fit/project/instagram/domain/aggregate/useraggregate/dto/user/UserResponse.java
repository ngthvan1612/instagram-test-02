package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user;

import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.enums.UserGender;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.enums.UserRole;
import lombok.Data;

import java.util.Date;

/**
 * Data transfer object (DTO) for User
 */
@Data
public class UserResponse {

    private Integer id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Date deletedAt;

    private String username;

    private String password;

    private String displayName;

    private Date birthday;

    private String avatar;

    private String profile;

    private UserGender gender;

    private UserRole role;


    public UserResponse(User user) {

        this.id = user.getId();
        this.createdAt = user.getCreatedAt();
        this.lastUpdatedAt = user.getLastUpdatedAt();
        this.deletedAt = user.getDeletedAt();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.displayName = user.getDisplayName();
        this.birthday = user.getBirthday();
        this.avatar = user.getAvatar();
        this.profile = user.getProfile();
        this.gender = user.getGender();
        this.role = user.getRole();
    }
}