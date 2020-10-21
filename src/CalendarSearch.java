import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalendarSearch {

    public static ArrayList<Event> searchEventByTag(Calendar currentCalendar, String para){

        ArrayList<Event> eventList = new ArrayList<Event>();

        for(Event event: currentCalendar.getEvents()){
            if(event.getTags().contains(para)){
                eventList.add(event);
            }
        }
        // get rid of the repeated elements
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }

    public static ArrayList<Event> searchEventByDate(Calendar currentCalendar, String para){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate search = LocalDate.parse(para, formatter);
        ArrayList<Event> eventList = new ArrayList<Event>();

        for(Event event: currentCalendar.getEvents()){

            if (event.getStartTime()!=null && event.getDuration()!=0){
                String startTimeString = event.getStartTime().format(formatter);
                LocalDate startTime = LocalDate.parse(startTimeString, formatter);
                String endTimeString = event.getEndTime().format(formatter);
                LocalDate endTime = LocalDate.parse(endTimeString, formatter);

                if(startTime.compareTo(search)<=0 && endTime.compareTo(search)>=0){
                    eventList.add(event);
                }

            }



        }

        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }


    public static ArrayList<Event> searchEventByMemo(Calendar currentCalendar, String memoName){

        ArrayList<Event> eventList = new ArrayList<Event>();

        for(Memo memo: currentCalendar.getMemos()){
            if(memo.getName().equals(memoName)){
                eventList.addAll(memo.getEvents());
            }
        }

        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());
    }

    public static ArrayList<Event> searchEventByName(Calendar currentCalendar, String para){

        ArrayList<Event> eventList = new ArrayList<Event>();


        for (Event event: currentCalendar.getEvents()){
            if (event.getName().contains(para)){

                eventList.add(event);
            }
        }
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());

    }


    public static ArrayList<Event> searchEventBySeriesName(Calendar currentCalendar, String para){


        ArrayList<Event> eventList = new ArrayList<Event>();

        for(Series series: currentCalendar.getSeries()){
            if(series.getName().equals(para)){

                eventList.addAll(series.getEvents());
            }
        }
        return (ArrayList<Event>) eventList.stream().distinct().collect(Collectors.toList());


    }

    public static ArrayList<Alert> searchAlerts(Calendar currentCalendar, String para1, String para2){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ArrayList<Alert> alertList = new ArrayList<Alert>();

        LocalDateTime startTime = LocalDateTime.parse(para1, formatter);

        LocalDateTime endTime = LocalDateTime.parse(para2, formatter);

        for(Alert alert: currentCalendar.getAlertList()){
            if (alert.getStartTime().isAfter(startTime) &&  alert.getStartTime().isBefore(endTime)){
                alertList.add(alert);

            }
        }


        return (ArrayList<Alert>) alertList.stream().distinct().collect(Collectors.toList());





    }
}
