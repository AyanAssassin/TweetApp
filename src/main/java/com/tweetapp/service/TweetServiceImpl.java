package com.tweetapp.service;

import com.tweetapp.dao.TweetDetailsDao;
import com.tweetapp.dao.UserLoginCredentialDao;
import com.tweetapp.repository.TweetDetailsRepository;
import com.tweetapp.repository.UserLoginCredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {
    private final TweetDetailsRepository tweetDetailsRepository;
    private final UserLoginCredentialsRepository userLoginCredentialsRepository;

    public TweetServiceImpl(TweetDetailsRepository tweetDetailsRepository, UserLoginCredentialsRepository userLoginCredentialsRepository) {
        this.tweetDetailsRepository = tweetDetailsRepository;
        this.userLoginCredentialsRepository = userLoginCredentialsRepository;
    }


    @Override
    public Boolean validateUser(String userId, String password) {
        if (null != userId && null != password) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(Integer.parseInt(userId));
            if (credentialDao.isPresent()) {
                return credentialDao.get().getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public String changePassword(String userId,String newPassword) {
        if (null != userId && null != newPassword) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(Integer.parseInt(userId));
            if (credentialDao.isPresent()) {
                credentialDao.get().setPassword(newPassword);
                userLoginCredentialsRepository.save(credentialDao.get());
                System.out.println("Password cannot be reset");
            }

        }
        System.out.println("Password cannot be reset");
        return null;
    }

    @Override
    public UserLoginCredentialDao registerUser() {

        return null;
    }

    @Override
    public List<TweetDetailsDao> getAllTweetsByUserId(String userId) {
        return tweetDetailsRepository.findByUserId(Integer.parseInt(userId));
    }

    @Override
    public List<UserLoginCredentialDao> getAllUsers() {
        return userLoginCredentialsRepository.findAll();
    }

    @Override
    public List<TweetDetailsDao> getAllTweets() {
        return tweetDetailsRepository.findAll();
    }
}
