import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;


public class Alarm extends javax.swing.JFrame {

    static ArrayList<Alarm> alarmList = new ArrayList<Alarm>();

    private java.util.Timer timer;
    //timeItTakes is the value
    public Alarm(String timeString, int timeItTakes) {

        int hourForTime;
        int minutesForTime;
        timeItTakes/= 60;

        System.out.println(timeItTakes);
        String [] times = timeString.split("[:]");

        if(times[2].equals("AM") && Integer.parseInt(times[0]) == 12)
        {
            hourForTime = 0;
            minutesForTime = Integer.parseInt(times[1]);
        }
        else if(times[2].equals("PM"))
        {
            hourForTime = Integer.parseInt(times[0])+12;

            minutesForTime = Integer.parseInt(times[1]);


        }
        else
        {

            hourForTime = Integer.parseInt(times[0]);

            minutesForTime = Integer.parseInt(times[1]);
        }

        //math to find time to leave
        int minuteMath = minutesForTime + (hourForTime * 60);
        minuteMath -= timeItTakes;

        hourForTime = minuteMath / 60;
        minutesForTime = minuteMath % 60;



        System.out.println(hourForTime + " " + minutesForTime);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hourForTime);
        cal.set(Calendar.MINUTE,minutesForTime);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        //alarmGregDateTime.setTime(hourForTime, minutesForTime);

        Date alarmDateTime = cal.getTime();

        System.out.println(cal.getTime());

        timer = new java.util.Timer();

        //timer.schedule(new AlarmTask(), alarmDateTime);

        timer.schedule(new AlarmTask(), alarmDateTime);
    }

    class AlarmTask extends TimerTask
    {
        public void run()
        {
            JOptionPane.showMessageDialog(null, "You gotta leave");

            //System.exit(0);

        }
    }

    public static boolean createAlarm(String time,String start, String end) throws Exception {

        if(timeValid( time)) {
            alarmList.add(new Alarm(time, (int) Route.getTime(Route.modString(start), Route.modString(end))));
            return true;
        }
        else
        {
            System.out.println("time invalid");
            return false;
        }

        //alarmList.add(new Alarm("4:03:PM", 120));


    }



    //converting the hours to minutes
    int hrToMin(int hour)
    {
        return hour* 60;

    }

    static boolean timeValid(String time)
    {
        String [] timeArr = time.split("[:]");

        int hour = 0;
        int minute = 0;
        if(isInteger(timeArr[0]) && isInteger(timeArr[1]))
        {
            hour = Integer.parseInt(timeArr[0]);

            minute = Integer.parseInt(timeArr[1]);

        }
        else
        {
            System.out.println("Hour and minute not integers");
            return false;
        }

        if(hour <1 || hour > 12)
        {
            System.out.println("Hour is out of range");
            return false;
        }

        if(minute < 0 || minute > 60)
        {
            System.out.println("Minutes is out out of range");

            return false;
        }

        //System.out.println(timeArr[2]);
        if(!(timeArr[2].equals("AM") || timeArr[2].equals("PM")))
        {

            System.out.println("AM and PM is Invalid");

            return false;

        }



        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static void clearAlarms(){
        for(int i=0;i<alarmList.size();i++) {
            alarmList.set(i,null);
        }
    }


}

