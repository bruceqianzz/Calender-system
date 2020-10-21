import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton createButton = new JButton("CREATE ACCOUNT");


    LoginFrame() {
        this.setTitle("Calendar Login");
        this.setVisible(true);
        this.setBounds(10, 10, 400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        createButton.setBounds(200, 300, 180, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(createButton);

    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        createButton.addActionListener(this);
    }

    static ArrayList<String> Users = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        ObjectOutputStream users = new ObjectOutputStream(new FileOutputStream("Users"));
        users.writeObject(Users);
        users.close();
    }

    private static boolean justifyPasswordQualified(String password){
        if (password.length()<8)
            return false;
        for(int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            if (c >= 66&& c <= 90){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> openUpUsersFile() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
        return (ArrayList<String>) is.readObject();
    }

    private static void writeInUsersFile(ArrayList<String> Users) throws IOException {
        ObjectOutputStream userList = new ObjectOutputStream(new FileOutputStream("Users"));
        userList.writeObject(Users);
        userList.close();
    }


    public static void createAccount(String name, String password) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
        Users = (ArrayList<String>) is.readObject();
        User account = new User(name, password);
        String fileName = account.getName();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
        os.writeObject(account);
        os.close();
        Users.add(fileName);
        writeInUsersFile(Users);
    }

    private static boolean whetherPasswordMatch(String name, String password) throws IOException, ClassNotFoundException {
        if (whetherUserExit(name)) {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(name));
            User user = (User) is.readObject();
            return user.getPassword().equals(password);
        }
        return false;
    }

    private static boolean whetherUserExit(String name) throws IOException, ClassNotFoundException {
        Users = openUpUsersFile();
        for (String user : Users) {
            if (name.equals(user)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if(e.getSource() == loginButton){
            String username;
            String password;
            username = userTextField.getText();
            password = passwordField.getText();
            while(true){
                try {
                    if(whetherPasswordMatch(username, password)){
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(username));
                        User user = (User) is.readObject();
                        CalendarFacade.setCurrentUser(user);
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        ChooseCalendarFrame choose = new ChooseCalendarFrame(user);
                        this.dispose();

                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                        userTextField.setText("");
                        passwordField.setText("");
                    }
                    return;
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }

        //Coding part of Create Button
        if(e.getSource() == createButton){
            String username;
            String password;
            username = userTextField.getText();
            password = passwordField.getText();
            try {
                if (whetherUserExit(username)){
                    JOptionPane.showMessageDialog(this, "Username already existed");
                    userTextField.setText("");
                    passwordField.setText("");
                    return;
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            if (!justifyPasswordQualified(password)){
                JOptionPane.showMessageDialog(this, "at least length of 8 and with at least one capital");
                passwordField.setText("");
                return;
            }
            try {
                createAccount(username, password);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            userTextField.setText("");
            passwordField.setText("");
        }


    }
}

