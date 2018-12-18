package taskmanager.controller;

import taskmanager.model.Task;
import taskmanager.model.TaskStore;
import taskmanager.systems.NotificationSystem;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

// controller
/**
 * Intended for communication {@link taskmanager.view.TaskManager} with {@link TaskStore}<br>
 * Corresponds to component <i>controller</i> of <i>MVC</i> pattern
 * @see Task
 * @see TaskStore
 */
public class TaskController {
    /**
     * Task store for task manipulation
     */
    private TaskStore taskStore;
    /**
     * Stores path to XML-file with tasks
     */
    private String xmlDataPath;
    /**
     * Notifies about tasks
     */
    private NotificationSystem notSys;

    /**
     * Initializes fields and loads tasks
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public TaskController() throws JAXBException {
        taskStore = new TaskStore();
        xmlDataPath = "./src/taskmanager/data/taskList.xml";
        taskStore.load(xmlDataPath);
        notSys = new NotificationSystem(taskStore);
    }

    // getters
    /**
     * @return list of strings of stored tasks info
     */
    public List<String> getTasks() {
        return taskStore.getTasks().stream().map(Task::toString).collect(Collectors.toList());
    }

    // actions
    /**
     * adds a single task to task list
     * @param name task name
     * @param desc task description
     * @param date task date
     * @return result of addition
     */
    public boolean addTask(String name, String desc, Calendar date) {
        if (taskStore.add(new Task(name, desc, date))) {
            notSys.refresh();
            return true;
        }
        return false;
    }
    /**
     * removes specified task from task list
     * @param taskInd task index
     * @return result of removal
     */
    public boolean removeTask(int taskInd) {
        if (taskStore.remove(taskInd)) {
            notSys.refresh();
            return true;
        }
        return false;
    }

    // close
    /**
     * closes notification system and saves tasks
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public void close() throws JAXBException {
        notSys.terminate();
        taskStore.save(xmlDataPath);
    }
}
