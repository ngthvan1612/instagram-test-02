package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.*;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.entities.Story;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.repositories.StoryRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.services.interfaces.StoryService;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.fit.project.instagram.domain.base.StorageRepository;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import com.hcmute.fit.project.instagram.domain.exception.ServiceExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private StoryRepository storyRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public StoryServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createStory(CreateStoryRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i ng?????i d??ng n??o v???i userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    Story story = new Story();
    
    story.setContent(request.getContent());
    story.setUser(user);

    //Save to database
    this.storyRepository.save(story);

    //Return
    StoryResponse storyDTO = new StoryResponse(story);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(storyDTO);
    response.addMessage("T???o Story 24h th??nh c??ng");

    LOG.info("Created story with id = " + story.getId());
    return response;
  }

  @Override
  public GetStoryResponse getStoryById(Integer id) {
    if (!this.storyRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Story 24h n??o v???i id l?? " + id);
    }

    Story story = this.storyRepository.findById(id).get();
    StoryResponse storyDTO = new StoryResponse(story);
    GetStoryResponse response = new GetStoryResponse(storyDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListStoryResponse searchStorys(Map<String, String> queries) {
    List<StoryResponse> listStoryResponses = this.storyRepository.searchStory(queries)
          .stream().map(story -> new StoryResponse(story)).toList();
    
    ListStoryResponse response = new ListStoryResponse(listStoryResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updateStory(UpdateStoryRequest request) {
    //Check record exists
    if (!this.storyRepository.existsById(request.getStoryId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Story 24h n??o v???i id l?? " + request.getStoryId());
    }

    //Read data from request
    Story story = this.storyRepository.findById(request.getStoryId()).get();
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Story 24h n??o v???i userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    
    story.setContent(request.getContent());
    story.setUser(user);

    //Validate unique
    

    //Update last changed time
    story.setLastUpdatedAt(new Date());

    //Store
    this.storyRepository.save(story);

    //Return
    StoryResponse storyDTO = new StoryResponse(story);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(storyDTO);
    response.addMessage("C???p nh???t Story 24h th??nh c??ng");

    LOG.info("Updated story with id = " + story.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteStory(Integer id) {
    if (!this.storyRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Story 24h n??o v???i id l?? " + id);
    }

    Story story = this.storyRepository.findById(id).get();
    story.setDeletedAt(new Date());
    
    this.storyRepository.save(story);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a Story 24h th??nh c??ng");

    LOG.info("Deleted story with id = " + story.getId());
    return response;
  }
  
}
  