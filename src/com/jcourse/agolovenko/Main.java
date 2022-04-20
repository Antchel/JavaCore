package com.jcourse.agolovenko;

import com.jcourse.agolovenko.lesson1.Echo;
import com.jcourse.agolovenko.lesson1.Magicnumber;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Echo echo = new Echo();
        echo.ExecuteEcho();

        Magicnumber game = new Magicnumber();
        game.StartGame();

    }
}
