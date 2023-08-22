package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ReportInXMLTest {

    @Test
    public void whenRightGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Roman", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report reportInXML = new ReportInXML(store, parser);
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        String expect = String.format(Locale.ROOT,
                """
                        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                        <employees>
                            <employee name="%s">
                                <hired>%s</hired>
                                <fired>%s</fired>
                                <salary>%.1f</salary>
                            </employee>
                            <employee name="%s">
                                <hired>%s</hired>
                                <fired>%s</fired>
                                <salary>%.1f</salary>
                            </employee>
                        </employees>
                        """,
                worker1.getName(),
                new SimpleDateFormat(pattern).format(now.getTime()),
                new SimpleDateFormat(pattern).format(now.getTime()),
                worker1.getSalary(),
                worker2.getName(),
                new SimpleDateFormat(pattern).format(now.getTime()),
                new SimpleDateFormat(pattern).format(now.getTime()),
                worker2.getSalary());
        assertThat(reportInXML.generate(em -> true)).isEqualTo(expect);
    }
}