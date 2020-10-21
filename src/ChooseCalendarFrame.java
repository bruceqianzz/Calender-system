import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseCalendarFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JList<String> calendarList = new JList<>();
    JLabel calendarLabel = new JLabel("My Calendars");
    JButton goButton = new JButton("GO");
    JButton backButton = new JButton("BACK");
    JButton createButton = new JButton("CREATE NEW CALENDAR");
    User user;
    ArrayList<Calendar> calendars = new ArrayList<>();

    ChooseCalendarFrame(User user) {
        this.setTitle("Choose a Calendar");
        this.setVisible(true);
        this.setBounds(10, 10, 650, 550);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.user = user;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        calendars = user.getCalendars();
        String[] calendarString = new String[calendars.size()];
        for (int j = 0; j < calendars.size(); j++) {
            calendarString[j] = calendars.get(j).getName();
        }
        calendarList.setListData(calendarString);
    }

    public static void main(String[] args) throws IOException {
        String fileName =CalendarFacade.getCurrentUser().getName();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        os.writeObject(CalendarFacade.getCurrentUser());
        os.close();
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        calendarLabel.setBounds(30, 30, 100, 30);
        calendarList.setBounds(30, 60, 200, 250);
        goButton.setBounds(260, 60, 200, 40);
        backButton.setBounds(260, 120, 200, 40);
        createButton.setBounds(260, 180, 200, 40);

    }

    private void addComponentsToContainer() {
        container.add(calendarLabel);
        container.add(calendarList);
        container.add(goButton);
        container.add(backButton);
        container.add(createButton);
    }

    private void addActionEvent() {
        goButton.addActionListener(this);
        backButton.addActionListener(this);
        createButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goButton) {
            int i = calendarList.getSelectedIndex();
            CalendarFacade.setCurrentCalendar(calendars.get(i));
            MainMenuFrame main = new MainMenuFrame();
            this.dispose();

            //EditEventFrame targetEventFrame = new EditEventFrame(events.get(i));
            // need to set some default
        }
        if (e.getSource() == backButton) {
            CalendarFacade.setCurrentUser(null);
            this.dispose();
            try {
                LoginSystem.login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(new FileOutputStream(CalendarFacade.getCurrentUser().getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException n) {
                try {
                    LoginSystem.login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                assert os != null;
                os.writeObject(CalendarFacade.getCurrentUser());
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException n) {
                try {
                    LoginSystem.login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException n) {
                try {
                    LoginSystem.login();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }


        if (e.getSource() == createButton){
            JFrame frame = new JFrame();
            String result = JOptionPane.showInputDialog(frame, "Enter new calendar name:");
            user.creatCalendar(result);
            ObjectOutputStream os = null;
            try {
                os = new ObjectOutputStream(new FileOutputStream(CalendarFacade.getCurrentUser().getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
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
            this.dispose();
            MainMenuFrame main = new MainMenuFrame();

        }

    }
}
