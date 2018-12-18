package taskmanager.view;

import taskmanager.controller.TaskController;

import javax.xml.bind.JAXBException;
import java.util.*;

// view
/**
 * Intended for interaction with user<br>
 * Corresponds to component <i>view</i> of <i>MVC</i> pattern
 * @see TaskController
 */
public class TaskManager {
    /**
     * Task controller for task manipulation
     */
    private TaskController taskCont;

    /**
     * Initializes task controller
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public TaskManager() throws JAXBException {
        taskCont = new TaskController();
    }

    /**
     * Launching dialog with task manager
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public void launch() throws JAXBException {
        printHelp();
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (!answer.equals("exit")) {
//            System.out.print(">> ");
            answer = scan.next();
            switch (answer) {
                case "help":
                    printHelp();
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "print":
                    printTasks();
                    break;
                case "exit":
                    taskCont.close();
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }

    // printings
    /**
     * Showing usage information
     */
    private void printHelp() {
        System.out.println("--------------------------------------------------------------------------------"
                + "\nWelcome to Task Manager!"
                + "\nCommands:"
                + "\n* help\t\t- to view this help"
                + "\n* add\t\t- for task addition"
                + "\n* remove\t- for task removing"
                + "\n* print\t\t- for task printing"
                + "\n* exit\t\t- for exit"
                + "\n--------------------------------------------------------------------------------");
    }
    /**
     * Showing task list
     */
    private void printTasks() {
        List<String> tasks = taskCont.getTasks();
        if (tasks.isEmpty())
            System.out.println("Task list is empty!");
        else {
            int i = 1;
            System.out.println("Task list:");
            for (String task : tasks)
                System.out.println(i++ + ") " + task);
        }
    }

    // actions
    /**
     * Task description and addition
     */
    private void addTask() {
        System.out.print("Task addition:\n");
        Scanner scan = new Scanner(System.in);

        System.out.print("- name: ");
        String name = scan.nextLine();

        System.out.print("- description: ");
        String desc = scan.nextLine();

        System.out.println("- date:");
        Calendar date = new GregorianCalendar();
        System.out.print("-- day: ");
        date.set(Calendar.DAY_OF_MONTH, scan.nextInt());
        System.out.print("-- month: ");
        date.set(Calendar.MONTH, scan.nextInt() - 1);
        System.out.print("-- year: ");
        date.set(Calendar.YEAR, scan.nextInt());

        System.out.println("- time:");
        System.out.print("-- hour: ");
        date.set(Calendar.HOUR_OF_DAY, scan.nextInt());
        System.out.print("-- minute: ");
        date.set(Calendar.MINUTE, scan.nextInt());
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        if (taskCont.addTask(name, desc, date))
            System.out.println("Added!");
        else
            System.out.println("Invalid time!");
    }
    /**
     * Task specifying and removing
     */
    private void removeTask() {
        System.out.print("TaskManager.mvc.Task removing:\n");
        Scanner scan = new Scanner(System.in);

        System.out.print("- ind: ");
        int ind = scan.nextInt();

        if (!taskCont.removeTask(ind - 1)) {
            System.out.println("No such index!\nYour tasks:");
            printTasks();
        }
        else
            System.out.println("Removed!");
    }
}
