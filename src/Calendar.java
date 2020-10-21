import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Calendar implements Serializable {

    private String name;
    private boolean alertOn;
    private boolean dark;
    private ArrayList<Event> pastEvents = new ArrayList<>();
    private ArrayList<Event> futureEvents = new ArrayList<>();
    private ArrayList<Event> ongoingEvents = new ArrayList<>();
    private ArrayList<Event> noTimeEvents = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Memo> memos = new ArrayList<>();
    private ArrayList<Alert> alertList = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();

    public Calendar(String Givename){
        name = Givename;
        alertOn = true;
        dark  = false;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Event> getNoTimeEvents() {
        return noTimeEvents;
    }

    public ArrayList<Event> getPastEvents() {
        return this.pastEvents;
    }

    public void setPastEvents(ArrayList<Event> pastEvents) {
        this.pastEvents = pastEvents;
    }

    public ArrayList<Event> getFutureEvents() {
        return this.futureEvents;
    }

    public void setFutureEvents(ArrayList<Event> futureEvents) {
        this.futureEvents = futureEvents;
    }

    public ArrayList<Event> getOngoingEvents() {
        return ongoingEvents;
    }

    public void setOngoingEvent(ArrayList<Event> ongoingEvents) {
        this.ongoingEvents = ongoingEvents;
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }

    public void setEvents(ArrayList<Event> evts){
        this.events = evts;
    }

    public boolean getAlertOn(){
        return this.alertOn;
    }

    public void setAlertOn() {
        this.alertOn = true;
    }

    public void setAlertOff(){
        this.alertOn = false;
    }

    public ArrayList<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(ArrayList<Alert> alertList) {
        this.alertList = alertList;
    }

    public ArrayList<Memo> getMemos() {
        return memos;
    }

    public void setMemos(ArrayList<Memo> mem) {
        this.memos = mem;
    }

    public ArrayList<Series> getSeries(){
        return this.series;
    }

    public void setSeries(ArrayList<Series> series){
        this.series = series;
    }

    public void OrderEvents(){
        Collections.sort(this.events);
    }

    public void OrderPastEvents(){
        Collections.sort(this.pastEvents);
    }

    public void OrderFutureEvents(){
        Collections.sort(this.futureEvents);
    }

    public void OrderOngoingEvents(){
        Collections.sort(this.ongoingEvents);
    }

    public Memo createMemo(String name, String content){
        Memo mm = new Memo(name, content);
        this.memos.add(mm);
        return mm;
    }

    public void deleteMemos(String name){
        ArrayList<Memo> del = new ArrayList<>();
        for (Memo me: memos){
            if(me.getName().equals(name)){
                for(Event evt : me.getEvents()){
                    evt.deleteMemo(me.getName());
                }
                del.add(me);
            }
        }
        this.memos.removeAll(del);
    }

    public void createEvent(String name, String startTime, int duration, String address){
        Event evt = new Event(name, startTime, duration, address);
        this.events.add(evt);
        LocalDateTime start = evt.getStartTime();
        LocalDateTime end = start.plusMinutes(duration);
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now)){
            this.futureEvents.add(evt);
        }
        else if(start.isBefore(now)){
            if (end.isBefore(now)){
                this.pastEvents.add(evt);
            }
            else{this.ongoingEvents.add(evt);
            }
        }
        else{
            this.ongoingEvents.add(evt);
        }
    }

    public void deleteEvent(String name){
        boolean res = false;
        for (Event evt: this.events){
            if (evt.getName().equals(name)) {
                this.events.remove(evt);
                res = true;
                break;
            }
        }
        for (Event evt: this.ongoingEvents){
            if(evt.getName().equals(name)){
                this.ongoingEvents.remove(evt);
                return;
            }
        }
        for (Event evt: this.pastEvents){
            if(evt.getName().equals(name)){
                this.pastEvents.remove(evt);
                return;
            }
        }
        for (Event evt: this.futureEvents){
            if(evt.getName().equals(name)){
                this.futureEvents.remove(evt);
                return;
            }
        }
        if (!res){
            System.out.println("No such event exists!");
        }
    }

    public void moveToNow(Event evt) {
        deleteEvent(evt.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String str =  formatter.format(evt.getStartTime());
        createEvent(evt.getName(), str, evt.getDuration(), evt.getAddress());
    }

    public void createSeries(String name){
        Series sir  = new Series(name);
        this.series.add(sir);
    }

    public ArrayList<Event> searchEventsBySeries(String name){
        for(Series series: this.series){
            if (series.getName().equals(name)){
                return series.getEvents();
            }
        }
//        System.out.println("Series does not exists!");
        return null;
    }

    public void addEvent(Event evt){
        events.add(evt);

        if (evt.getStartTime()==null){
            noTimeEvents.add(evt);
            return;
        }


        LocalDateTime start = evt.getStartTime();
        LocalDateTime end = start.plusMinutes(evt.getDuration());
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now)){
            this.futureEvents.add(evt);
        }
        else if(start.isBefore(now)){
            if (end.isBefore(now)){
                this.pastEvents.add(evt);
            }
            else{this.ongoingEvents.add(evt);
            }
        }
        else{
            this.ongoingEvents.add(evt);
        }
    }





}
