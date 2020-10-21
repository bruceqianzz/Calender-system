import java.util.TimerTask;

class Updater extends TimerTask {
    public void run() {
        CalendarFacade.updater();
        CalendarFacade.alert();
    }
}
