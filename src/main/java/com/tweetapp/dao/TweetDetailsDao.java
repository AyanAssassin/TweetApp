package com.tweetapp.dao;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @NotNull
    @Column(name = "user_id")
    private String userId;
    @NotNull
    @Column(name = "tweet_data")
    private String tweetData;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated;
}
