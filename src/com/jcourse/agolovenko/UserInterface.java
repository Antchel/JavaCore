package com.jcourse.agolovenko;

import java.util.Scanner;

public class UserInterface {
    public static int userInput;

    int GetUserInput() {
        System.out.println("Enter your number ");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            userInput = console.nextInt();
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
