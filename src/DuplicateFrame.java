import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuplicateFrame extends BasicFrame implements ActionListener{

    Container container = getContentPane();
    JLabel startTimeLabel = new JLabel("start time： yyyy-MM-dd HH:mm");
    JTextField startTimeTextField = new JTextField();
    JButton duplicateButton = new JButton("DUPLICATE");
    JButton backButton = new JButton("BACK");
    private Event event;

    DuplicateFrame(Event event) {
        this.setTitle("Duplicate Event");
        this.setBounds(50, 50, 550, 400);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.event = event;
    }

    private void setLayoutManager(){
        container.setLayout(null);
    }

    private void setLocationAndSize(){
        startTimeLabel.setBounds(50, 100, 250, 30);
        startTimeTextField.setBounds(320, 100, 150, 30);
        duplicateButton.setBounds(100, 200, 100, 30);
        backButton.setBounds(300, 200, 100, 30);
    }

    private void addComponentsToContainer() {
        container.add(startTimeLabel);
        container.add(startTimeTextField);
        container.add(duplicateButton);
        container.add(backButton);
    }

    private void addActionEvent() {
        duplicateButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == duplicateButton) {
            try{
                String startTimeString = startTimeTextField.getText();
                event.duplicate(startTimeString);
                JOptionPane.showMessageDialog(this, "event duplicated succesfully!");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this, "invalid time format!");
                DuplicateFrame df = new DuplicateFrame(event);
            }
            this.dispose();
        }

        if (e.getSource() == backButton) {
            this.dispose();
        }
    }
}
