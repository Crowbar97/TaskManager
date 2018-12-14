package TaskManager.controller;

import TaskManager.model.Task;
import TaskManager.model.TaskList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

// controller
public class TaskController {
    private TaskList taskList;
    private String xmlDataPath;

    public TaskController() {
        taskList = new TaskList();
        xmlDataPath = "./src/TaskManager/data/taskList.xml";
    }

    // actions
    public boolean addTask(Task task) {
        return taskList.add(task);
    }
    // for debugging
    public boolean add(Task task) {
        return taskList.add(task);
    }
    public boolean removeTask(String taskName) {
        return taskList.remove(taskName);
    }

    // data manips
    public void saveTasks() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TaskList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(taskList, new File(xmlDataPath));
    }
    public void loadTasks() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TaskList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        taskList = (TaskList) unmarshaller.unmarshal(new File(xmlDataPath));
    }

    // getters
    public List<Task> getTasks() {
        return taskList.getTasks();
    }
}
