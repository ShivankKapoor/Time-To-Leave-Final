import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event implements Serializable {
    String name;
    String building;
    String hour;
    String min;
    String amPm;
    int timeVal = 0;

    Calendar activeTimeValue;

    public Event(String name, String building, String hour, String min, String amPm) {
        this.name = name;
        this.building = building;
        this.hour = hour;
        this.min = min;
        this.amPm = amPm;
        int twentyFour = Integer.parseInt(hour);
        if (amPm.equals(":PM")) {
            timeVal += 720;
            twentyFour=+12;
        }
        timeVal += Integer.parseInt(min);
        if (Integer.parseInt(hour) != 12) {
            timeVal += (Integer.parseInt(hour) * 60);
        }
        Calendar timingVal = Calendar.getInstance();
        timingVal.set(Calendar.HOUR_OF_DAY,twentyFour);
        timingVal.set(Calendar.MINUTE,(Integer.parseInt(this.min)));
    }

    public int getTimeVal() {
        return timeVal;
    }

    public String getName(){
        return name;
    }

    public String getBuilding(){
        return building;
    }

    public String getTimeString(){
        return (hour+":"+min+":"+amPm);
    }
}
