import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AllEventFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JList<String> eventList = new JList<>();
    JLabel eventLabel = new JLabel("Events");
    JButton editButton = new JButton("EDIT");
    JButton backButton = new JButton("BACK");
    ArrayList<Event> events = new ArrayList<>();


    AllEventFrame() {
        this.setTitle("All Events");
        this.events = CalendarFacade.showEvents();
        this.setBounds(10, 10, 650, 550);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        String[] eventString = new String[events.size()];
        for (int j = 0; j < events.size(); j++) {
            eventString[j] = events.get(j).getName();
        }
        eventList.setListData(eventString);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        eventLabel.setBounds(30, 30, 100, 30);
        eventList.setBounds(30, 60, 200, 250);
        editButton.setBounds(460, 60, 100, 40);
        backButton.setBounds(460, 120, 100, 40);

    }

    private void addComponentsToContainer() {
        container.add(eventLabel);
        container.add(eventList);
        container.add(editButton);
        container.add(backButton);
    }

    private void addActionEvent() {
        editButton.addActionListener(this);
        backButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            int i = eventList.getSelectedIndex();
            EditEventFrame edit = new EditEventFrame(events.get(i));
            this.dispose();

        }
        if (e.getSource() == backButton) {
            this.dispose();

        }
    }
}

