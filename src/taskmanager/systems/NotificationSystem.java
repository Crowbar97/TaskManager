package taskmanager.systems;

import taskmanager.model.Task;
import taskmanager.model.TaskStore;

import java.util.Date;

/**
 * Intended for task notification
 * @see Task
 * @see TaskStore
 */
public class NotificationSystem implements Runnable {
    /**
     * target for observing
     */
    private TaskStore target;
    /**
     * specifies that task-checking thread must be terminated
     */
    private boolean interrupted;
    /**
     * defines the next action after waiting
     */
    private boolean timeForTask;

    /**
     * initializes target by passed parameter
     * @param target target for observation
     */
    public NotificationSystem(TaskStore target) {
        this.target = target;
        interrupted = false;
        new Thread(this).start();
    }

    /**
     * terminates task-checking thread
     */
    public synchronized void terminate() {
        interrupted = true;
        notify();
    }
    /**
     * refresh task-checking thread for checking a new task to wait
     */
    public synchronized void refresh() {
        timeForTask = false;
        notify();
    }

    /**
     * runs task-checking thread
     */
    @Override
    public synchronized void run() {
//        System.out.println("Thread starts...");

        try {
            while (!interrupted) {
                while (target.getTasks().isEmpty()) {
                    System.out.println("Task list is empty!\nWaiting for task addition...");
                    wait();
                    if (interrupted)
                        break;
                }
                if (interrupted)
                    break;

                Task nextTask = target.getTasks().get(0);
                timeForTask = true;

                long time = nextTask.getDate().getTimeInMillis() - new Date().getTime();
                if (time >= 0) {
                    System.out.println("Next task is \"" + nextTask.getName() + "\" in " + getTime(time));
                    wait(time);
                    if (interrupted)
                        break;
                }

                if (timeForTask) {
                    System.out.println("It's time for task \"" + nextTask + "\"!");
                    target.remove(0);
                }

                System.out.println("Next task switching...");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }

//        System.out.println("Thread ends...");
    }

    // aux
    /**
     * returns time in string format
     * @param time time in milliseconds
     * @return string representation of passed time argument
     */
    private static String getTime(long time) {
        long s = time / 1000;
        long m = s / 60;
        long h = m / 60;
        long d = h / 24;
        return d + "d " + (h % 24) + "h " + (m % 60) + "m " + (s % 60) + "s";
    }
}
