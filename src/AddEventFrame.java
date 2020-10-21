import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddEventFrame extends InfoEventFrame implements ActionListener {

    JButton addButton = new JButton("ADD");


    AddEventFrame() {
        super();
        this.setTitle("Add New Event");
        addButton.setBounds(300, 500, 100, 30);
        container.add(addButton);
        addButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
        if (e.getSource() == backButton){
            this.dispose();


        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            String startTime = dateTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());
            String address = addressTextField.getText();
            // we store currentCalendar rather than currentUser now --- by Oliver
            try {
                CalendarFacade.getCurrentCalendar().createEvent(name, startTime, duration, address);
                JOptionPane.showMessageDialog(this, "Event added!");
                nameTextField.setText("");
                dateTextField.setText("");
                durationTextField.setText("");
                addressTextField.setText("");
            } catch (Exception e1){
                JOptionPane.showMessageDialog(this, "Wrong input!");
            }
            nameTextField.setText("");
            dateTextField.setText("");
            durationTextField.setText("");
            addressTextField.setText("");
        }
    }
}

