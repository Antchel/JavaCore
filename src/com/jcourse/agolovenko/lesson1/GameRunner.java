package com.jcourse.agolovenko.lesson1;

import java.io.*;

public class GameRunner {

    public static void main(String[] args) throws IOException {

        Echo echo = new Echo();
        echo.executeEcho();

        UserInterface UI = new UserInterface();
        MagicNumber game = new MagicNumber(UI);
        game.startGame();

    }
}
