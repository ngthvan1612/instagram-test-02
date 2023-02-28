package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.enums.PostPrivacy;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt")
    private Date createdAt = new Date();

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    private PostPrivacy privacy;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;


    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<UserTagFriendPost> userTagFriendPosts;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Reaction> reactions;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JsonIgnore
    //private Collection<Comment> comments;

}
