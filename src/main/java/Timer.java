import java.util.Date;

public class Timer implements Runnable {

    private static long startTime = System.currentTimeMillis();
    private static long elapsedTime = 0L;
    private static long timeRemainingMin;
    private static long timeRemainingSec;

    @Override
    public void run() {
    long totalTime = 7 * 60 * 1000;
        while (elapsedTime < totalTime) {
            elapsedTime = (new Date()).getTime() - startTime;
            timeRemainingSec = (totalTime - elapsedTime) / 1000 % 60;
            timeRemainingMin = (totalTime - elapsedTime) / 1000 / 60;
        }
        if (elapsedTime == (7*60 * 1000)) {
            System.out.println("==================================");
            System.out.println("You ran out of time...Game Over");
            System.out.println("==================================");
//            gameClient.setQuitGame(true);
            GameClientUtil.gameExitMessage();
            System.exit(0);
        }
    }

    public static long getTimeRemainingMin() {
        return timeRemainingMin;
    }

    public static void setTimeRemainingMin(int timeRemainingMin) {
        Timer.timeRemainingMin = timeRemainingMin;
    }

    public static long getTimeRemainingSec() {
        return timeRemainingSec;
    }

    public static void setTimeRemainingSec(int timeRemainingSec) {
        Timer.timeRemainingSec = timeRemainingSec;
    }
}

