package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
        String expect = String.format(Locale.ROOT,
                "[{\"name\":\"%s\",\"hired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},"
                        + "\"fired\":{\"year\":%s,\"month\":%s,\"dayOfMonth\":%s,\"hourOfDay\":%s,\"minute\":%s,\"second\":%s},\"salary\":%.1f}]",
                worker1.getName(),
                worker1.getHired().get(Calendar.YEAR),
                worker1.getHired().get(Calendar.MONTH),
                worker1.getHired().get(Calendar.DAY_OF_MONTH),
                worker1.getHired().get(Calendar.HOUR_OF_DAY),
                worker1.getHired().get(Calendar.MINUTE),
                worker1.getHired().get(Calendar.SECOND),
                worker1.getFired().get(Calendar.YEAR),
                worker1.getFired().get(Calendar.MONTH),
                worker1.getFired().get(Calendar.DAY_OF_MONTH),
                worker1.getFired().get(Calendar.HOUR_OF_DAY),
                worker1.getFired().get(Calendar.MINUTE),
                worker1.getFired().get(Calendar.SECOND),
                worker1.getSalary());
        System.out.println(expect);

        String pattern = "'\"year\":'yyyy',\"month\":'M',\"dayOfMonth\":'dd',\"hourOfDay\":'H',\"minute\":'m',\"second\":'ss";
        System.out.println(new SimpleDateFormat(pattern).format(worker1.getHired().getTime()));

        assertThat(reportInJSON.generate(em -> true)).isEqualTo(expect);
    }
}