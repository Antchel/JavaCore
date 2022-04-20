package com.jcourse.agolovenko.lesson1;

public class Magicnumber {
    public static final int LOWER_LIMIT = 1;
    public static final int UPPER_LIMIT = 100;
    public static final int ATTEMPTS_LIMIT = 8;
    private int attemptsCount = 0;
    private boolean IsRight = false;
    private final int magicNumber = (int) (Math.random() * UPPER_LIMIT) + LOWER_LIMIT;

    /**
     * Begin the guessing magic number game.
     */
    public void StartGame() {
        UserInterface UI = new UserInterface();
        while (attemptsCount < ATTEMPTS_LIMIT && !IsRight) {
            attemptsCount++;

            int userInput = UI.GetUserInput();
            int compareResult = Integer.compare(userInput, magicNumber);

            UI.SendNotifyByCode(compareResult);

            if (compareResult == 0)
                IsRight = true;

        }
        if (attemptsCount == ATTEMPTS_LIMIT) {
            UI.SendNotifyLoseGame(magicNumber);
        }
    }
}