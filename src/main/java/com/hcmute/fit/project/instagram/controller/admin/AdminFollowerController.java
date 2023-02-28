package com.hcmute.fit.project.instagram.controller.admin;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.CreateFollowerRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.GetFollowerResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.ListFollowerResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.UpdateFollowerRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.services.interfaces.FollowerService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/follower")
public class AdminFollowerController {

    @Autowired
    private FollowerService followerService;

    public AdminFollowerController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchFollower(
            @RequestParam Map<String, String> queries
    ) {
        ListFollowerResponse listFollowerResponse = this.followerService.searchFollowers(queries);
        return listFollowerResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getFollower(
            @PathVariable Integer id
    ) {
        GetFollowerResponse getFollowerResponse = this.followerService.getFollowerById(id);
        return getFollowerResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createFollower(
            @RequestBody CreateFollowerRequest request
    ) {
        SuccessfulResponse createFollowerResponse = this.followerService.createFollower(request);
        return createFollowerResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateFollower(
            @PathVariable Integer id,
            @RequestBody UpdateFollowerRequest request
    ) {
        request.setFollowerId(id);
        SuccessfulResponse updateFollowerResponse = this.followerService.updateFollower(request);
        return updateFollowerResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteFollower(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateFollowerResponse = this.followerService.deleteFollower(id);
        return updateFollowerResponse;
    }
}
