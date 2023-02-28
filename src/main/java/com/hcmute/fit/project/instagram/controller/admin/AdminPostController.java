package com.hcmute.fit.project.instagram.controller.admin;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.PostService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/post")
public class AdminPostController {

  @Autowired
  private PostService postService;

  public AdminPostController() {

  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract searchPost(
    @RequestParam Map<String, String> queries
  ) {
    ListPostResponse listPostResponse = this.postService.searchPosts(queries);
    return listPostResponse;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract getPost(
    @PathVariable Integer id
  ) {
    GetPostResponse getPostResponse = this.postService.getPostById(id);
    return getPostResponse;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseBaseAbstract createPost(
    @RequestBody CreatePostRequest request
  ) {
    SuccessfulResponse createPostResponse = this.postService.createPost(request);
    return createPostResponse;
  }
  
  @PutMapping("{id}/update")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract updatePost(
    @PathVariable Integer id,
    @RequestBody UpdatePostRequest request
  ) {
    request.setPostId(id);
    SuccessfulResponse updatePostResponse = this.postService.updatePost(request);
    return updatePostResponse;
  }

  @DeleteMapping("{id}/delete")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract deletePost(
    @PathVariable Integer id
  ) {
    SuccessfulResponse updatePostResponse = this.postService.deletePost(id);
    return updatePostResponse;
  }
}
