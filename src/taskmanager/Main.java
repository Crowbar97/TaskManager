package taskmanager;

import taskmanager.view.TaskManager;
import javax.xml.bind.JAXBException;

/**
 * main class for task manager launching
 */
public class Main {
    /**
     * @param args command line arguments
     * @throws JAXBException when smth goes wrong with JAXB
     */
    public static void main(String[] args) throws JAXBException {
        new TaskManager().launch();
    }
}
