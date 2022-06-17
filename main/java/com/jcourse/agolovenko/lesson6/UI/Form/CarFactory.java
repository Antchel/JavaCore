package com.jcourse.agolovenko.lesson6.UI.Form;

import com.jcourse.agolovenko.lesson6.Configurator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CarFactory extends JFrame {
    public JPanel mainPanel;
    public JSlider EnginesProduceSpeed;
    public JSlider CarBodyProduceSpeed;
    public JSlider AccessoriesProduceSpeed;
    public JSlider CarsSellSpeed;
    public JLabel ProducedCars;
    public JLabel EngineStore;
    public JLabel CarBodyStore;
    public JLabel AccessoriesStore;
    public JLabel TasksInQueue;
    public JLabel TotalEnginesProduced;
    public JLabel TotalCarBodyProduced;
    public JLabel TotalAccessoriesProduced;
    public JLabel CarsStore;

    public CarFactory(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        setPreferredSize(new Dimension(1024, 760));
        EnginesProduceSpeed.setValue(Configurator.getCreationItemFreq());
        CarBodyProduceSpeed.setValue(Configurator.getCreationItemFreq());
        AccessoriesProduceSpeed.setValue(Configurator.getCreationItemFreq());
        CarsSellSpeed.setValue(Configurator.getCarSoldFreq());
        this.pack();
    }
}