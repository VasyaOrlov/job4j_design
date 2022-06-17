package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class ReportITTest {

    @Test
    public void itTest() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportIT(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>Employees</title>").append(ln)
                .append("</head>").append(ln)
                .append("</body>").append(ln)
                .append("Name; Hired; Fired; Salary;").append(ln)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(ln)
                .append("</body>").append(ln)
                .append("</html>").append(ln);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}