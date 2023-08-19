package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportInXMLTest {

    @Test
    public void whenRightGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report reportInXML = new ReportInXML(store, parser);
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employee name=\"").append(worker1.getName()).append("\">")
                .append(System.lineSeparator())
                .append("    <hired>").append(new SimpleDateFormat(pattern).format(now.getTime())).append("</hired>")
                .append(System.lineSeparator())
                .append("    <fired>").append(new SimpleDateFormat(pattern).format(now.getTime())).append("</fired>")
                .append(System.lineSeparator())
                .append("    <salary>").append(worker1.getSalary()).append("</salary>")
                .append(System.lineSeparator()).append("</employee>")
                .append(System.lineSeparator());
        System.out.println(expect);
        System.out.println(reportInXML.generate(em -> true));
        /*assertThat(reportInXML.generate(em -> true)).isEqualTo(expect.toString());*/
    }
}