package com.hcmute.fit.project.instagram.controller.common;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.CreateMessageRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.GetMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.ListMessageResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.UpdateMessageRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces.MessageService;
import com.hcmute.fit.project.instagram.domain.base.ResponseBaseAbstract;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
//hoi lai, camelcase hay la a-a-a
@RequestMapping("api/common/message")
public class CommonMessageController {

  @Autowired
  private MessageService messageService;

  public CommonMessageController() {

  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract searchMessage(
    @RequestParam Map<String, String> queries
  ) {
    ListMessageResponse listMessageResponse = this.messageService.searchMessages(queries);
    return listMessageResponse;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract getMessage(
    @PathVariable Integer id
  ) {
    GetMessageResponse getMessageResponse = this.messageService.getMessageById(id);
    return getMessageResponse;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseBaseAbstract createMessage(
    @RequestBody @Valid CreateMessageRequest request
  ) {
    SuccessfulResponse createMessageResponse = this.messageService.createMessage(request);
    return createMessageResponse;
  }
  
  @PutMapping("{id}/update")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract updateMessage(
    @PathVariable Integer id,
    @RequestBody @Valid UpdateMessageRequest request
  ) {
    request.setMessageId(id);
    SuccessfulResponse updateMessageResponse = this.messageService.updateMessage(request);
    return updateMessageResponse;
  }

  @DeleteMapping("{id}/delete")
  @ResponseStatus(HttpStatus.OK)
  public ResponseBaseAbstract deleteMessage(
    @PathVariable Integer id
  ) {
    SuccessfulResponse updateMessageResponse = this.messageService.deleteMessage(id);
    return updateMessageResponse;
  }
}
