package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private final Marshaller marshaller;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
        try {
            this.marshaller = JAXBContext.newInstance(Employee.Employees.class).createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employee.Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
