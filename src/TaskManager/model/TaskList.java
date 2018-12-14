package TaskManager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// model
@XmlRootElement
public class TaskList {
    @XmlElementWrapper(nillable = true)
    @XmlElement(name = "task")
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    // aux
    private List<String> taskNames() {
        return tasks.stream().map(Task::getName).collect(Collectors.toList());
    }

    // actions
    public boolean add(Task newTask) {
        if (taskNames().contains(newTask.getName()))
            return false;

        tasks.add(newTask);
        return true;
    }
    public boolean remove(String taskName) {
        if (!taskNames().contains(taskName))
            return false;

        tasks = tasks.stream().filter(task -> !task.getName().equals(taskName)).collect(Collectors.toList());
        return true;
    }

    // getters
    public List<Task> getTasks() {
        return tasks;
    }
}
