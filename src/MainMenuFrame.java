import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class MainMenuFrame extends BasicFrame implements ActionListener {
    Container container = getContentPane();
    JLabel todayEvents = new JLabel("Today's events");
    JButton pastButton = new JButton("Past Events");
    JButton ongoingButton = new JButton("Ongoing Events");
    JButton futureButton = new JButton("Future Events");
    JButton allEventButton = new JButton("All Events");
    JButton seriesButton = new JButton("Series");
    JButton memoButton = new JButton("Memos");
    JButton searchButton = new JButton("Search Events");
    JButton addEventButton = new JButton("Add Event");
    JButton logoutButton = new JButton("BACK");
    JButton colorButton = new JButton("DARK MODE");
    JLabel viewEvents = new JLabel("View Events");
    JButton noTimeEventButton = new JButton("No-Time Events");


    MainMenuFrame(){
        this.setTitle("Calendar: " + CalendarFacade.getCurrentCalendar().getName());
        this.setBounds(10, 10, 800, 750);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager(){
        container.setLayout(null);
    }

    public void setLocationAndSize(){
        pastButton.setBounds(50, 100, 200, 50);
        ongoingButton.setBounds(50, 160, 200, 50);
        futureButton.setBounds(50, 220, 200, 50);
        noTimeEventButton.setBounds(50, 280, 200, 50);
        allEventButton.setBounds(50, 340, 200, 50);
        seriesButton.setBounds(480, 165, 100, 50);
        memoButton.setBounds(480, 245, 100, 50);
        searchButton.setBounds(300, 150, 150, 80);
        addEventButton.setBounds(300, 250, 150, 80);
        logoutButton.setBounds(380, 600, 100, 30);
        colorButton.setBounds(610,230, 160, 30);
        //ArrayList<Event> today = CalendarFacade.showTodayEvents();
       // todayEvents.setText("Today's events:");
        //todayEvents.setBounds(50, 370, 200, 200);
        viewEvents.setBounds(80, 50, 150, 30);
    }

    public void addComponentsToContainer(){
        container.add(pastButton);
        container.add(ongoingButton);
        container.add(futureButton);
        container.add(allEventButton);
        container.add(seriesButton);
        container.add(memoButton);
        container.add(searchButton);
        container.add(addEventButton);
        container.add(logoutButton);
        container.add(todayEvents);
        container.add(colorButton);
        container.add(viewEvents);
        container.add(noTimeEventButton);
    }

    public void addActionEvent(){
        pastButton.addActionListener(this);
        ongoingButton.addActionListener(this);
        futureButton.addActionListener(this);
        allEventButton.addActionListener(this);
        seriesButton.addActionListener(this);
        memoButton.addActionListener(this);
        searchButton.addActionListener(this);
        addEventButton.addActionListener(this);
        logoutButton.addActionListener(this);
        colorButton.addActionListener(this);
        noTimeEventButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding part of past
        if(e.getSource() == colorButton){
            if(CalendarFacade.getCurrentCalendar().isDark()){
                CalendarFacade.getCurrentCalendar().setDark(false);
            }
            else{
                CalendarFacade.getCurrentCalendar().setDark(true);
            }
            this.dispose();
            MainMenuFrame me = new MainMenuFrame();
        }


        if(e.getSource() == pastButton){
            PastEventFrame past = new PastEventFrame();
        }

        if(e.getSource() == ongoingButton){
            //wrong spelling in class ongoing event frame.
            OngoingEventFrame ing = new OngoingEventFrame();
        }

        if(e.getSource() == futureButton){
            FutureEventFrame fut = new FutureEventFrame();
        }

        if(e.getSource() == noTimeEventButton){
            //connect the no time event frame
        }

        if(e.getSource() == allEventButton){
            AllEventFrame all = new AllEventFrame();
        }

        if(e.getSource() == seriesButton){
            SeriesFrame series = new SeriesFrame();
        }

        if(e.getSource() == memoButton){
            MemoFrame memo = new MemoFrame();
        }

        if(e.getSource() == searchButton){
            SearchFrame search = new SearchFrame();
        }

        if(e.getSource() == addEventButton){
            AddEventFrame newEvent = new AddEventFrame();
        }


        if (e.getSource() == logoutButton){
//            CalendarFacade.logout();
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(new FileOutputStream(CalendarFacade.getCurrentUser().getName()));
            }

            catch (IOException ex) {
                ex.printStackTrace();
            }
            catch(NullPointerException n){

                    this.dispose();
                    ChooseCalendarFrame choose = new ChooseCalendarFrame(CalendarFacade.getCurrentUser());

            }

            try {
                assert os != null;
                os.writeObject(CalendarFacade.getCurrentUser());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            CalendarFacade.logout();
            this.dispose();
            ChooseCalendarFrame choose = new ChooseCalendarFrame(CalendarFacade.getCurrentUser());
        }



    }
}
