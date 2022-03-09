package com.tweetapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TweetappApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(TweetappApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TweetappApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }

    public void showMainMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> operations = new HashSet<>(Arrays.asList('1', '2', '3'));
        while (true) {
            System.out.println("\ti. Register\t\n" +
                    "\tii.Login\t\n" +
                    "\tiii.Forgot Password\t\n");
            Character s = (char) br.read();
            if (!operations.contains(s)) {
                break;
            }
            switch (s) {
                case '1':

                    break;
                case '2':
                    break;
                case '3':
                    break;
                default:


            }


        }
    }

    public void showTweetMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> operations = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6'));
        while (true) {
            System.out.println("\ti. Post a tweet\n" +
                    "\tii. View my tweets\t\n" +
                    "\tiii. View all tweets\t\n" +
                    "\tiv. View All Users\t\n" +
                    "\tv. Reset Password\t\n" +
                    "\tvi. Logout\t");
            Character s = (char) br.read();
            switch (s) {
                case '1':


            }
        }
    }


}
