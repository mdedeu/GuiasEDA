public class Timer {
    private final long start;
    private long end;
    private int stops = 0;

    public Timer(){
        this.start = System.currentTimeMillis();
    }

    public Timer(long start){
        this.start = start;
    }

    public void stop(long end){
        this.end = end;
        addStop();
        check();
    }

    public void stop(){
        this.end = System.currentTimeMillis();
        addStop();
        check();
    }

    public void addStop(){
        stops++;
        if(stops > 1)
            throw new IllegalStateException();
    }

    private void check(){
        if(end < start)
            throw new IllegalStateException();
    }

    public long getElapsedTime(){
        return end - start;
    }

    @Override
    public String toString() {
        long time = end - start;
        double seconds = time / (double) 1000;
        long days = (int) (seconds / (3600 * 24));
        long hours = (int) ((seconds % (3600 * 24))) / 3600;
        long minutes = (int) (seconds % 3600) / 60;
        double secondsPrint =  seconds % 60;
        return String.format("(%s ms) ", time) +
                String.format("%s Days %s Hours %s Minutes %.2f Seconds", days, hours, minutes, secondsPrint);
    }

}


