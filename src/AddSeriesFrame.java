import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class AddSeriesFrame extends BasicFrame implements ActionListener {

    JButton addButton = new JButton("add");
    JButton backButton = new JButton("back");

    Container container = getContentPane();
    JLabel nameLabel = new JLabel("SERIES NAME");
    JTextField nameTextField = new JTextField();




    AddSeriesFrame() {
        this.setTitle("Add New Series");
        this.setBounds(10, 10, 500, 600);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

//        addButton.addActionListener(this);
//        backButton.addActionListener(this);

    }
    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(50, 150, 100, 30);

        nameTextField.setBounds(150, 150, 150, 30);

        addButton.setBounds(100, 500, 100, 30);
        backButton.setBounds(300, 500, 100, 30);

    }

    private void addComponentsToContainer() {
        container.add(addButton);
        container.add(backButton);
        container.add(nameLabel);
        container.add(nameTextField);
    }

    public void addActionEvent() {
        addButton.addActionListener(this);
        backButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding part of add and back button
        if (e.getSource() == backButton){
            this.dispose();
        }

        if(e.getSource() == addButton){
            String name = nameTextField.getText();
            // we store currentCalendar in CalendarFacade now  --- by Oliver
            CalendarFacade.getCurrentCalendar().createSeries(name);
            JOptionPane.showMessageDialog(this, "Series added!");


            nameTextField.setText("");

            ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();
            String[] seriesString = new String[series.size()];
            for (int j = 0; j < series.size(); j++) {
                seriesString[j] = series.get(j).getName();
            }
            SeriesFrame.seriesList.setListData(seriesString);


        }

    }






}

