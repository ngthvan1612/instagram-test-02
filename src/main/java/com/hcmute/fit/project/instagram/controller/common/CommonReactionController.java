package com.hcmute.fit.project.instagram.controller.common;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.CreateReactionRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.GetReactionResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.ListReactionResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.reaction.UpdateReactionRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces.ReactionService;
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
@RequestMapping("api/common/reaction")
public class CommonReactionController {

    @Autowired
    private ReactionService reactionService;

    public CommonReactionController() {

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract searchReaction(
            @RequestParam Map<String, String> queries
    ) {
        ListReactionResponse listReactionResponse = this.reactionService.searchReactions(queries);
        return listReactionResponse;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract getReaction(
            @PathVariable Integer id
    ) {
        GetReactionResponse getReactionResponse = this.reactionService.getReactionById(id);
        return getReactionResponse;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBaseAbstract createReaction(
            @RequestBody @Valid CreateReactionRequest request
    ) {
        SuccessfulResponse createReactionResponse = this.reactionService.createReaction(request);
        return createReactionResponse;
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract updateReaction(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateReactionRequest request
    ) {
        request.setReactionId(id);
        SuccessfulResponse updateReactionResponse = this.reactionService.updateReaction(request);
        return updateReactionResponse;
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseBaseAbstract deleteReaction(
            @PathVariable Integer id
    ) {
        SuccessfulResponse updateReactionResponse = this.reactionService.deleteReaction(id);
        return updateReactionResponse;
    }
}
