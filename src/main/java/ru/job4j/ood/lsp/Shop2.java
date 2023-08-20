package ru.job4j.ood.lsp;

public class Shop2 {

    public int orderAmount(int sumOrders, int sumServices) {
        int sum = sumOrders + sumServices;
        if (sum < 100) {
            throw new IllegalArgumentException("order amount is not enough");
        }
        return sum;
    }

    public class VipShop extends Shop2 {

        @Override
        public int orderAmount(int sumOrders, int sumServices) {
            int sum = sumOrders + sumServices;
            if (sum < 500) {
                throw new IllegalArgumentException("order amount is not enough");
            }
            return sum;
        }
    }
}
