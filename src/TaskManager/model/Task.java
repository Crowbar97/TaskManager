package TaskManager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement
public class Task {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "desc")
    private String desc;
    @XmlElement(name = "date")
    private Calendar date;

    // for jaxb
    Task() {}
    public Task(String name, String desc, Calendar date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    // getters
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + desc + " - " + date.getTime();
    }
}
