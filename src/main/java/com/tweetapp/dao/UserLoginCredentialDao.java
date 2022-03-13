package com.tweetapp.dao;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "users")
public class UserLoginCredentialDao {
    @Id
    @Column(name = "user_id")
    private String userId;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created;
    @Column(name = "last_logout")
    private Timestamp lastLogout;
}