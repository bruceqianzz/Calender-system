import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMemoInEventFrame extends MemoFrame implements ActionListener, ListSelectionListener {

    Event event;
    JButton addToButton = new JButton("Add To Existed Memo");
    JButton addMemoButton = new JButton();

    EditMemoInEventFrame(Event event) {
        super();
        this.event = event;
        this.memos = event.getMemos();
        try{
        String[] memoString = new String[memos.size()];
            for (int j = 0; j < memos.size(); j++) {
                memoString[j] = memos.get(j).toString();
            }
            memoList.setListData(memoString);}
        catch(NullPointerException ignored){
        }

        container.add(addToButton);
        container.add(addMemoButton);
        addMemoButton.setText("Add A New Memo");
        addToButton.addActionListener(this);
        addMemoButton.addActionListener(this);
        addToButton.setBounds(460, 180, 200, 30);
        addMemoButton.setBounds(460, 300, 180, 30);
    }

    public void actionPerformed(ActionEvent e) {
        String memoName = memoList.getSelectedValue();
        if (e.getSource() == goButton) {
            int i = eventList.getSelectedIndex();
            EditEventFrame targetEventFrame = new EditEventFrame(events.get(i));
        }
        if (e.getSource() == backButton) {
            EditEventFrame ed = new EditEventFrame(event);
            this.dispose();
        }
        if (e.getSource() == addMemoButton) {
            AddMemoFrame addMemoFrame = new AddMemoFrame(event);
            this.dispose();
        }

        //delete this memo in this event and delete the event from that memo
        if (e.getSource() == deleteMemoButton) {
            event.deleteMemo(memoName);
            EditMemoInEventFrame ed = new EditMemoInEventFrame(event);
            this.dispose();
        }

        if(e.getSource() == addToButton){
            SelectMemoFrame sm = new SelectMemoFrame(event);
            this.dispose();
        }

    }

}
