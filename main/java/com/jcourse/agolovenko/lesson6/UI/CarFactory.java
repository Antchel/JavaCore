package com.jcourse.agolovenko.lesson6.UI;

import com.jcourse.agolovenko.lesson6.Configurator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CarFactory extends JFrame{
    private JPanel mainPanel;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JTextField enginesManufacturingSpeedTextField;
    private JTextField carBodyManufacturingSpeedTextField;
    private JTextField accessoriesManufacturingSpeedTextField;
    private JTextField textField4;
    private JTextArea textArea1;
    private JTextPane textPane1;

    public CarFactory(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        setPreferredSize(new Dimension(1024, 760));
        getSlider1().setValue(Configurator.getCreationItemFreq());
        this.pack();


        getSlider1().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                syncSlider(getSlider1(), Configurator.getCreationItemFreq());

            }
        });
    }

    public void syncSlider(JSlider slider, int value){
        System.out.println(slider.getValue());

//        slider.setValue(value);
    }

    public JSlider getSlider1() {
        return slider1;
    }
}
