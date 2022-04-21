package com.jcourse.agolovenko;

import com.jcourse.agolovenko.lesson1.Echo;
import com.jcourse.agolovenko.lesson1.MagicNumber;
import com.jcourse.agolovenko.lesson1.UserInterface;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Echo echo = new Echo();
        echo.executeEcho();

        UserInterface UI = new UserInterface();
        MagicNumber game = new MagicNumber(UI);
        game.startGame();

    }
}
