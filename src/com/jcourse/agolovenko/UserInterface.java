package com.jcourse.agolovenko;

import java.util.Scanner;

public class UserInterface {
    public static int userInput;
    UserInterface() {
        System.out.println(" Let's play in Magic number game.\n You have 8 attempts to guess the magic number");
    }
    int GetUserInput() {
        System.out.println("Enter your number ");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            userInput = console.nextInt();
            if (userInput > 100 || userInput < 1) {
                System.out.println("Wrong range! Hint: the number is places between 1 and 100");
            }
        } else {
            console.nextLine();
            userInput = Integer.MAX_VALUE;
            System.out.println("Incorrect input format, please try again!");
        }
        return userInput;
    }
    void SendNotify(String notification) {
        System.out.println(notification);
    }
}
