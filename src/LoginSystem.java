import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class LoginSystem extends JFrame {


    public static void login() throws IOException {
        LoginFrame frame = new LoginFrame();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File("Users");
        if (!file.exists()) {
            ObjectOutputStream users = new ObjectOutputStream(new FileOutputStream("Users"));
            users.writeObject(LoginFrame.Users);
            users.close();
        }
        LoginSystem.login();
        java.util.Timer timer = new Timer();
        timer.schedule(new Updater(), 0, 5000);
    }
//    private static boolean justifyPasswordQualified(String password){
//        if (password.length()<8)
//            return false;
//        for(int i=0; i<password.length(); i++){
//            char c = password.charAt(i);
//            if (c >= 66&& c <= 90){
//                return true;
//            }
//        }
//        return false;
//    }

//    private static ArrayList<String> openUpUsersFile() throws IOException, ClassNotFoundException {
//        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
//        return (ArrayList<String>) is.readObject();
//    }
//
//    private static void writeInUsersFile(ArrayList<String> Users) throws IOException {
//        ObjectOutputStream userList = new ObjectOutputStream(new FileOutputStream("Users"));
//        userList.writeObject(Users);
//        userList.close();
//    }


    //    public static void createAccount() throws IOException, ClassNotFoundException {
//        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Users"));
//        Users = (ArrayList<String>) is.readObject();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Type in your name:");
//        String name = input.next();
//        if (whetherUserExit(name)){
//            System.out.println("User already exist");
//            return;
//        };
//        System.out.print("Type in your password(at least length of 8 and with at least one capital):");
//        String password = input.next();
//        while (! justifyPasswordQualified(password)){
//            System.out.println("Unqualified password");
//            System.out.print("Type in your password(at least length of 8 and with at least one capital):");
//            password = input.next();
//        }
//        System.out.println("You successfully create your personal account");
//        User account = new User(name, password);
//        String fileName = account.getName();
//        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
//        os.writeObject(account);
//        os.close();
//        Users.add(fileName);
//        writeInUsersFile(Users);
//    }
//
//    private static boolean whetherUserExit(String name) throws IOException, ClassNotFoundException {
//        Users = openUpUsersFile();
//        if (name.equals("Esc"))
//            return true;
//        for (String user : Users) {
//            if (name.equals(user)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean whetherPasswordMatch(String name, String password) throws IOException, ClassNotFoundException {
//        if (name.equals("Esc"))
//            return true;
//        ObjectInputStream is = new ObjectInputStream(new FileInputStream(name));
//        User user = (User) is.readObject();
//        if (user.getPassword().equals(password))
//            return true;
//        else
//            return false;
//    }
//
//


}



