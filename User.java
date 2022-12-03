
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User implements Serializable {
    private String Email;
    private String Password;


    private ArrayList<Event> userSchedule ;


    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
        this.userSchedule = new ArrayList<Event>();

    }



    public boolean loginRequest(String Password) {
        return (this.Password.equals(Password));
    }

    public String getEmail() {
        return Email;
    }

    public void addEvent(Event what) {
        userSchedule.add(what);

        sort();
    }

    public int numOfEvents() {
        return userSchedule.size();
    }

    private void sort() {
        Collections.sort(userSchedule, Comparator.comparingInt(Event::getTimeVal));
    }

    public void removeEvent(int what) {
        userSchedule.remove(what);
    }

    public String[][] getScheduleInfo() throws Exception {
        ArrayList<String> timings = buildTiming();
        String[][] r = new String[userSchedule.size()][5];
        for (int i = 0; i < userSchedule.size(); i++) {
            r[i][0] = i + "";
            r[i][1] = userSchedule.get(i).getName();
            r[i][2] = userSchedule.get(i).getBuilding();
            r[i][3] = userSchedule.get(i).getTimeString();
            r[i][4] = timings.get(i);

        }
        Alarm.clearAlarms();
        for (int i = 1; i < userSchedule.size(); i++) {
            Alarm.createAlarm(r[i][3], r[i-1][2], r[i][2]);
        }

        return r;
    }

    private ArrayList<String> buildTiming() throws Exception {

        ArrayList<String> timings = new ArrayList<String>();
        for (int i = 0; i < userSchedule.size() - 1; i++) {
            String origin = fixInputForCall(userSchedule.get(i).getBuilding());
            String destination = fixInputForCall(userSchedule.get(i + 1).getBuilding());
            long what = Route.getTime(origin, destination);
            int whatInMins = (int) (what / 60);

            timings.add(whatInMins + " Mins");
        }
        timings.add("N/A");
        return timings;
    }


    private String fixInputForCall(String what) {
        what = what.replaceAll(" ", "+");
        return what;
    }
}