import java.awt.*;        // Using AWT layouts
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import javax.swing.*;     // Using Swing components and containers
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SeriesFrame extends BasicFrame implements ActionListener, ListSelectionListener {

    Container container = getContentPane();
    public static JList<String> seriesList = new JList<>();
    JList<String> eventList = new JList<>();
    JLabel seriesLabel = new JLabel("series");
    JLabel eventsLabel = new JLabel("events of selected series");
    //    JTextField seriesContentTextField = new JTextField();
    JButton goButton = new JButton("show events of selected series");
    JButton backButton = new JButton("back");
    JButton addEventButton = new JButton("add events to selected series");
    JButton frequencyEventButton = new JButton("<html>" + "create recurring events" + "<br>" + "for selected series");

    JButton addSeriesButton = new JButton("create series");
    JButton deleteSeriesButton = new JButton("delete series");

    ArrayList<Event> events = new ArrayList<>();



    SeriesFrame() {
        this.setTitle("Series");
        this.setBounds(10, 10, 700, 550);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        // we store currentCalendar rather than currentUser now --- by Oliver
        ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();
        String[] seriesString = new String[series.size()];
        for (int j = 0; j < series.size(); j++) {
            seriesString[j] = series.get(j).getName();
        }
        seriesList.setListData(seriesString);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        seriesLabel.setBounds(60, 30, 100, 30);
        eventsLabel.setBounds(220, 30, 250, 30);
//        seriesContentTextField.setBounds(30, 450, 400, 90);
        seriesList.setBounds(30, 60, 100, 350);
        eventList.setBounds(150, 60, 300, 350);
        goButton.setBounds(455, 90, 220, 30);
        backButton.setBounds(455, 30, 130, 30);
        addSeriesButton.setBounds(455, 150, 130, 30);
        deleteSeriesButton.setBounds(455, 210, 130, 30);
        addEventButton.setBounds(455, 270, 220, 30);
        frequencyEventButton.setBounds(455, 330, 220, 60);


    }

    private void addComponentsToContainer() {
        container.add(seriesLabel);
        container.add(eventsLabel);
        container.add(seriesList);
        container.add(eventList);
        container.add(goButton);
        container.add(backButton);
        container.add(addSeriesButton);
        container.add(deleteSeriesButton);
        container.add(addEventButton);
        container.add(frequencyEventButton);
    }

    private void addActionEvent() {
        seriesList.addListSelectionListener(this);
        goButton.addActionListener(this);
        backButton.addActionListener(this);
        addSeriesButton.addActionListener(this);
        deleteSeriesButton.addActionListener(this);
        addEventButton.addActionListener(this);
        frequencyEventButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goButton) {

            // we store currentCalendar rather than currentUser now --- by Oliver
            ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();



            try {

                int i = seriesList.getSelectedIndex();
                String seriesName = series.get(i).getName();

                ArrayList<Event> events = CalendarFacade.searchEventBySeriesName(seriesName);
                String[] eventsString = new String[events.size()];

                if (events.size() == 0){
                    JOptionPane.showMessageDialog(this, "No events in this series so far");
                }
                else {for (int j = 0; j < events.size(); j++) {
                    eventsString[j] = events.get(j).toString();
                }

                    eventList.setListData(eventsString);
                }




            } catch (IndexOutOfBoundsException ee) {
                JOptionPane.showMessageDialog(this, "Uh-oh! No series so far, please " +
                        "create series first");

            }
            catch (NullPointerException n) {
                n.printStackTrace();
                JOptionPane.showMessageDialog(this, "No events in this series so far");
            }

        }


        if (e.getSource() == backButton) {
            this.dispose();

        }

        if (e.getSource() == addSeriesButton) {
            AddSeriesFrame newSeries = new AddSeriesFrame();
        }



        if (e.getSource() == deleteSeriesButton) {
            // we store currentCalendar rather than currentUser now --- by Oliver
            try {
                ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();

                int i = seriesList.getSelectedIndex();
                String seriesName = series.get(i).getName();

                Series target = null;
                for (Series value : series) {
                    if (value.getName().equals(seriesName)) {
                        target = value;
                    }
                }
                series.remove(target);
                String[] seriesString = new String[series.size()];
                for (int j = 0; j < series.size(); j++) {
                    seriesString[j] = series.get(j).getName();
                }
                seriesList.setListData(seriesString);
                ArrayList<Event> events = CalendarFacade.searchEventBySeriesName(seriesName);
                String[] eventsString = new String[events.size()];
                eventList.setListData(eventsString);
            } catch (IndexOutOfBoundsException i) {
                JOptionPane.showMessageDialog(this, "Uh-oh! No series so far, please create " +
                        "series first");
            }
        }


            if (e.getSource() == addEventButton) {
                try {
                    ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();
                    int i = seriesList.getSelectedIndex();
                    String seriesName = series.get(i).getName();

                    AddEventToSeriesFrame a = new AddEventToSeriesFrame(seriesName);
                } catch (IndexOutOfBoundsException i) {
                    JOptionPane.showMessageDialog(this, "please select a series first or " +
                            "create series first if no series exists");
                }
            }



            if (e.getSource() == frequencyEventButton) {
                try {
                    ArrayList<Series> series = CalendarFacade.getCurrentCalendar().getSeries();
                    int i = seriesList.getSelectedIndex();
                    String seriesName = series.get(i).getName();

                    CreateFrequencyEventsFrame f = new CreateFrequencyEventsFrame(seriesName);
                } catch (IndexOutOfBoundsException i) {
                    JOptionPane.showMessageDialog(this, "Uh-oh! No series so far, please create " +
                            "series first");
                }
            }
        }



        public void valueChanged (ListSelectionEvent e){
            String targetSeriesString = seriesList.getSelectedValue();

            try {
                events = CalendarFacade.searchEventBySeriesName(targetSeriesString);
                String[] eventString;
                eventString = new String[events.size()];
                for (int i = 0; i < events.size(); i++) {
                    eventString[i] = events.get(i).toString();
                }
                eventList.setListData(eventString);
            } catch (NullPointerException n) {
                JOptionPane.showMessageDialog(this, "Uh-oh! No events in this series so far");
            }


        }

}





