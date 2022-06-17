package com.jcourse.agolovenko.lesson6.UI.Controllers;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;
import com.jcourse.agolovenko.lesson6.Vendors.Vendor;

import javax.swing.*;

public class VendorsSliderController {
    public static void assignListener(JSlider slider, Vendor<? extends IStorageItem> producer) {
        slider.addChangeListener(e -> {
            int delayInMs = slider.getValue();
            if (!slider.getValueIsAdjusting())
                producer.runWithRate((delayInMs > 0) ? delayInMs : 1);
        });
    }
}