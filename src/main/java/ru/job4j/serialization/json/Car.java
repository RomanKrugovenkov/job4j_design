package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "Car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean credit;
    private int speed;
    private String name;
    private Contact contact;
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() { }

    public Car(boolean credit, int speed, String name, Contact contact, String[] options) {
        this.credit = credit;
        this.speed = speed;
        this.name = name;
        this.contact = contact;
        this.options = options;
    }

    public static void main(String[] args) throws JAXBException {
        Car car = new Car(
                true,
                200,
                "Nissan",
                new Contact("22-222"),
                new String[] {"mats", "tyres"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(result)) {
            Car resultCar = (Car) unmarshaller.unmarshal(reader);
            System.out.println(resultCar);
        }
    }

    @Override
    public String toString() {
        return "Car{"
                + "credit=" + credit
                + ", speed=" + speed
                + ", name=" + name
                + ", contact=" + contact
                + ", options=" + Arrays.toString(options)
                + '}';
    }
}
