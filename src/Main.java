import TaskManager.view.TaskManager;
import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws JAXBException {
        TaskManager tm = new TaskManager();

        tm.launch();

        // += id

        /*tm.add(new Task("milk", "buy", new GregorianCalendar()));
        tm.add(new Task("bread", "buy x2", new GregorianCalendar()));
        tm.add(new Task("eat", "milk and bread", new GregorianCalendar()));

        tm.saveTasks();*/
    }
}
