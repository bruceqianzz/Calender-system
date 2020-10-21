import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

public class CalendarFacade {

    private static User currentUser;
    private static Calendar currentCalendar;

    //private static LoginSystem loginSystem = new LoginSystem();

    public static String timeAppeared = "0";

    // if currentCalendar is null then set currentCalendar else do nothing
    public static void VerifyCurrentCalendar(){

    }

    public static  User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User para){
        CalendarFacade.currentUser = para;
    }

    public static  Calendar getCurrentCalendar(){
        return currentCalendar;
    }

    public static void setCurrentCalendar(Calendar para){
        CalendarFacade.currentCalendar = para;
    }

    // Call all the methods of Event

    // Call login system and set user to currentUser
    public static void callLogin() throws IOException, ClassNotFoundException {
        LoginSystem.login();
    }

    public static void updater(){

        LocalDateTime now = LocalDateTime.now();

        for (Event event: currentCalendar.getEvents()){
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();

            // case1 move futureEvent
            if (startTime.isAfter(now) && currentCalendar.getFutureEvents().contains(event))
                currentCalendar.moveToNow(event);
            // case2 move ongoingEvent
            if (endTime.isBefore(now) && currentCalendar.getOngoingEvents().contains(event))
                currentCalendar.moveToNow(event);
        }

    }

    public static void logout() {
        setCurrentUser(null);
        setCurrentCalendar(null);
    }

    public static ArrayList<Calendar> showCalendar(){
        return CalendarShow.showCalendar(currentUser);
    }

    public static ArrayList<Event> showOngoingEvent(){
        return CalendarShow.showOngoingEvent(currentCalendar);
    }

    public static ArrayList<Event> showPastEvent(){
        return CalendarShow.showPastEvent(currentCalendar);
    }

    public static ArrayList<Event> showFutureEvent(){
        return CalendarShow.showFutureEvent(currentCalendar);
    }

    public static ArrayList<Event> showEvents(){
        return CalendarShow.showEvents(currentCalendar);
    }

    public static ArrayList<Event> showTodayEvents(){
        return CalendarShow.showTodayEvents(currentCalendar);
    }

    public static String getMemoContent(String memoName) { return CalendarShow.getMemoContent(currentCalendar, memoName);  }

    public static ArrayList<Memo> showMemo(){
        // border
        return CalendarShow.showMemo(currentCalendar);
    }

    public static ArrayList<Series> showSeries(){
        return CalendarShow.showSeries(currentCalendar);
    }


    public static ArrayList<Event> getEvents(){
        return currentCalendar.getEvents();
    }

    public static ArrayList<String> getAllUsers() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
        return (ArrayList<String>) is.readObject();
    }



    public static ArrayList<Event> searchEventByTag(String para){
        return CalendarSearch.searchEventByTag(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByDate(String para){
        return CalendarSearch.searchEventByDate(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByMemo(String para){
        return CalendarSearch.searchEventByMemo(currentCalendar, para);
    }

    public static ArrayList<Event> searchEventByName(String para){
        return CalendarSearch.searchEventByName(currentCalendar, para);
    }


    public static ArrayList<Event> searchEventBySeriesName(String para) {
        return CalendarSearch.searchEventBySeriesName(currentCalendar, para);
    }

    public static ArrayList<Alert> searchAlerts(String para1, String para2){
        return CalendarSearch.searchAlerts(currentCalendar, para1, para2);
    }

    public static void alert(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String nowString = LocalDateTime.now().format(formatter);
        //LocalDateTime now = LocalDateTime.parse(nowString, formatter);

        boolean detective = false;
        // check user alert on and event alert on
        if (currentCalendar.getAlertOn()){
            for (Event event: currentCalendar.getEvents())
                for(Alert alert: event.getAlerts()){
                    //if (alert.getStartTime().isEqual(now)) {
                    if ((alert.getStartTime().format(formatter)).equals(nowString) && !nowString.equals(timeAppeared)) {
                        //System.out.println(alert);
                        JOptionPane.showMessageDialog(null, alert.toString());
                        detective = true;
                        timeAppeared = nowString;
                        //alert.closeAlert();
                    }else {
                        if (detective)
                            break;
                    }
                }
        }

    }

    public static void shareEventbetweenUser(String eventName, String username) throws IOException, ClassNotFoundException {
        ArrayList<Event> events = currentCalendar.getEvents();
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(username));
        User user = (User) is.readObject();
        for (Event event: events){
            if (event.getName().equals(eventName)){
                //Event newEvent = event.duplicate();
                user.getCalendars().get(0).addEvent(event);
                System.out.println("shareEventbetweenUser");
            }

        }
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(username));
        os.writeObject(user);
        os.close();

    }

    public static void deleteOldSharedEvent(String eventName, String username) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(username));
        User user = (User) is.readObject();
        int index = 0;
        ArrayList<Event> events = user.getCalendars().get(0).getEvents();
        for (Event event: events){
            if (event.getName().equals(eventName))
                break;
            index++;
        }
        events.remove(index);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(username));
        os.writeObject(user);
        os.close();
    }

    public static void addToSeries(String seriesName, Event e){
        Series getSeries = new Series("idk");
        for (Series series: currentCalendar.getSeries()){
            if (series.getName().equals(seriesName)){
                getSeries = series;
                break;
            }
        }

//        Event getEvent;
//        for (Event event: currentCalendar.getEvents()){
//            if (event.toString().equals(e.toString())){
//                getSeries.addToSeries(event);
//            }
//        }
        getSeries.addToSeries(e);

    }

    public static Event postDupHelper(String eventName, String op, LocalDateTime startTime, int duration){

        Event choose = null;
        for (Event event: currentCalendar.getEvents()){
            if (event.getName().equals(eventName))
                choose = event;
        }

        Event newEvent = new Event(choose.getName(), choose.getAddress());
        newEvent.setTags(choose.getTags());
        newEvent.setMemos(choose.getMemos());

        switch (op){
            case "postpone":
                currentCalendar.deleteEvent(eventName);
                break;

            case "duplicate":
                newEvent.setStartTime(startTime);
                newEvent.setDuration(duration);
        }
        currentCalendar.addEvent(newEvent);
        return newEvent;
    }

    public static void riseNoTimeEventUp(String eventName){
        Event choose = null;
        for (Event event: currentCalendar.getEvents()){
            if (event.getName().equals(eventName)){
                choose = event;
            }
        }
        currentCalendar.getEvents().remove(choose);
        currentCalendar.getNoTimeEvents().remove(choose);
        currentCalendar.addEvent(choose);
    }



}
