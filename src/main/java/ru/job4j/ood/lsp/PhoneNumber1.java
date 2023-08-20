package ru.job4j.ood.lsp;

public class PhoneNumber1 {
    String number;

    public PhoneNumber1(String number) {
        if (number.length() > 7 || number.length() < 5) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.number = number;
    }

    public class ShortNumber extends PhoneNumber1 {
        String number;

        public ShortNumber(String number) {
            super(number);
            if (number.length() != 3) {
                throw new IllegalArgumentException("Invalid phone number");
            }
        }
    }
}
