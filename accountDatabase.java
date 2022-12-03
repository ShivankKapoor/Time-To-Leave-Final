import java.io.*;
import java.util.ArrayList;

public class accountDatabase implements Serializable {

    private static final String FILENAME = "Accounts.tmp";
    private static ArrayList<User> dataBase = new ArrayList<User>();

    public accountDatabase(){
        
    }

    public void loadData() throws IOException, ClassNotFoundException{
        File f = new File(FILENAME);
        if (f.exists() && !f.isDirectory()) {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            dataBase = (ArrayList<User>) ois.readObject();
            ois.close();
        }else{
            saveAndQuit();
        }
    }

    public static void saveAndQuit() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dataBase);
        oos.close();
    }

    public static User loginToAccount(String email, String password) {
        email = email.toLowerCase();
        for (int i = 0; i < dataBase.size(); i++) {
            User tempUser = dataBase.get(i);
            if (tempUser.getEmail().equals(email)) {
                if (tempUser.loginRequest(password)) {
                    return tempUser;
                }
            }
        }
        return null;
    }
    public static User getUser(int index)
    {
        return dataBase.get(index);
    }
    public static boolean accountExist(String email){
        for (int i = 0; i < dataBase.size(); i++) {
            User tempUser = dataBase.get(i);
            if (tempUser.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean register(String email, String password) throws IOException {
        email = email.toLowerCase();
        if (!accountExist(email)) {
            User newUser = new User(email, password);
            dataBase.add(newUser);
            saveAndQuit();
            return true;
        } else {
            return false;
        }
    }
}