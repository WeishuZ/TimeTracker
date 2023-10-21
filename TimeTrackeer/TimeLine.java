package TimeTrackeer;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class TimeLine {
    ArrayDeque<TimeLog> timeLine;
    public TimeLine() {
        timeLine = new ArrayDeque<>();
    }
    /** add the time log, check whether the whole time zone is available.
     * if some time zone has been occupied, ask which descripution and time zone was occupied already,
     * then if reply Y, replace it. if N, cancel the add
     */

    private Date getTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("please specify the hour and minutes in the format of HH:mm:");
        String timeS = sc.next();
        return transToTime(timeS);
    }
    private Date transToTime(String timeS) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            return format.parse(timeS);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addTimeLog(String startS, String endS, Tag tag, String describe) {
        Date start = transToTime(startS);
        Date end = transToTime(endS);
        TimeLog newTimeLog = new TimeLog(start,end,tag,describe);
        insertLog = checkAvaliable(start,end);
        if(insertLog != ERROR) {
            timeLine.addAfter(insertLog,newTimeLog);
        }
    }
    private TimeLog checkAvaliable(Date start, Date end) {
        Iterator<TimeLog> iterator = timeLine.iterator();
        TimeLog prev = null;
        TimeLog after = iterator.next();
        while (iterator.hasNext()) {
            if (prev == null && end.before(after.getStart())) {
                return ERROR;
            } else if (end.before(after.getStart())) {
                if(start.after(prev.getEnd()) && end.before(after.getStart())) {
                    return prev;
                }
            }
            prev = after;
            after = iterator.next();
        }
        return after;
    }
}
