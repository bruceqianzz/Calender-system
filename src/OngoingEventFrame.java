import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OngoingEventFrame extends AllEventFrame implements ActionListener {


    OngoingEventFrame() {
        this.setTitle("Ongoing Events");
        this.events = CalendarFacade.showOngoingEvent();
        String[] eventString = new String[events.size()];
        for (int j = 0; j < events.size(); j++) {
            eventString[j] = events.get(j).getName();
        }
        eventList.setListData(eventString);
    }




}
