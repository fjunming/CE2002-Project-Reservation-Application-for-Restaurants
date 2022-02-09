/**
 Class to handle the scheduling of Reservations.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ReservationScheduler {
    /**
    * Scheduling Queue that implements the ScheduledExecutorService format, with the schedule methods running tasks after a fixed input of delay.
    * The scheduler is tied to a single thread which monitors and execute tasks in the queue once the delay is up.
    */  
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    /**
    * HashMap of an Integer key and the scheduled task to keep track of the pointer pointing to the scheduled task.
    */
    private static HashMap<Integer, ScheduledFuture> schedule_queue;
    /**
    * Creates a ReservationScheduler object which comes with a schduling queue and to schedule the first DailyReset.
    */
    public ReservationScheduler() {
        schedule_queue = new HashMap<>();
        LocalDateTime nextRun = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));
        schedule_queue.put(1, scheduler.schedule(new DailyReset(nextRun), nextRun.getSecond(), TimeUnit.SECONDS));
    }
    /**
    * Insert a new reservation timeout schedule into the scheduler based on the customer's id and the delay.
    * @param cust_id The customer's id.
    * @param delay The amount of time in Duration object format to delay the task.
    */    
    public static void insertSchedule(int cust_id, Duration delay) {
        schedule_queue.put(cust_id, scheduler.schedule(new ReservationTimeOut(cust_id, false), delay.toSeconds(), TimeUnit.SECONDS));
    }
    /**
    * A method that runs daily to reset the generation of customer ID.
    */    
    public static void dailySchedule() {
        Reservation.resetCustID();
        LocalDateTime nextrun = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));
        long delay = Duration.between(LocalDateTime.now(), nextrun).toSeconds();
        schedule_queue.put(1, scheduler.schedule(new DailyReset(nextrun), delay, TimeUnit.SECONDS));
    }
    /**
    * Removes the chosen reservation timeout schedule based on the customer's id.
    * @param cust_id The customer's id.
    */
    public static void removeSchedule(int cust_id) {
        ScheduledFuture future_task = schedule_queue.remove(cust_id);
        future_task.cancel(false);
    }
}
