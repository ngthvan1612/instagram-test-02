package com.hcmute.fit.project.instagram.controller.admin;

import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.dto.user.*;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.services.interfaces.UserService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    public AdminUserController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchUser(
            @RequestParam Map<String, String> queries
    ) {
        ListUserResponse listUserResponse = this.userService.searchUsers(queries);
        return listUserResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getUser(
            @PathVariable Integer id
    ) {
        GetUserResponse getUserResponse = this.userService.getUserById(id);
        return getUserResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createUser(
            @RequestBody CreateUserRequest request
    ) {
        SuccessfulResponse createUserResponse = this.userService.createUser(request);
        return createUserResponse;
    }

    @PutMapping("{id}/update-avatar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUserAvatar(
            @PathVariable Integer id,
            @RequestParam("avatarFile") MultipartFile avatarFile
    ) throws IOException {
        UpdateUserAvatarRequest request = new UpdateUserAvatarRequest();

        request.setUserId(id);
        request.setAvatarBufferByteArray(avatarFile.getBytes());
        request.setUploadFileName(avatarFile.getOriginalFilename());

        SuccessfulResponse updateUserAvatarResponse = this.userService.updateAvatarById(request);
        return updateUserAvatarResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateUser(
            @PathVariable Integer id,
            @RequestBody UpdateUserRequest request
    ) {
        request.setUserId(id);
        SuccessfulResponse updateUserResponse = this.userService.updateUser(request);
        return updateUserResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteUser(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateUserResponse = this.userService.deleteUser(id);
        return updateUserResponse;
    }
}
