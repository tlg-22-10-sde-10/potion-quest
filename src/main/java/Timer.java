import java.util.Date;

public class Timer implements Runnable {
    private static long startTime = System.currentTimeMillis();
    private static long elapsedTime = 0L;
    private static long timeRemainingMin = 7L;
    private static long timeRemainingSec = 0L;

    @Override
    public void run() {
    long totalTime = 7 * 60 * 1000;
        while (elapsedTime < totalTime) {
            elapsedTime = (new Date()).getTime() - startTime;
            timeRemainingSec = (totalTime - elapsedTime) / 1000 % 60;
            setTimeRemainingSec(timeRemainingSec);
            timeRemainingMin = (totalTime - elapsedTime) / 1000 / 60;
            setTimeRemainingMin(timeRemainingMin);
        }
        if (elapsedTime == (7*60 * 1000)) {
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

    public static void setTimeRemainingSec(long timeRemainingSec) {
        Timer.timeRemainingSec = timeRemainingSec;
    }
}

