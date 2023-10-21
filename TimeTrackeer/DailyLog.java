package TimeTrackeer;

import java.util.Date;

public class DailyLog {
    private Date day;
    private TimeLine timeLine;
    public DailyLog(Date day) {
        this.day = day;
        timeLine = new TimeLine();
    }
    public Date day() { return day; }

    public DailyLog(Date day, TimeLine timeLine) {
        this.day = day;
        this.timeLine = timeLine;
    }
    public Date getDay() { return day; }
    public TimeLine getTimeLine() { return timeLine; }
}
