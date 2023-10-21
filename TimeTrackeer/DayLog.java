package TimeTrackeer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DayLog {
    private static ArrayDeque<DailyLog> daylog = new ArrayDeque<>();
    //private Date earlyest, lastest;


    /** create a new dailyLog and add it to the DayLog by the date */
    public static void addDailyLog(Date day, TimeLine timeLine){
        DailyLog d = new DailyLog(day,timeLine);
        DailyLog newDay = new DailyLog(day,timeLine);
        for(int i = 0; i < daylog.size();i++) {
            DailyLog current = daylog.get(i);
            if (current.day().before(day)) {
                daylog.addBefore(i,newDay);
            }
        }
        daylog.addLast(newDay);
    }
     /** remove the spercified date's log, if doesn't exsist, throw error */
     public static void removeDay(Date day) {
     }

    private TimeLine getTimeLine(Date day) {
        for(DailyLog i : daylog) {
            if(i.getDay() == day) {
                return i.getTimeLine();
            }
        }
        return null;
    }
    public class Printer {
        int hourDiviNum = 2;
        public void PrintTimeLine(Date day) {
            TimeLine timeLine = getTimeLine(day);
            if (timeLine != null) {

            }
        }
    }

}


