package ru.job4j.ood.dip.examples;

public class LogisticsByDIP {

    public void carry(Transport transport, Product product) {
        transport.go();
        /*везем продукты на транспорте*/
    }

    public abstract class Product {
        String name;
        int weight;
    }

    interface Transport {

        void go();
    }
}
