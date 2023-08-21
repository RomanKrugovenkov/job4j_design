package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
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

        JAXBContext context = JAXBContext.newInstance(Employee.class);
        this.marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var list = store.findBy(filter);
        StringBuilder rsl = new StringBuilder();
        try (StringWriter writer = new StringWriter()) {
            for (Employee employee : list) {
                marshaller.marshal(employee, writer);
            }
            rsl.append(writer.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }
}
