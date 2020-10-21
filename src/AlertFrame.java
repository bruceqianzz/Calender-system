import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AlertFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JLabel dateLabel = new JLabel("START TIME");
    JLabel countLabel = new JLabel("REPEAT TIMES");
    JLabel formatLabel = new JLabel("YYYY-MM-DD HH:MM");
    JLabel repeatLabel = new JLabel("REPEAT CYCLE");
    JTextField dateTextField = new JTextField();
    JTextField countTextField = new JTextField();
    JTextField repeatTextField = new JTextField();
    JButton backButton = new JButton("BACK");
    JButton addButton = new JButton("ADD");
    JLabel unitLabel = new JLabel("MINUTE/HOUR/DAY/WEEK/MONTH/YEAR/ONETIME");
    private Event event;


    AlertFrame(Event event) {
        this.event = event;
        this.setBounds(10, 10, 700, 600);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        dateLabel.setBounds(50, 220, 100, 30);
        dateTextField.setBounds(150, 220, 150, 30);
        formatLabel.setBounds(320, 220, 200, 30);
        countLabel.setBounds(50, 290, 100, 30);
        repeatLabel.setBounds(50, 360, 200, 30);
        unitLabel.setBounds(250, 360, 350,30);
        countTextField.setBounds(150, 290, 150, 30);
        repeatTextField.setBounds(150, 360, 80, 30);
        backButton.setBounds(50, 500, 100, 30);
        addButton.setBounds(150, 500, 100, 30);

    }

    public void addComponentsToContainer() {
        container.add(dateLabel);
        container.add(formatLabel);
        container.add(addButton);
        container.add(backButton);
        container.add(repeatTextField);
        container.add(repeatLabel);
        container.add(countTextField);
        container.add(dateTextField);
        container.add(countLabel);
        container.add(unitLabel);


    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        addButton.addActionListener(this);
    }

    public static Unit getUnit(String unit) throws Exception{
        switch (unit){
            case "MINUTE":{
                return Unit.MINUTE;
            }
            case "HOUR":{
                return Unit.HOUR;
            }
            case "DAY":{
                return Unit.DAY;
            }
            case "WEEK":{
                return Unit.WEEK;
            }
            case "MONTH":{
                return Unit.MONTH;
            }
            case "YEAR":{
                return Unit.YEAR;
            }
            case "ONETIME":{
                return Unit.ONETIME;
            }
            default:{
                throw new Exception();
            }

        }
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
            EditEventFrame ed = new EditEventFrame(event);
        }
        if(e.getSource()==addButton){
            String startTime = dateTextField.getText();
            int rep = Integer.parseInt(countTextField.getText());
            String unit = repeatTextField.getText();
            try {
                Unit u = getUnit(unit);
                event.setAlert(startTime, rep, u);
                JOptionPane.showMessageDialog(this, "Alert added!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"Invalid Format");
            } catch (Exception ignored){

            }

            dateTextField.setText("");
            repeatTextField.setText("");
            countTextField.setText("");
        }
    }
}

