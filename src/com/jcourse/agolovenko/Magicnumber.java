package com.jcourse.agolovenko;

public class Magicnumber {
    private final int LOWER_LIMIT = 1;
    private final int UPPER_LIMIT = 100;
    private int attemptsCount = 0;
    private boolean IsRight = false;
    private final int magicNumber = (int) (Math.random() * UPPER_LIMIT) + LOWER_LIMIT;

    public void StartGame() {
        UserInterface UI = new UserInterface();
        while (attemptsCount < 8 && !IsRight) {
            attemptsCount++;
            int userInput = UI.GetUserInput();
            if (userInput < magicNumber) {
                UI.SendNotify("Magic number greater than " + userInput);
            } else if (userInput > magicNumber) {
                UI.SendNotify("Magic number less than " + userInput);
            } else {
                IsRight = true;
                UI.SendNotify("Congratulations You are guess. Magic number is " + magicNumber);
            }
        }
        if (attemptsCount == 8) {
            UI.SendNotify("You are not guess. Magic number was " + magicNumber);
        }
    }
}
