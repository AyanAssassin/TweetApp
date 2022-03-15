package com.tweetapp.service;

import com.tweetapp.dao.TweetDetailsDao;
import com.tweetapp.dao.UserLoginCredentialDao;
import com.tweetapp.repository.TweetDetailsRepository;
import com.tweetapp.repository.UserLoginCredentialsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TweetServiceImpl implements TweetService {
    private final TweetDetailsRepository tweetDetailsRepository;
    private final UserLoginCredentialsRepository userLoginCredentialsRepository;

    public TweetServiceImpl(TweetDetailsRepository tweetDetailsRepository, UserLoginCredentialsRepository userLoginCredentialsRepository) {
        this.tweetDetailsRepository = tweetDetailsRepository;
        this.userLoginCredentialsRepository = userLoginCredentialsRepository;
    }


    @Override
    public String validateUser(String userId, String password) {
        if (null != userId && null != password) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(userId);
            if (credentialDao.isPresent()) {
                if (credentialDao.get().getPassword().equals(password)) {
                    return credentialDao.get().getUserName();
                } else {
                    System.out.println("Invalid password");
                }
            } else {
                System.out.println("Invalid userName");
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean logoutUser(String userId) {
        if (null != userId) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(userId);
            if (credentialDao.isPresent()) {
                credentialDao.get().setLastLogout(Timestamp.from(Instant.now()));
                userLoginCredentialsRepository.save(credentialDao.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean changePassword(String userId, String oldPassword,String newPassword) {
        if (null != userId && null != newPassword) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(userId);
            if (credentialDao.isPresent()) {
                if (credentialDao.get().getPassword().equals(oldPassword)) {
                    credentialDao.get().setPassword(newPassword);
                    userLoginCredentialsRepository.save(credentialDao.get());
                    System.out.println("\tPassword successfully changed");
                    return true;
                } else {
                    System.out.println("\tInvalid password");
                }

            }

        }
        System.out.println("\tPassword cannot be reset");
        return false;
    }


    @Override
    public Boolean forgotPassword(String userId,String userName) {
        if (null != userId && null != userName) {
            Optional<UserLoginCredentialDao> credentialDao = userLoginCredentialsRepository.findById(userId);
            if (credentialDao.isPresent()) {
                if (credentialDao.get().getUserName().equals(userName)) {
                    System.out.println(credentialDao.get().getPassword());
                    return true;
                } else {
                    System.out.println("Invalid userName");
                }
            } else {
                System.out.println("Invalid user ID");
            }
        }
        return false;
    }

    @Override
    @Transactional
    public UserLoginCredentialDao registerUser(String userId, String userName, String password) {
        if (validateEmail(userId) && null != password && null != userName && null != userId) {
            if (userLoginCredentialsRepository.findById(userId).isPresent()) {
                System.out.println("\tUser with email ID already registered!!\n\tPlease try to reset password or change it!!");
                return null;
            }
            UserLoginCredentialDao credentialDao = new UserLoginCredentialDao();
            credentialDao.setUserId(userId);
            credentialDao.setPassword(password);
            credentialDao.setUserName(userName);
            userLoginCredentialsRepository.save(credentialDao);
            System.out.println("\tUser with email ID successfully registered!!");
        }
        return null;
    }

    private Boolean validateEmail(String emailId) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(emailId);
        //Verifying whether given email ID is valid
        if (matcher.matches()) {
            System.out.println("\tGiven email id is valid");
            return true;
        } else {
            System.out.println("\tGiven email id is not valid");
            return false;
        }
    }

    @Override
    public List<TweetDetailsDao> getAllTweetsByUserId(String userId) {
        return tweetDetailsRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Boolean postTweetByUserId(String tweet, String userId) {
        if (null != tweet && null != userId) {
            TweetDetailsDao tweetDetailsDao = new TweetDetailsDao();
            tweetDetailsDao.setTweetData(tweet);
            tweetDetailsDao.setUserId(userId);
            tweetDetailsRepository.save(tweetDetailsDao);
            return true;
        }
        return false;
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
