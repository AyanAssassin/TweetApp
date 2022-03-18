package com.tweetapp.utility;

import java.util.Scanner;

public class PasswordField {

    /**
     * @param prompt The prompt to display to the user
     * @return The password as entered by the user
     */
    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);
        mask.start();
        Scanner in = new Scanner(System.in);
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";
        password = in.nextLine();
        // stop masking
        et.stopMasking();
        System.out.println("Password is " + password);
        // return the password entered by the user
        return password;
    }
}