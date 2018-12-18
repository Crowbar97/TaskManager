package taskmanager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeSet;

/**
 * Intended for task storage
 * @see Task
 */
@XmlRootElement
public class TaskSet extends TreeSet<Task> {
    /**
     * Intended for JAXB correct processing
     * @return this set of tasks
     */
    @XmlElement(name = "task")
    public TreeSet<Task> getIt() {
        return this;
    }
}
