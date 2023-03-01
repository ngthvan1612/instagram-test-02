package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.*;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.entities.Follower;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.repositories.FollowerRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.services.interfaces.FollowerService;
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
public class FollowerServiceImpl implements FollowerService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private FollowerRepository followerRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public FollowerServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createFollower(CreateFollowerRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại người dùng nào với userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    Optional<User> optionalFollow = this.userRepository.findById(request.getFollowId());
    User follow = null;
    
    if (optionalFollow.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại người dùng nào với followId = " + request.getFollowId());
    }
    else {
      follow = optionalFollow.get();
    }
    
    
    Follower follower = new Follower();
    
    follower.setUser(user);
    follower.setFollow(follow);

    //Save to database
    this.followerRepository.save(follower);

    //Return
    FollowerResponse followerDTO = new FollowerResponse(follower);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(followerDTO);
    response.addMessage("Tạo Theo dõi thành công");

    LOG.info("Created follower with id = " + follower.getId());
    return response;
  }

  @Override
  public GetFollowerResponse getFollowerById(Integer id) {
    if (!this.followerRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Theo dõi nào với id là " + id);
    }

    Follower follower = this.followerRepository.findById(id).get();
    FollowerResponse followerDTO = new FollowerResponse(follower);
    GetFollowerResponse response = new GetFollowerResponse(followerDTO);

    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public ListFollowerResponse searchFollowers(Map<String, String> queries) {
    List<FollowerResponse> listFollowerResponses = this.followerRepository.searchFollower(queries)
          .stream().map(follower -> new FollowerResponse(follower)).toList();
    
    ListFollowerResponse response = new ListFollowerResponse(listFollowerResponses);
    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public SuccessfulResponse updateFollower(UpdateFollowerRequest request) {
    //Check record exists
    if (!this.followerRepository.existsById(request.getFollowerId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Theo dõi nào với id là " + request.getFollowerId());
    }

    //Read data from request
    Follower follower = this.followerRepository.findById(request.getFollowerId()).get();
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Theo dõi nào với userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    Optional<User> optionalFollow = this.userRepository.findById(request.getFollowId());
    User follow = null;
    
    if (optionalFollow.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Theo dõi nào với followId = " + request.getFollowId());
    }
    else {
      follow = optionalFollow.get();
    }
    
    
    
    follower.setUser(user);
    follower.setFollow(follow);

    //Validate unique
    

    //Update last changed time
    follower.setLastUpdatedAt(new Date());

    //Store
    this.followerRepository.save(follower);

    //Return
    FollowerResponse followerDTO = new FollowerResponse(follower);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(followerDTO);
    response.addMessage("Cập nhật Theo dõi thành công");

    LOG.info("Updated follower with id = " + follower.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteFollower(Integer id) {
    if (!this.followerRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Theo dõi nào với id là " + id);
    }

    Follower follower = this.followerRepository.findById(id).get();
    follower.setDeletedAt(new Date());
    
    this.followerRepository.save(follower);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("Xóa Theo dõi thành công");

    LOG.info("Deleted follower with id = " + follower.getId());
    return response;
  }
  
}
  