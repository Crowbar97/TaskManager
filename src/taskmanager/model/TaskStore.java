package taskmanager.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// model
/**
 * Intended for task storage and getting<br>
 * Corresponds to component <i>model</i> of <i>MVC</i> pattern
 * @see Task
 * @see TaskSet
 */
public class TaskStore {
    /**
     * stores sorted set of tasks
     */
    private TaskSet taskSet;

    /**
     * initializes task set
     */
    public TaskStore() {
        taskSet = new TaskSet();
    }

    // getters
    /**
     * @return array list of tasks
     */
    public List<Task> getTasks() {
        return new ArrayList<>(taskSet);
    }

    // actions
    /**
     * adds new task
     * @param newTask new task to add
     * @return result of addition
     */
    public boolean add(Task newTask) {
        if (newTask.getDate().getTime().before(new Date()))
            return false;

        taskSet.add(newTask);
        return true;
    }
    /**
     * removes specified task
     * @param taskInd task index to remove
     * @return result of removal
     */
    public boolean remove(int taskInd) {
        if (taskInd < 0 || taskInd >= taskSet.size())
            return false;

        int i = 0;
        for (Task task : taskSet) {
            if (i++ == taskInd) {
                taskSet.remove(task);
                break;
            }
        }
        return true;
    }

    // data

    /**
     * saves tasks to XML-file
     * @param xmlDataPath specifies path to XML-file
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public void save(String xmlDataPath) throws JAXBException {
        System.out.println("Saving...");
        JAXBContext context = JAXBContext.newInstance(TaskSet.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(taskSet, new File(xmlDataPath));
        System.out.println("Done!");
    }
    /**
     * loads tasks from XML-file
     * @param xmlDataPath specifies path to XML-file
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public void load(String xmlDataPath) throws JAXBException {
        System.out.println("Loading...");
        JAXBContext context = JAXBContext.newInstance(TaskSet.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        taskSet = (TaskSet) unmarshaller.unmarshal(new File(xmlDataPath));
        System.out.println("Done!");
    }
}
