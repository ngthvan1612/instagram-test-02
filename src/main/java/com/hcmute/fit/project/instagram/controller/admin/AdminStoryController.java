package com.hcmute.fit.project.instagram.controller.admin;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.CreateStoryRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.GetStoryResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.ListStoryResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.services.interfaces.StoryService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/admin/story")
public class AdminStoryController {

  @Autowired
  private StoryService storyService;

  public AdminStoryController() {

  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract searchStory(
    @RequestParam Map<String, String> queries
  ) {
    ListStoryResponse listStoryResponse = this.storyService.searchStorys(queries);
    return listStoryResponse;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract getStory(
    @PathVariable Integer id
  ) {
    GetStoryResponse getStoryResponse = this.storyService.getStoryById(id);
    return getStoryResponse;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseBaseAbstract createStory(
    @RequestBody CreateStoryRequest request
  ) {
    SuccessfulResponse createStoryResponse = this.storyService.createStory(request);
    return createStoryResponse;
  }
  
  @PutMapping("{id}/update")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract updateStory(
    @PathVariable Integer id,
    @RequestBody UpdateStoryRequest request
  ) {
    request.setStoryId(id);
    SuccessfulResponse updateStoryResponse = this.storyService.updateStory(request);
    return updateStoryResponse;
  }

  @DeleteMapping("{id}/delete")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract deleteStory(
    @PathVariable Integer id
  ) {
    SuccessfulResponse updateStoryResponse = this.storyService.deleteStory(id);
    return updateStoryResponse;
  }
}
