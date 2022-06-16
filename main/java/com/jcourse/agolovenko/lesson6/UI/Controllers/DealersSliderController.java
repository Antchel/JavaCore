package com.jcourse.agolovenko.lesson6.UI.Controllers;

import com.jcourse.agolovenko.lesson6.Dealers.CarDealer;

import javax.swing.*;

public class DealersSliderController {
    public static void assignListener(JSlider slider, CarDealer carDealer) {
        slider.addChangeListener(e -> {
            int delayInMs = slider.getValue();
            if (!slider.getValueIsAdjusting())
                carDealer.runThreadsWithDelay((delayInMs > 0) ? delayInMs : 1);
        });
    }
}
