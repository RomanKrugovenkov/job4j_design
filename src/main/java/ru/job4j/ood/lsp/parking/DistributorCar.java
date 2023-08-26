package ru.job4j.ood.lsp.parking;

import java.util.List;

public class DistributorCar {

    public void distribute(Car car, List<AbstrParking> parkingList) {
        for (Parking parking : parkingList) {
            if (parking.addParking(car)) {
                break;
            }
        }
    }

    public void distList(List<Car> carList, List<AbstrParking> parkingList) {
        for (Car car : carList) {
            distribute(car, parkingList);
        }
    }
}
