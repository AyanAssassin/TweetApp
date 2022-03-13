package com.tweetapp.service;

import com.tweetapp.dao.TweetDetailsDao;
import com.tweetapp.dao.UserLoginCredentialDao;

import java.util.List;

public interface TweetService {
    public String validateUser(String userId,String password);

    public Boolean logoutUser(String userId);

    public Boolean changePassword(String userId,String newPassword);

    public UserLoginCredentialDao registerUser(String userId,String userName,String password);

    public List<TweetDetailsDao> getAllTweetsByUserId(String userId);

    public Boolean postTweetByUserId(String tweet,String userId);

    public List<UserLoginCredentialDao> getAllUsers();

    public List<TweetDetailsDao> getAllTweets();

}
