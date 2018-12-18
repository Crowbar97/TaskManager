package taskmanager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

/**
 * Intended for task representation
 */
@XmlRootElement
public class Task implements Comparable {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "desc")
    private String desc;
    @XmlElement(name = "date")
    private Calendar date;

    /**
     * Intended for JAXB correct processing
     */
    Task() {}
    public Task(String name, String desc, Calendar date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    // getters
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public Calendar getDate() {
        return date;
    }

    @Override
    public int compareTo(Object o) {
        return date.compareTo(((Task) o).getDate());
    }

    @Override
    public String toString() {
        return name + ": " + desc + " - " + date.getTime();
    }
}
