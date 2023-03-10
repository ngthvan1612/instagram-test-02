package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.*;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Reaction;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories.PostRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories.ReactionRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.ReactionService;
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
public class ReactionServiceImpl implements ReactionService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private ReactionRepository reactionRepository;
  
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public ReactionServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createReaction(CreateReactionRequest request) {
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
    
    
    Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
    Post post = null;
    
    if (optionalPost.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i B??i ????ng n??o v???i postId = " + request.getPostId());
    }
    else {
      post = optionalPost.get();
    }
    
    
    Reaction reaction = new Reaction();
    
    reaction.setReaction(request.getReaction());
    reaction.setUser(user);
    reaction.setPost(post);

    //Save to database
    this.reactionRepository.save(reaction);

    //Return
    ReactionResponse reactionDTO = new ReactionResponse(reaction);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(reactionDTO);
    response.addMessage("T???o Th??? c???m x??c th??nh c??ng");

    LOG.info("Created reaction with id = " + reaction.getId());
    return response;
  }

  @Override
  public GetReactionResponse getReactionById(Integer id) {
    if (!this.reactionRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Th??? c???m x??c n??o v???i id l?? " + id);
    }

    Reaction reaction = this.reactionRepository.findById(id).get();
    ReactionResponse reactionDTO = new ReactionResponse(reaction);
    GetReactionResponse response = new GetReactionResponse(reactionDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListReactionResponse searchReactions(Map<String, String> queries) {
    List<ReactionResponse> listReactionResponses = this.reactionRepository.searchReaction(queries)
          .stream().map(reaction -> new ReactionResponse(reaction)).toList();
    
    ListReactionResponse response = new ListReactionResponse(listReactionResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updateReaction(UpdateReactionRequest request) {
    //Check record exists
    if (!this.reactionRepository.existsById(request.getReactionId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Th??? c???m x??c n??o v???i id l?? " + request.getReactionId());
    }

    //Read data from request
    Reaction reaction = this.reactionRepository.findById(request.getReactionId()).get();
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Th??? c???m x??c n??o v???i userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    Optional<Post> optionalPost = this.postRepository.findById(request.getPostId());
    Post post = null;
    
    if (optionalPost.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Th??? c???m x??c n??o v???i postId = " + request.getPostId());
    }
    else {
      post = optionalPost.get();
    }
    
    
    
    reaction.setReaction(request.getReaction());
    reaction.setUser(user);
    reaction.setPost(post);

    //Validate unique
    

    //Update last changed time
    reaction.setLastUpdatedAt(new Date());

    //Store
    this.reactionRepository.save(reaction);

    //Return
    ReactionResponse reactionDTO = new ReactionResponse(reaction);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(reactionDTO);
    response.addMessage("C???p nh???t Th??? c???m x??c th??nh c??ng");

    LOG.info("Updated reaction with id = " + reaction.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteReaction(Integer id) {
    if (!this.reactionRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Th??? c???m x??c n??o v???i id l?? " + id);
    }

    Reaction reaction = this.reactionRepository.findById(id).get();
    reaction.setDeletedAt(new Date());
    
    this.reactionRepository.save(reaction);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a Th??? c???m x??c th??nh c??ng");

    LOG.info("Deleted reaction with id = " + reaction.getId());
    return response;
  }
  
}
  