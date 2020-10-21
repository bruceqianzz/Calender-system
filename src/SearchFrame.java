import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;     // Using Swing components and containers

public class SearchFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JButton tagButton = new JButton("BY TAG");
    JButton dateButton = new JButton("BY DATE");
    JButton memoButton = new JButton("BY MEMO");
    JButton seriesButton = new JButton("BY SERIES");
    JButton eventButton = new JButton("BY EVENT");
    JButton backButton = new JButton("BACK");
    JButton goButton = new JButton("GO");
    JLabel tagLabel = new JLabel("tag");
    JLabel dateLabel = new JLabel("date (yyyy-MM-dd)");
    JLabel memoLabel = new JLabel("memo");
    JLabel seriesLabel = new JLabel("series");
    JLabel eventLabel = new JLabel("event");
    JTextField tagTextField = new JTextField();
    JTextField dateTextField = new JTextField();
    JTextField memoTextField = new JTextField();
    JTextField seriesTextField = new JTextField();
    JTextField eventTextField = new JTextField();
    JList<String> eventList = new JList<>();
    ArrayList<Event> events = new ArrayList<>();

    SearchFrame() {
        this.setTitle("Search");
        this.setBounds(10, 10, 800, 600);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    private void setLayoutManager() {
        container.setLayout(null);
    }


    private void setLocationAndSize() {
        tagLabel.setBounds(50, 100, 80, 30);
        dateLabel.setBounds(50, 140, 200, 30);
        memoLabel.setBounds(50, 180, 80, 30);
        seriesLabel.setBounds(50, 220, 80, 30);
        eventLabel.setBounds(50, 260, 80, 30);
        tagTextField.setBounds(260, 100, 150, 30);
        dateTextField.setBounds(260, 140, 150, 30);
        memoTextField.setBounds(260, 180, 150, 30);
        seriesTextField.setBounds(260, 220, 150, 30);
        eventTextField.setBounds(260, 260, 150, 30);
        tagButton.setBounds(420, 100, 100, 30);
        dateButton.setBounds(420, 140, 100, 30);
        memoButton.setBounds(420, 180, 100, 30);
        seriesButton.setBounds(420, 220, 100, 30);
        eventButton.setBounds(420, 260, 100, 30);
        backButton.setBounds(420, 450, 100, 40);
        eventList.setBounds(550, 50, 200, 350);
        goButton.setBounds(600, 450, 100, 40);

    }

    private void addComponentsToContainer() {
        container.add(tagLabel);
        container.add(dateLabel);
        container.add(memoLabel);
        container.add(seriesLabel);
        container.add(eventLabel);
        container.add(tagTextField);
        container.add(dateTextField);
        container.add(memoTextField);
        container.add(seriesTextField);
        container.add(eventTextField);
        container.add(tagButton);
        container.add(dateButton);
        container.add(memoButton);
        container.add(seriesButton);
        container.add(eventButton);
        container.add(backButton);
        container.add(eventList);
        container.add(goButton);
    }

    private void addActionEvent() {
        tagButton.addActionListener(this);
        dateButton.addActionListener(this);
        memoButton.addActionListener(this);
        seriesButton.addActionListener(this);
        eventButton.addActionListener(this);
        backButton.addActionListener(this);
        goButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] eventString;
        int j = eventList.getSelectedIndex();
        if (e.getSource() == tagButton) {
            String tagName = tagTextField.getText();
            events = CalendarFacade.searchEventByTag(tagName);
            if (events.size() == 0) {
                JOptionPane.showMessageDialog(this, "No event with this tag");
            }
        }
        if (e.getSource() == dateButton) {
            String date = dateTextField.getText();
            events = CalendarFacade.searchEventByDate(date);
            if (events.size() == 0) {
                JOptionPane.showMessageDialog(this, "No event at this datetime");
            }
        }
        if (e.getSource() == memoButton) {
            String memoName = memoTextField.getText();
            events = CalendarFacade.searchEventByMemo(memoName);
            if (events.size() == 0) {
                JOptionPane.showMessageDialog(this, "No event with this memo");
            }
        }
        if (e.getSource() == seriesButton) {
            String seriesName = seriesTextField.getText();
            events = CalendarFacade.searchEventBySeriesName(seriesName);
            if (events.size() == 0) {
                JOptionPane.showMessageDialog(this, "No event in this series");
            }
        }
        if (e.getSource() == eventButton) {
            String eventName = eventTextField.getText();
            events = CalendarFacade.searchEventByName(eventName);
            if (events.size() == 0) {
                JOptionPane.showMessageDialog(this, "No event with this name");
            }
        }

        eventString = new String[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventString[i] = events.get(i).toString();
        }
        eventList.setListData(eventString);

        if (e.getSource() == goButton) {
            EditEventFrame targetEventFrame = new EditEventFrame(events.get(j));
            // need to set some default;
        }

        if (e.getSource() == backButton) {
            this.dispose();

        }

    }
}
