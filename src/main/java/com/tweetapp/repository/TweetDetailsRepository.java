package com.tweetapp.repository;

import com.tweetapp.dao.TweetDetailsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetDetailsRepository extends JpaRepository<TweetDetailsDao, Integer> {
    List<TweetDetailsDao> findByUserId(String userId);
}
