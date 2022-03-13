package com.tweetapp.repository;

import com.tweetapp.dao.UserLoginCredentialDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginCredentialsRepository extends JpaRepository<UserLoginCredentialDao, String> {

}
