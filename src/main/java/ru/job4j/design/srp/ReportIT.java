package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class ReportIT implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        String ln = System.lineSeparator();
        text.append("<html>").append(ln)
                .append("<head>").append(ln)
                .append("<title>Employees</title>").append(ln)
                .append("</head>").append(ln)
                .append("</body>").append(ln)
                .append("Name; Hired; Fired; Salary;").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(ln)
                .append("</html>").append(ln);
        return text.toString();
    }
}