//import sun.jvm.hotspot.debugger.bsd.amd64.BsdAMD64CFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class InfoEventFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JLabel nameLabel = new JLabel("EVENT NAME");
    JLabel dateLabel = new JLabel("START TIME");
    JLabel durationLabel = new JLabel("DURATION");
    JLabel formatLabel = new JLabel("YYYY-MM-DD HH:MM");
    JLabel inMinuteLabel = new JLabel("In Minutes");
    JLabel addressLabel = new JLabel("ADDRESS");
    JTextField nameTextField = new JTextField();
    JTextField dateTextField = new JTextField();
    JTextField durationTextField = new JTextField();
    JTextField addressTextField = new JTextField();
    JButton backButton = new JButton("BACK");


    InfoEventFrame() {
        this.setBounds(10, 10, 500, 600);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 150, 100, 30);
        dateLabel.setBounds(50, 220, 100, 30);
        nameTextField.setBounds(150, 150, 150, 30);
        dateTextField.setBounds(150, 220, 150, 30);
        formatLabel.setBounds(320, 220, 200, 30);
        durationLabel.setBounds(50, 290, 100, 30);
        inMinuteLabel.setBounds(320, 290, 200, 30);
        durationTextField.setBounds(150, 290, 150, 30);
        addressLabel.setBounds(50, 360, 100, 30);
        addressTextField.setBounds(150, 360, 150, 30);
        backButton.setBounds(50, 500, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(durationLabel);
        container.add(dateLabel);
        container.add(formatLabel);
        container.add(addressLabel);
        container.add(backButton);
        container.add(nameTextField);
        container.add(addressTextField);
        container.add(durationTextField);
        container.add(dateTextField);
        container.add(inMinuteLabel);


    }

    public void addActionEvent() {
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
        if (e.getSource() == backButton){
            this.dispose();


        }
    }
}

