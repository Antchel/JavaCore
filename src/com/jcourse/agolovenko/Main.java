package com.jcourse.agolovenko;

import com.jcourse.agolovenko.lesson1.Echo;
import com.jcourse.agolovenko.lesson1.MagicNumber;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Echo echo = new Echo();
        echo.executeEcho();

        MagicNumber game = new MagicNumber();
        game.startGame();

    }
}
