import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//import java.io.Serializable

public class Series implements java.io.Serializable {

    //name of the Series
    private String name;

    //list of events in this series
    private ArrayList<Event> events;

    public Series(String name){
        this.name = name;
        events = new ArrayList<>();
    }

//    private void createEventInMiniute(String nameOfEvent, LocalDateTime t, int duration, String address, int num){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            time = t.plusMinutes(i);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
//            events.add(e);
//        }
//    }

//    public void createEventInHour(String nameOfEvent, LocalDateTime t, int duration, String address, int num,
//                                   int numOfHours){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            int h = numOfHours * i ;
//            time = t.plusHours(h);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
////            events.add(e);
//            addToSeries(e);
//            CalendarFacade.getCurrentCalendar().addEvent(e);
////            Calendar.createEvent(nameOfEvent, time.toString().replace("T", " "), duration, address);
//        }
//    }

//    private void createEventInDay(String nameOfEvent, LocalDateTime t, int duration, String address, int num){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            time = t.plusDays(i);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
//            events.add(e);
//        }
//    }

//    private void createEventInWeek(String nameOfEvent, LocalDateTime t, int duration, String address, int num){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            time = t.plusWeeks(i);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
//            events.add(e);
//        }
//    }

//    private void createEventInMonth(String nameOfEvent, LocalDateTime t, int duration, String address, int num){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            time = t.plusMonths(i);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
//            events.add(e);
//        }
//    }

//    private void createEventInYear(String nameOfEvent, LocalDateTime t, int duration, String address, int num){
//        LocalDateTime time;
//        for (int i = 0; i < num; i++){
//            time = t.plusYears(i);
//            Event e = new Event(nameOfEvent, time.toString().replace("T", " "), duration, address);
//            events.add(e);
//        }
//    }

//    public void createFrequencyEvent(String nameOfEvent, String startTime, int duration, String address,
//                                     int num, int numOfHours) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
//        createEventInHour(nameOfEvent, t, duration, address, num, numOfHours);
//    }
//        switch (unit){
//            case MINUTE:
//                createEventInMiniute(nameOfEvent, t, duration, address, num);
//            case HOUR:
//                createEventInHour(nameOfEvent, t, duration,  address, num);
//                break;
//            case DAY:
//                createEventInDay(nameOfEvent, t, duration,  address, num);
//                break;
//            case WEEK:
//                createEventInWeek(nameOfEvent, t, duration,  address, num);
//                break;
//            case MONTH:
//                createEventInMonth(nameOfEvent, t, duration,  address, num);
//                break;
//            case YEAR:
//                createEventInYear(nameOfEvent, t, duration,  address, num);
//                break;
//            default:
//                Event e = new Event(nameOfEvent, t.toString().replace("T", " "), duration, address);
//                events.add(e);
//        }


    public void addToSeries(String nameOfEvent, String startTime, int duration, String address){
        Event e = new Event(nameOfEvent, startTime, duration, address);
        events.add(e);
    }


    public void addToSeries(Event event){
        events.add(event);
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }

//    public String toString(){
//        //        for (Event e : events){
////            result = result + e.getName() + " ";
////        }
//        return name + "\n";
//    }
//
//    public void showEvents(){
//        if (events.size() == 0){
//            System.out.println("no events in this series");
//        }else {
//            String result = "";
//            for (int i = 0; i < events.size(); i++){
//                result = Integer.toString(i+1) + " " + events.get(i).getName() + "\n";
//            }
//            System.out.println(result);
//        }
//    }

}
