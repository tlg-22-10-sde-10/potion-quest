import java.util.Date;

public class Timer implements Runnable {
    private long startTime;
    private long elapsedTime;
    private static long timeRemainingMin;
    private static long timeRemainingSec;

    public Timer(long startTime, long timeRemainingMin, long timeRemainingSec, long elapsedTime) {
        setStartTime(startTime);
        setTimeRemainingMin(timeRemainingMin);
        setTimeRemainingSec(timeRemainingSec);
        setElapsedTime(elapsedTime);
    }

    @Override
    public void run() {
    long totalTime = 7 * 60 * 1000;
        while (getElapsedTime() < totalTime) {
            setElapsedTime((new Date()).getTime() - getStartTime());
            setTimeRemainingSec((totalTime - getElapsedTime()) / 1000 % 60);
            setTimeRemainingMin((totalTime - getElapsedTime()) / 1000 / 60);
        }
        if (getElapsedTime() == (7*60 * 1000)) {
            System.out.println();
            System.out.println("==================================");
            System.out.println("You ran out of time...Game Over");
            System.out.println("==================================");
            System.out.println();
            GameClientUtil.endGameSequence();
        }
    }

    public static long getTimeRemainingMin() {
        return timeRemainingMin;
    }

    public void setTimeRemainingMin(long timeRemainingMin) {
        Timer.timeRemainingMin = timeRemainingMin;
    }

    public static long getTimeRemainingSec() {
        return timeRemainingSec;
    }

    public void setTimeRemainingSec(long timeRemainingSec) {
        Timer.timeRemainingSec = timeRemainingSec;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}

