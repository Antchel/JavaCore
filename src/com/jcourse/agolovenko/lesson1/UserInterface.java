package com.jcourse.agolovenko.lesson1;

import java.util.Scanner;

import static com.jcourse.agolovenko.lesson1.Magicnumber.*;

public class UserInterface {
    public static int userInput;

    /**
     * Notify user that game has been started.
     */
    UserInterface() {
        System.out.println(" Let's play in Magic number game.\n You have " + ATTEMPTS_LIMIT + " attempts to guess the magic number");
    }

    /**
     * This method return a number, which user enter from console
     *
     * @return User number.
     */
    public int GetUserInput() {
        System.out.println("Enter your number ");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            userInput = console.nextInt();
            if (userInput > UPPER_LIMIT || userInput < LOWER_LIMIT) {
                System.out.println("Wrong range! Hint: the number is places between 1 and 100");
            }
        }
        return userInput;
    }

    /**
     * This method notify user about equal his number to magic number or not.
     *
     * @param code You should pass here Integer.compare() result
     */
    public void SendNotifyByCode(int code) {
        switch (code) {
            case 1 -> System.out.println("Magic number is less");
            case -1 -> System.out.println("Magic number is greater");
            case 0 -> System.out.println("Congratulation. You are won!");
        }
    }

    /**
     * Notify User that it loose the game
     *
     * @param magicNumber Pass a number which user should guess
     */
    public void SendNotifyLoseGame(int magicNumber) {
        System.out.println("You are loose. Magic number was " + magicNumber);
    }
}
