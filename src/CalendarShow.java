import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarShow {

    public static ArrayList<Calendar> showCalendar(User currentUser){

        ArrayList<Calendar> calendarList = currentUser.getCalendars();
        return calendarList;

    }

    public static ArrayList<Event> showOngoingEvent(Calendar currentCalendar){

        ArrayList<Event> eventList = currentCalendar.getOngoingEvents();
        return eventList;

    }

    public static ArrayList<Event> showPastEvent(Calendar currentCalendar){

        ArrayList<Event> eventList = currentCalendar.getPastEvents();
        return eventList;

    }

    public static ArrayList<Event> showFutureEvent(Calendar currentCalendar){

        ArrayList<Event> eventList = currentCalendar.getFutureEvents();
        return eventList;

    }

    public static ArrayList<Event> showEvents(Calendar currentCalendar){

        ArrayList<Event> eventList = currentCalendar.getEvents();
        return eventList;

    }

    public static ArrayList<Event> showTodayEvents(Calendar currentCalendar){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowString = now.format(formatter);
        ArrayList<Event> eventList = new ArrayList<Event>();


        for (Event event: currentCalendar.getEvents()) {
            if (event.getStartTime().format(formatter).equals(nowString)) {

                eventList.add(event);
            }
        }

        return eventList;

    }

    public static ArrayList<Memo> showMemo(Calendar currentCalendar){
        // border
        // border
        ArrayList<Memo> memoList = currentCalendar.getMemos();
        return memoList;
    }

    public static ArrayList<Series> showSeries(Calendar currentCalendar){
        ArrayList<Series> seriesList = currentCalendar.getSeries();

        return seriesList;
    }

    public static String getMemoContent(Calendar currentCalendar,String memoName) {
        String res = "No result";
        for (Memo memo: currentCalendar.getMemos()){
            if (memo.getName().equals(memoName))
                return memo.getContent();
        }
        return res;
    }

}
