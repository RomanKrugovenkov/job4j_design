package ru.job4j.ood.lsp;

public class Account3 {

    protected float account = 0;

    public Account3(float account) {
        if (account < 0) {
            throw new IllegalArgumentException("count cannot be less than zero");
        }
        this.account = account;
    }


    public class VipAccount3 extends Account3 {

        public VipAccount3(float account) {
            super(account);
        }
    }
}
