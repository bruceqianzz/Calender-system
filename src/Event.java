import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;


public class Event implements java.io.Serializable, Comparable<Event> {

    //name of the event
    private String name;

    //start time of the event
    private LocalDateTime startTime;

    //duration of the event in minute
    private int duration;

    //address of the event
    private String address;

    //list of tags of the event
    private ArrayList<String> tags;

    //list of memos of the event
    private ArrayList<Memo> memos;

    //the alert of the event
    private ArrayList<Alert> alerts = new ArrayList<>();

    private ArrayList<String> users = new ArrayList<String>() {{
        add(CalendarFacade.getCurrentUser().getName());
    }};

    //a boolean indicates whether the alert of the event is on or off
    private boolean alertOn = true;

    public Event(String name, String address){
        this.name = name;
        this.address = address;
        this.tags = new ArrayList<>();
        this.memos = new ArrayList<>();
    }

    public Event(String name, String startTime, int duration, String address){
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.duration = duration;
        this.address = address;
        this.tags = new ArrayList<>();
        this.memos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public ArrayList<Memo> getMemos() {
        return memos;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTimeInput) {
        String determinant = "default";
        if (startTime==null)
            determinant = "noTimeEvent";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = LocalDateTime.parse(startTimeInput, formatter);
        if (determinant.equals("noTimeEvent"))
            CalendarFacade.riseNoTimeEventUp(name);
        //System.out.println("haha");
    }

    public void setStartTime(LocalDateTime startTimeInput) {

        this.startTime = startTimeInput;

    }



    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private LocalDateTime getStartTimeForAlert(LocalDateTime t, int increment, Unit unit){
        switch (unit){
            case MINUTE:
                return t.plusMinutes(increment);
            case HOUR:
                return t.plusHours(increment);
            case DAY:
            case WEEK:
                return t.plusWeeks(increment);
            case MONTH:
                return t.plusMonths(increment);
            case YEAR:
                return t.plusYears(increment);
            default:
                return t;
        }
    }

    public void setAlert(String startTime, int num, Unit unit) {
        // no alert at same time
        Alert a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        LocalDateTime time;
        for (int i = 0; i<num; i++){
            time = getStartTimeForAlert(t, i, unit);
            a = new Alert(this, time);
            alerts.add(a);
        }
    }

    public void deleteAlert(String startTime){
        //leads to unreferenced Alert object?
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(startTime, formatter);
        alerts.removeIf(a -> t.isEqual(a.getStartTime()));
    }

    public void turnOnAlert(){
        if (! alertOn){
            alertOn = true;
        }
    }

    public void turnOffAlert(){
        if (alertOn) {
            alertOn = false;
        }
    }


    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean deleteTag(String tag) {
        return tags.remove(tag);
    }

    public void addNewMemo(String name, String content){
        Memo m = new Memo(name, content);
        memos.add(m);
        m.addEvent(this);
    }

    public void addMemo(Memo memo){
        memos.add(memo);
    }

    public boolean deleteMemo(String nameOfMemo) {
        for (Memo m : memos){
            if (m.getName().equals(nameOfMemo)){
                m.deleteEvent(this.name, this.startTime.toString().replace("T", " "), duration, address);
                memos.remove(m);
                return true;
            }
        }
        return false;
    }

    public void showMemos(){
        if (memos.size() == 0){
            System.out.println("no memo in this event!");
        }
        else {
            String result = "";
            int j;
            for (int i = 0; i < memos.size(); i++){
                j = i + 1;
                result = result + j + " " + memos.get(i).getName() + "\n";
            }
            System.out.println(result);
        }
    }

    public void showAlerts(){
        if (alerts.size() == 0){
            System.out.println("no alert in this event!");
        }
        else {
            String result = "";
            int j;
            for (int i = 0; i < alerts.size(); i++){
                j = i + 1;
                result = result + j + " " + alerts.get(i).toString() + "\n";
            }
            System.out.println(result);
        }
    }


    public String toString(){
        String result = name + ": " + "from " + startTime.toString().replace("T", " ") + " to ";
        LocalDateTime endTime = startTime.plusMinutes(duration);
        result = result + endTime.toString().replace("T", " ") + " at " + address;
        return result;
    }

    public void orderAlert(){
        Collections.sort(this.alerts);
    }

    public LocalDateTime getEndTime(){
        return startTime.plusMinutes(duration);
    }

    @Override
    public int compareTo(Event e1) {
        if (this.startTime.isEqual(e1.startTime)) {
            return 0;
        }else if (this.startTime.isBefore(e1.startTime)) {
            return -1;
        }else{
            return 1;
        }
    }

    public void setTags(ArrayList<String> tagsInput){
        tags = tagsInput;
    }

    public void setMemos(ArrayList<Memo> memosInput){
        memos = memosInput;
    }

    public boolean isAlertOn() {
        return alertOn;
    }

    public void addUser(String username) throws IOException, ClassNotFoundException {
        if (!users.contains(username))
            users.add(username);
        CalendarFacade.shareEventbetweenUser(name, username);
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    //use this method whenever we modify the Event
    public void updateSharedEvent() throws IOException, ClassNotFoundException {
        if (users.size() > 1){
            for(String user: users){
                if (!user.equals(CalendarFacade.getCurrentUser().getName())){
                    CalendarFacade.deleteOldSharedEvent(name, user);
                    CalendarFacade.shareEventbetweenUser(name, user);
                }
            }
        }
    }

    public Event postpone(){
        return CalendarFacade.postDupHelper(name, "postpone", startTime, duration);
    }

    public Event duplicate(String startTimeInput){
        LocalDateTime startTimeOutput;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        startTimeOutput = LocalDateTime.parse(startTimeInput, formatter);
        return CalendarFacade.postDupHelper(name, "duplicate", startTimeOutput, duration);
    }

    
}
