import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FutureEventFrame extends AllEventFrame implements ActionListener {


   FutureEventFrame() {
        this.setTitle("Future Events");
        this.events = CalendarFacade.showFutureEvent();
        String[] eventString = new String[events.size()];
        for (int j = 0; j < events.size(); j++) {
            eventString[j] = events.get(j).getName();
        }
        eventList.setListData(eventString);
   }
}
