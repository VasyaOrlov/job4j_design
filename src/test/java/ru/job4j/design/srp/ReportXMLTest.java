package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReportXMLTest {

    @Test
    public void whenXMLTest() {
        MemStore store = new MemStore();
        Calendar dateHired = new GregorianCalendar(2022, Calendar.FEBRUARY, 24);
        Calendar dateFired = new GregorianCalendar(2022, Calendar.FEBRUARY, 25);
        dateHired.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+3")));
        dateFired.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+3")));
        Employee worker = new Employee("Ivan", dateHired, dateFired, 100);
        store.add(worker);
        String result;
        try {
            Report engine = new ReportXML(store);
            result = engine.generate(em -> true);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n<employees>")
                .append("\n    <employees name=\"").append(worker.getName()).append("\"")
                .append(" hired=\"")
                .append("2022-02-24T00:00:00+03:00")
                .append("\" fired=\"")
                .append("2022-02-25T00:00:00+03:00")
                .append("\" salary=\"").append(worker.getSalary())
                .append("\"/>")
                .append("\n</employees>\n");
        assertThat(result, is(expect.toString()));
    }
}