package TimeTrackeer;
import java.util.Date;
public class TimeLog {
    private Date start;
    private Date end;
    private Tag tag;
    private String describe;

    public TimeLog(Date start, Date end, Tag tag, String describe) {
        this.start = start;
        this.end = end;
        this.tag = tag;
        this.describe = describe;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnd() {
        return end;
    }
}
