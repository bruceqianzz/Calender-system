import javax.swing.*;
import java.awt.*;

public class BasicFrame extends JFrame {


    BasicFrame(){
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        if(CalendarFacade.getCurrentCalendar().isDark()){
            this.getContentPane().setBackground(Color.darkGray);
        }
    }
}
