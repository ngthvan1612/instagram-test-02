package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.usertagfriendpost.*;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories.UserTagFriendPostRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.UserTagFriendPostService;
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
public class UserTagFriendPostServiceImpl implements UserTagFriendPostService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private UserTagFriendPostRepository userTagFriendPostRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private StorageRepository storageRepository;

  public UserTagFriendPostServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createUserTagFriendPost(CreateUserTagFriendPostRequest request) {
    //Validate
    

    //Check null
    
    Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
    Post post = null;
    
    if (optionalPost.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i B??i ????ng n??o v???i postId = " + request.getPostId());
    }
    else {
      post = optionalPost.get();
    }
    
    
    Optional<User> optionalFriend = this.userRepository.findById(request.getFriendId());
    User friend = null;
    
    if (optionalFriend.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i ng?????i d??ng n??o v???i friendId = " + request.getFriendId());
    }
    else {
      friend = optionalFriend.get();
    }
    
    
    UserTagFriendPost userTagFriendPost = new UserTagFriendPost();
    
    userTagFriendPost.setPost(post);
    userTagFriendPost.setFriend(friend);

    //Save to database
    this.userTagFriendPostRepository.save(userTagFriendPost);

    //Return
    UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(userTagFriendPostDTO);
    response.addMessage("T???o Tag b???n b?? c???a b??i ????ng th??nh c??ng");

    LOG.info("Created userTagFriendPost with id = " + userTagFriendPost.getId());
    return response;
  }

  @Override
  public GetUserTagFriendPostResponse getUserTagFriendPostById(Integer id) {
    if (!this.userTagFriendPostRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tag b???n b?? c???a b??i ????ng n??o v???i id l?? " + id);
    }

    UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(id).get();
    UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
    GetUserTagFriendPostResponse response = new GetUserTagFriendPostResponse(userTagFriendPostDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListUserTagFriendPostResponse searchUserTagFriendPosts(Map<String, String> queries) {
    List<UserTagFriendPostResponse> listUserTagFriendPostResponses = this.userTagFriendPostRepository.searchUserTagFriendPost(queries)
          .stream().map(userTagFriendPost -> new UserTagFriendPostResponse(userTagFriendPost)).toList();
    
    ListUserTagFriendPostResponse response = new ListUserTagFriendPostResponse(listUserTagFriendPostResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updateUserTagFriendPost(UpdateUserTagFriendPostRequest request) {
    //Check record exists
    if (!this.userTagFriendPostRepository.existsById(request.getUserTagFriendPostId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tag b???n b?? c???a b??i ????ng n??o v???i id l?? " + request.getUserTagFriendPostId());
    }

    //Read data from request
    UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(request.getUserTagFriendPostId()).get();
    
    Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
    Post post = null;
    
    if (optionalPost.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Tag b???n b?? c???a b??i ????ng n??o v???i postId = " + request.getPostId());
    }
    else {
      post = optionalPost.get();
    }
    
    
    Optional<User> optionalFriend = this.userRepository.findById(request.getFriendId());
    User friend = null;
    
    if (optionalFriend.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Tag b???n b?? c???a b??i ????ng n??o v???i friendId = " + request.getFriendId());
    }
    else {
      friend = optionalFriend.get();
    }
    
    
    
    userTagFriendPost.setPost(post);
    userTagFriendPost.setFriend(friend);

    //Validate unique
    

    //Update last changed time
    userTagFriendPost.setLastUpdatedAt(new Date());

    //Store
    this.userTagFriendPostRepository.save(userTagFriendPost);

    //Return
    UserTagFriendPostResponse userTagFriendPostDTO = new UserTagFriendPostResponse(userTagFriendPost);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(userTagFriendPostDTO);
    response.addMessage("C???p nh???t Tag b???n b?? c???a b??i ????ng th??nh c??ng");

    LOG.info("Updated userTagFriendPost with id = " + userTagFriendPost.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteUserTagFriendPost(Integer id) {
    if (!this.userTagFriendPostRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tag b???n b?? c???a b??i ????ng n??o v???i id l?? " + id);
    }

    UserTagFriendPost userTagFriendPost = this.userTagFriendPostRepository.findById(id).get();
    userTagFriendPost.setDeletedAt(new Date());
    
    this.userTagFriendPostRepository.save(userTagFriendPost);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a Tag b???n b?? c???a b??i ????ng th??nh c??ng");

    LOG.info("Deleted userTagFriendPost with id = " + userTagFriendPost.getId());
    return response;
  }
  
}
  