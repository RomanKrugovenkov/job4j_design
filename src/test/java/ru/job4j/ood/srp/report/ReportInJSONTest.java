package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportInJSONTest {

    @Test
    public void whenRightGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report reportInJSON = new ReportInJSON(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{\"name\":\"").append(worker1.getName()).append("\",")
                .append("\"hired\":{\"year\":").append(worker1.getHired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker1.getHired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker1.getHired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker1.getHired().get(Calendar.HOUR)).append(",")
                .append("\"minute\":").append(worker1.getHired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker1.getHired().get(Calendar.SECOND)).append("},")
                .append("\"fired\":{\"year\":").append(worker1.getFired().get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker1.getFired().get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker1.getFired().get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker1.getFired().get(Calendar.HOUR)).append(",")
                .append("\"minute\":").append(worker1.getFired().get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker1.getFired().get(Calendar.SECOND)).append("},")
                .append("\"salary\":")
                .append(worker1.getSalary()).append("}")
                .append("]");
        System.out.println(expect);
        assertThat(reportInJSON.generate(em -> true)).isEqualTo(expect.toString());
    }
}