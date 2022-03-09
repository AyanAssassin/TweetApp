package com.tweetapp.service;

import com.tweetapp.dao.TweetDetailsDao;
import com.tweetapp.dao.UserLoginCredentialDao;

import java.util.List;

public interface TweetService {
    public Boolean validateUser(String userId,String password);

    public String changePassword(String userId,String newPassword);

    public UserLoginCredentialDao registerUser();

    public List<TweetDetailsDao> getAllTweetsByUserId(String userId);

    public List<UserLoginCredentialDao> getAllUsers();

    public List<TweetDetailsDao> getAllTweets();

}
