package com.tweetapp.dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tweets")
public class TweetDetailsDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private Integer tweetId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "tweet_data")
    private String tweetData;
    @Column(name = "created_at")
    private Timestamp created;
    @Column(name = "updated_at")
    private Timestamp updated;
}
