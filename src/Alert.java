//package ???

import java.time.LocalDateTime;

public class Alert implements java.io.Serializable, Comparable<Alert> {
    private Event event;
    private LocalDateTime startTime;
//    private Frequency frequency;
//    private  int num;
//    private Unit unit;

//    public enum Unit {
//        MINUTE,
//        HOUR,
//        DAY,
//        WEEK,
//        MONTH,
//        YEAR,
//    }


    //Constructor
    //setter and getter
    //toString

    public Alert(Event event, LocalDateTime startTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        this.event = event;
        this.startTime = startTime;
//        this.num = num;
//        this.unit = unit;
    }


    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event e) {
        this.event = e;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime t) {
        this.startTime = t;
    }

//    public int getNum() {
//        return this.num;
//    }
//
//    public void setNum(int n) {
//        this.num = n;
//    }
//
//    public Unit getUnit() {
//        return this.unit;
//    }
//
//    public void setUnit(Unit u) {
//        this.unit = u;
//    }


    // toString example: workout on 2020-March-1 13:00
    public String toString() {
        String s = "Alert at " + this.getStartTime().toString().replace("T", " ") + ":\n" + "  " +
                this.event.getName() + " (at " + event.getAddress() + " " +
                event.getStartTime().toString().replace("T", " ") + ")";
        return s;
    }

    @Override
    public int compareTo(Alert a) {
        if (this.startTime.isBefore(a.startTime)) {
            return -1;
        } else if (this.startTime.isEqual(a.startTime)) {
            return 0;
        } else  {
            return 1;
        }
    }

//    public int compare(Alert a1, Alert a2){
//        if(a1.startTime.isBefore(a2.startTime)){
//            return -1;
//        }
//        else if(a1.startTime.isEqual(a2.startTime)){
//            return 0;
//        }
//        else(a1.startTime.isAfter(a2.startTime)){
//            return 1;
//        }
//
//    }

}

