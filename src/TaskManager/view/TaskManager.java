package TaskManager.view;

import TaskManager.controller.TaskController;
import TaskManager.model.Task;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

// view
public class TaskManager {
    private TaskController taskCont;

    public TaskManager() {
        taskCont = new TaskController();
    }

    // help
    private void printHelp() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Welcome to TaskManager.mvc.Task Manager!");
        System.out.println("Commands:");
        System.out.println("* help\t\t- to view this help");
        System.out.println("* add\t\t- for task addition");
        System.out.println("* remove\t- for task removing");
        System.out.println("* print\t\t- for task printing");
        System.out.println("* exit\t\t- for exit");
        System.out.println("--------------------------------------------------------------------------------");
    }

    // common
    public void launch() throws JAXBException {
        loadTasks();
        printHelp();
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (!answer.equals("exit")) {
            System.out.print(">> ");
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
                    saveTasks();
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }

    // actions
    // add fast checking and current day supporting
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

        if (taskCont.addTask(new Task(name, desc, date)))
            System.out.println("Added!");
        else
            System.out.println("Deny: task with name \"" + name + "\" already exists!");
    }
    // for debugging
    public void add(Task task) {
        taskCont.add(task);
    }
    private void removeTask() {
        System.out.print("TaskManager.mvc.Task removing:\n");
        Scanner scan = new Scanner(System.in);

        System.out.print("- name: ");
        String name = scan.nextLine();

        if (taskCont.removeTask(name))
            System.out.println("Removed!");
        else
            System.out.println("Deny: task with name \"" + name + "\" does not exist!");
    }

    // data manips
    private void saveTasks() throws JAXBException {
        System.out.println("Saving tasks...");
        taskCont.saveTasks();
        System.out.println("Done!");
    }
    private void loadTasks() throws JAXBException {
        System.out.println("Loading tasks...");
        taskCont.loadTasks();
        System.out.println("Done!");
    }

    // displaying
    private void printTasks() {
        List<Task> tasks = taskCont.getTasks();
        if (tasks.isEmpty())
            System.out.println("Task list is empty!");
        else {
            System.out.println("Task list:");
            for (Task task : tasks)
                System.out.println(task);
        }
    }
}
