package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ReportInJSONTest {

    @Test
    public void whenRightGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Roman", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report reportInJSON = new ReportInJSON(store, parser);
        System.out.println(reportInJSON.generate(em -> true));
        System.out.println(parser.parse(worker1.getHired()));
        String expect = String.format(Locale.ROOT,
                "[{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f},{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}]",
                worker1.getName(),
                parser.parse(worker1.getHired()),
                parser.parse(worker1.getFired()),
                worker1.getSalary(),
                worker2.getName(),
                parser.parse(worker2.getHired()),
                parser.parse(worker2.getFired()),
                worker2.getSalary());
        assertThat(reportInJSON.generate(em -> true)).isEqualTo(expect);
    }
}