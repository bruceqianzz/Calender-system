import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SelectMemoFrame extends BasicFrame implements ActionListener {

    Container container = getContentPane();
    JList<String> memoList = new JList<>();
    JLabel memoLabel = new JLabel("Memos:");
    JButton editButton = new JButton("SELECT");
    JButton backButton = new JButton("BACK");
    Event event;
    ArrayList<Memo> memos = CalendarFacade.showMemo();

    SelectMemoFrame(Event event) {
        this.setBounds(10, 10, 650, 550);
        this.event = event;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        String[] memoString = new String[memos.size()];
        for (int j = 0; j < memos.size(); j++) {
            memoString[j] = memos.get(j).getName();
        }
        memoList.setListData(memoString);
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        memoLabel.setBounds(30, 30, 100, 30);
        memoList.setBounds(30, 60, 200, 250);
        editButton.setBounds(460, 60, 100, 40);
        backButton.setBounds(460, 120, 100, 40);

    }

    private void addComponentsToContainer() {
        container.add(memoLabel);
        container.add(memoList);
        container.add(editButton);
        container.add(backButton);
    }

    private void addActionEvent() {
        editButton.addActionListener(this);
        backButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            int i = memoList.getSelectedIndex();
            Memo memo = memos.get(i);
            memo.addEvent(event);
            event.addMemo(memo);
            EditMemoInEventFrame ef = new EditMemoInEventFrame(event);
            this.dispose();

        }
        if (e.getSource() == backButton) {
            this.dispose();
            EditMemoInEventFrame ef = new EditMemoInEventFrame(event);

        }

    }


}

