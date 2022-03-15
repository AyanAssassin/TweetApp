package com.tweetapp;

import com.tweetapp.dao.TweetDetailsDao;
import com.tweetapp.dao.UserLoginCredentialDao;
import com.tweetapp.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

@SpringBootApplication
public class TweetappApplication implements CommandLineRunner {
    @Value("classpath:scripts/reset_password.js")
    private Resource resource1;

    private static Logger LOG = LoggerFactory
            .getLogger(TweetappApplication.class);
    private final TweetService tweetService;

    public TweetappApplication(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TweetappApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        try {
            //showMainMenu();
            runJavaScript(null,null,null,null);
        }
        catch (Exception ex){
            ex.printStackTrace();
            LOG.info(ex.getMessage());
        }
    }

    public void showMainMenu() throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
        String password;
        String userName;
        String userId;
        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> operations = new HashSet<>(Arrays.asList('1', '2', '3','\n','\t'));
        while (true) {
            userId=userName=password="";
            System.out.println("\ti. Register\t\n" +
                    "\tii.Login\t\n" +
                    "\tiii.Forgot Password\t\n");
            //Character s = (char) br.read();
            Character s = sc.nextLine().charAt(0);
            System.out.println("\tYou have entered  :" +s);
            if (!operations.contains(s)) {
                break;
            }
            switch (s) {
                case '1':
                    System.out.println("\tEnter email ID :");
                    userId = sc.nextLine();
                    System.out.println("\tEnter password :");
                    password = sc.nextLine();
                    System.out.println("\tEnter userName :");
                    userName = sc.nextLine();
                    tweetService.registerUser(userId,userName,password);
                    break;
                case '2':
                    System.out.println("\tEnter email ID:");
                    userId = sc.nextLine();
                    System.out.println("\tEnter password:");
                    password = sc.nextLine();
                    userName = tweetService.validateUser(userId, password);
                    if (null != userName) {
                        System.out.println("\tWelcome to Tweety ,"+ userName);
                        showTweetMenu(userId);
                    }
                    break;
                case '3':
                    System.out.println("\tEnter email ID:");
                    userId = sc.nextLine();
                    System.out.println("\tEnter userName:");
                    userName = sc.nextLine();
                    tweetService.forgotPassword(userId,userName);
                    break;
                default:
                    System.out.println("Please enter valid input");
            }


        }
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private void runJavaScript(String userId,String oldPass,String newPass,String confirmPass) throws ScriptException, NoSuchMethodException, IOException, URISyntaxException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");;
        if (!(engine instanceof Invocable)) {
            System.out.println("Invoking methods is not supported.");
            return;
        }
        Invocable inv = (Invocable) engine;
        //ClassLoader classLoader = getClass().getClassLoader();
        //File f=ResourceUtils.getFile("src/main/resources/file.txt");
        //InputStream inputStream = classLoader.getResourceAsStream("file.txt");
        //String data = readFromInputStream(inputStream);
        //System.out.println(System.getProperty("java.class.path"));
        //System.out.println(new String(Files.readAllBytes(Paths.get(resource1.getURI()))));
        //URL fileURL=getClass().getResource("/reset_password.js");
        //System.out.println(fileURL.getPath());
        //File resource = new ClassPathResource("//reset_password.js").getFile();
        //System.out.println(new String(Files.readAllBytes(Paths.get(resource.getAbsolutePath()))));
        String scriptPath = "classpath:scripts//reset_password.js";

        //engine.eval(Files.newBufferedReader(Paths.get("reset_password.js"), StandardCharsets.UTF_8));
        //engine.eval("load('" + Paths.get(resource1.getURI()).toString() + "')");
        //engine.eval("load('" + resource.getPath() + "')");
        engine.put("out", System.out);
        engine.eval("load('" + scriptPath + "')");

        //engine.eval(resource.toString());
        //URL fileUrl = getClass().getResource("scripts\\reset_password.js");
        //engine.eval(Files.newBufferedReader(Paths.get(fileURL.toURI()), StandardCharsets.UTF_8));
        //engine.eval(resource1.getFile());
        //engine.eval(new String(Files.readAllBytes(Paths.get(resource1.getURI()))));

        Object handler = engine.get("handler");

        Object addResult = inv.invokeMethod(handler, "reset", oldPass, newPass,confirmPass);
        if (addResult instanceof Boolean && (Boolean)addResult){
            tweetService.changePassword(userId,oldPass, newPass);
            System.out.println("Password successfully reset");
            return;
        }
        System.out.println("Cannot reset password");
    }

    public void showTweetMenu(String userId) throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
        String tweet;

        Scanner sc = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> operations = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6','\n','\t'));
        while (true) {
            tweet="";
            System.out.println("\ti. Post a tweet\n" +
                    "\tii. View my tweets\t\n" +
                    "\tiii. View all tweets\t\n" +
                    "\tiv. View All Users\t\n" +
                    "\tv. Reset Password\t\n" +
                    "\tvi. Logout\t");
            //Character s = (char) br.read();
            Character s = sc.nextLine().charAt(0);
            if (!operations.contains(s)) {
                break;
            }
            switch (s) {
                case '1':
                    System.out.println("\tEnter tweet:");
                    tweet = sc.nextLine();
                    if (tweetService.postTweetByUserId(tweet, userId)) {
                        System.out.println("Tweet created successfully");
                    } else {
                        System.out.println("Cannot create tweet");
                    }

                    break;
                case '2':
                    System.out.println("\tTweets on your userId are:");
                    List<TweetDetailsDao> userTweetDetails = tweetService.getAllTweetsByUserId(userId);
                    if (!CollectionUtils.isEmpty(userTweetDetails)) {
                        userTweetDetails.forEach(tweetDao ->
                                System.out.println("\t" + tweetDao.getTweetData())
                        );
                    }
                    break;
                case '3':
                    System.out.println("\tDisplaying all tweets on database:");
                    List<TweetDetailsDao> tweetDetails = tweetService.getAllTweets();
                    if (!CollectionUtils.isEmpty(tweetDetails)) {
                        tweetDetails.forEach(tweetDao ->
                                System.out.println("\t" + tweetDao.getTweetData())
                        );
                    }
                    break;
                case '4':
                    System.out.println("\tDisplaying all users on database:");
                    List<UserLoginCredentialDao> userDetails = tweetService.getAllUsers();
                    if (!CollectionUtils.isEmpty(userDetails)) {
                        userDetails.forEach(userDao ->
                                System.out.println("\t" + userDao)
                        );
                    }
                    break;
                case '5':
                    System.out.println("\tEnter old password:");
                    String oldPass = sc.nextLine();
                    System.out.println("\tEnter new password:");
                    String newPass = sc.nextLine();
                    System.out.println("\tConfirm new password:");
                    String confirmPass = sc.nextLine();
                    runJavaScript(userId,oldPass,newPass,confirmPass);
                    break;
                case '6':
                    if (tweetService.logoutUser(userId)) {
                        System.out.println("User " + userId + "successfully logged out");
                    }
                    System.out.println("Cannot logout user with userId : " + userId);
                    break;
                default:
            }
        }
    }


}
