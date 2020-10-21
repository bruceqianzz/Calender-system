import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddEventToSeriesFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JList<String> eventList = new JList<>();
    JLabel eventLabel = new JLabel("All Events");
    JButton addButton = new JButton("Add event");
    JButton backButton = new JButton("BACK");
    ArrayList<Event> events = CalendarFacade.getEvents();
    String seriesName ;


//    public void createList(){
//        ArrayList<Event> pe = CalendarFacade.getEvents();
//        events = CalendarFacade.showOngoingEvent();
//        ArrayList<Event> fe = CalendarFacade.showFutureEvent();
//        for (int i=0;i<pe.size();i++){
//            events.add(events.size()+i,pe.get(i));
//        }
//        for (int i=0;i<fe.size();i++){
//            events.add(events.size()+i,fe.get(i));
//        }
//    }

    AddEventToSeriesFrame(String seriesName) {
//        this.createList();
        this.setTitle("Add Events to this series");
        this.seriesName = seriesName;
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
        addButton.setBounds(460, 60, 100, 40);
        backButton.setBounds(460, 120, 100, 40);

    }

    private void addComponentsToContainer() {
        container.add(eventLabel);
        container.add(eventList);
        container.add(addButton);
        container.add(backButton);
    }

    private void addActionEvent() {
        addButton.addActionListener(this);
        backButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                int i = eventList.getSelectedIndex();
                Event event = events.get(i);
                CalendarFacade.addToSeries(seriesName, event);
                this.dispose();
            }
            catch (IndexOutOfBoundsException i){
                JOptionPane.showMessageDialog(this, "please go back to the main menu and" +
                        " add events to your calendar first");
            }

        }
        if (e.getSource() == backButton) {
            this.dispose();

        }

    }


}


