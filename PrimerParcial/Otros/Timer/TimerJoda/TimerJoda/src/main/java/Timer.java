import org.joda.time.DateTime;
import org.joda.time.Period;

public class Timer {
    private final DateTime start;
    private DateTime end;
    private Period elapsedTime;
    private long minutes, hours, days;
    private double seconds;

    public Timer(long start){
        this.start = new DateTime(start);
    }

    public Timer(){
        this(DateTime.now().getMillis());
    }

    public DateTime getEnd() {
        validate();
        return end;
    }

    public void stop(long end){
        if(start.isAfter(end)){
            throw new RuntimeException("Bad Origin");
        }
        this.end = new DateTime(end);
        elapsedTime = new Period(start,this.end);
        days = elapsedTime.getDays();
        hours = elapsedTime.getHours();
        minutes = elapsedTime.getMinutes();
        seconds = elapsedTime.getSeconds() + elapsedTime.getMillis() / (double) 1000;
    }
    public void stop(){
        stop(DateTime.now().getMillis());
    }

    public long getMinutes() {
        validate();
        return minutes;
    }

    public long getHours() {
        validate();
        return hours;
    }

    public long getDays() {
        validate();
        return days;
    }

    public double getSeconds() {
        validate();
        return seconds;
    }

    public void validate(){
        if(end == null)
            throw new RuntimeException("No stop");
    }

    @Override
    public String toString() {
        validate();
        Period elapsedTime = new Period(start,end);
        return String.format("(%d ms) %d Days %02d Hours %02d Minutes %.2f Seconds",
                elapsedTime.toStandardDuration().getMillis(), days, hours, minutes, seconds);
    }
}
