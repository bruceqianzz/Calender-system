import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class CreateFrequencyEventsFrame extends AddEventFrame implements ActionListener {
    JLabel frequencyLabel = new JLabel("Frequency of the event");
    JLabel formatLabel = new JLabel("Repeat of times");
    JTextField frequencyTextField = new JTextField();
    JLabel hoursApartLabel = new JLabel("How many hours between two adjacent events");
    JTextField hoursApartTextField = new JTextField();
    String seriesName;

    CreateFrequencyEventsFrame(String seriesName){
        super();
        this.setTitle("Create recurring events for the series you select");
        this.seriesName = seriesName;

        frequencyLabel.setBounds(50, 400, 150, 20);
        frequencyTextField.setBounds(210, 400, 40, 25);
        formatLabel.setBounds(255, 400, 150, 25);
        container.add(frequencyLabel);
        container.add(frequencyTextField);
        container.add(formatLabel);

        hoursApartLabel.setBounds(50, 430, 300, 20);
        hoursApartTextField.setBounds(360, 430, 40, 25);
        container.add(hoursApartLabel);
        container.add(hoursApartTextField);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
        if (e.getSource() == backButton){
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(new FileOutputStream(CalendarFacade.getCurrentUser().getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                assert os != null;
                os.writeObject(CalendarFacade.getCurrentUser());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();


        }

        if(e.getSource() == addButton){
            try {
            String name = nameTextField.getText();
            String startTime = dateTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());
            String address = addressTextField.getText();
            int fre = Integer.parseInt(frequencyTextField.getText());
            int hours = Integer.parseInt(hoursApartTextField.getText());


//                CalendarFacade.createFrequencyEvent(seriesName, name, startTime, duration,
//                        address, fre, hours);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime t = LocalDateTime.parse(startTime, formatter);

                LocalDateTime time;
                for (int i = 0; i < fre; i++) {
                    int h = hours * i;
                    time = t.plusHours(h);
                    Event event = new Event(name, time.toString().replace("T", " "), duration, address);


                    CalendarFacade.getCurrentCalendar().addEvent(event);
                    CalendarFacade.addToSeries(seriesName, event);
                }

                JOptionPane.showMessageDialog(this, "Events added!");
                nameTextField.setText("");
                dateTextField.setText("");
                durationTextField.setText("");
                addressTextField.setText("");
                frequencyTextField.setText("");
                hoursApartTextField.setText("");
            } catch (Exception e1){
                JOptionPane.showMessageDialog(this, "Wrong input!");
            }
            nameTextField.setText("");
            dateTextField.setText("");
            durationTextField.setText("");
            addressTextField.setText("");
            frequencyTextField.setText("");
            hoursApartTextField.setText("");
            this.dispose();
        }
    }





    }

