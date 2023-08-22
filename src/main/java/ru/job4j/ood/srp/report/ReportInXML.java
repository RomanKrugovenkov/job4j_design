package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportInXML implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Marshaller marshaller;

    public ReportInXML(Store store, DateTimeParser<Calendar> dateTimeParser) throws JAXBException {
        this.store = store;
        this.dateTimeParser = dateTimeParser;

        JAXBContext context = JAXBContext.newInstance(Employee.Employees.class);
        this.marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employee.Employees employees = new Employee.Employees(store.findBy(filter));
        String rsl = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            rsl = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Roman", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report reportInXML = new ReportInXML(store, parser);

        System.out.println(reportInXML.generate(em -> true));
    }
}
