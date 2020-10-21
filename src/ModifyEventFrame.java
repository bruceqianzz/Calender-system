import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ModifyEventFrame extends InfoEventFrame implements ActionListener {

    JButton modButton = new JButton("MODIFY");
    private Event event;

    ModifyEventFrame(Event event) {
        super();
        this.event = event;
        this.setTitle("Modify Event");
        modButton.setBounds(300, 500, 100, 30);
        container.add(modButton);
        modButton.addActionListener(this);
        nameTextField.setText(event.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String start = event.getStartTime().format(formatter);
        dateTextField.setText(start);
        durationTextField.setText(Integer.toString(event.getDuration()));
        addressTextField.setText(event.getAddress());


    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent(){
        return this.event;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of go back button
        if (e.getSource() == backButton){
            this.dispose();
            EditEventFrame ed = new EditEventFrame(event);

        }

        if(e.getSource() == modButton){
            String name = nameTextField.getText();
            String startTime = dateTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());
            String address = addressTextField.getText();
            this.event.setName(name);
            this.event.setStartTime(startTime);
            this.event.setDuration(duration);
            this.event.setAddress(address);
            try {
                this.event.updateSharedEvent();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Event Modified!");
            this.dispose();
            EditEventFrame nf = new EditEventFrame(event);
        }
    }
}
