import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class User implements Serializable{
    private String name;
    private String password;
    private ArrayList<Calendar> calendars = new ArrayList<Calendar>();


    public User(String username, String psw){
        name = username;
        password = psw;
        calendars.add(new Calendar("Default"));
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String psw){
        this.password = psw;
    }

    public ArrayList<Calendar> getCalendars(){
        return calendars;
    }

    public void creatCalendar(String name){
        Calendar calendar = new Calendar(name);
        calendars.add(calendar);
        CalendarFacade.setCurrentCalendar(calendar);
    }

}
