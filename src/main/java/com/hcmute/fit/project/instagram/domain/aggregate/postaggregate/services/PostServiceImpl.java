package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.*;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.PostService;
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
public class PostServiceImpl implements PostService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public PostServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createPost(CreatePostRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalAuthor = this.userRepository.findById(request.getAuthorId());
    User author = null;
    
    if (optionalAuthor.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i ng?????i d??ng n??o v???i authorId = " + request.getAuthorId());
    }
    else {
      author = optionalAuthor.get();
    }
    
    
    Post post = new Post();
    
    post.setContent(request.getContent());
    post.setPrivacy(request.getPrivacy());
    post.setAuthor(author);

    //Save to database
    this.postRepository.save(post);

    //Return
    PostResponse postDTO = new PostResponse(post);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(postDTO);
    response.addMessage("T???o B??i ????ng th??nh c??ng");

    LOG.info("Created post with id = " + post.getId());
    return response;
  }

  @Override
  public GetPostResponse getPostById(Integer id) {
    if (!this.postRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y B??i ????ng n??o v???i id l?? " + id);
    }

    Post post = this.postRepository.findById(id).get();
    PostResponse postDTO = new PostResponse(post);
    GetPostResponse response = new GetPostResponse(postDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListPostResponse searchPosts(Map<String, String> queries) {
    List<PostResponse> listPostResponses = this.postRepository.searchPost(queries)
          .stream().map(post -> new PostResponse(post)).toList();
    
    ListPostResponse response = new ListPostResponse(listPostResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updatePost(UpdatePostRequest request) {
    //Check record exists
    if (!this.postRepository.existsById(request.getPostId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y B??i ????ng n??o v???i id l?? " + request.getPostId());
    }

    //Read data from request
    Post post = this.postRepository.findById(request.getPostId()).get();
    
    Optional<User> optionalAuthor = this.userRepository.findById(request.getAuthorId());
    User author = null;
    
    if (optionalAuthor.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i B??i ????ng n??o v???i authorId = " + request.getAuthorId());
    }
    else {
      author = optionalAuthor.get();
    }
    
    
    
    post.setContent(request.getContent());
    post.setPrivacy(request.getPrivacy());
    post.setAuthor(author);

    //Validate unique
    

    //Update last changed time
    post.setLastUpdatedAt(new Date());

    //Store
    this.postRepository.save(post);

    //Return
    PostResponse postDTO = new PostResponse(post);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(postDTO);
    response.addMessage("C???p nh???t B??i ????ng th??nh c??ng");

    LOG.info("Updated post with id = " + post.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deletePost(Integer id) {
    if (!this.postRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y B??i ????ng n??o v???i id l?? " + id);
    }

    Post post = this.postRepository.findById(id).get();
    post.setDeletedAt(new Date());
    
    this.postRepository.save(post);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a B??i ????ng th??nh c??ng");

    LOG.info("Deleted post with id = " + post.getId());
    return response;
  }
  
}
  