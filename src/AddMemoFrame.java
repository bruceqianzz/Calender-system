import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class AddMemoFrame extends BasicFrame implements ActionListener {

    JButton addButton = new JButton("add");
    JButton backButton = new JButton("back");

    Container container = getContentPane();
    JLabel nameLabel = new JLabel("MEMO NAME");
    JTextField nameTextField = new JTextField();
    JLabel contentLabel = new JLabel("MEMO CONTENT");
    JTextField contextText = new JTextField();
    Event event;


    AddMemoFrame(Event event) {
        this.event = event;
        this.setTitle("Add New Memo");
        this.setBounds(10, 10, 500, 400);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 50, 100, 30);
        nameTextField.setBounds(50, 80, 200, 30);
        contentLabel.setBounds(50, 180, 150, 30);
        contextText.setBounds(50, 210, 200, 30);

        addButton.setBounds(100, 350, 100, 30);
        backButton.setBounds(300, 350, 100, 30);

    }

    private void addComponentsToContainer() {
        container.add(addButton);
        container.add(backButton);
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(contentLabel);
        container.add(contextText);
    }

    public void addActionEvent() {
        addButton.addActionListener(this);
        backButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of add and back button
        if (e.getSource() == backButton){
            EditMemoInEventFrame ef = new EditMemoInEventFrame(event);
            this.dispose();
        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            String content = contextText.getText();
            Memo mm = CalendarFacade.getCurrentCalendar().createMemo(name, content);
            mm.addEvent(event);
            event.addMemo(mm);
            JOptionPane.showMessageDialog(this, "Memo added!");
            nameTextField.setText("");
            contextText.setText("");
        }

    }






}

