/**
 The runnable task for the daily reset of the customer ID generation.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/
import java.time.LocalDateTime;

public class DailyReset implements Runnable {
/**
  * Timing for the next scheduled run
  */
    private LocalDateTime nextRun;
/**
  * Creates the task for daily reset.
  * @param run The next timing to run the task.
  */
    public DailyReset(LocalDateTime run) {
        nextRun = run;
    }
/**
  * Runs the task.
  */
    public void run() {
        ReservationScheduler.dailySchedule();
    }
}