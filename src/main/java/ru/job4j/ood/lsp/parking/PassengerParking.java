package ru.job4j.ood.lsp.parking;

public class PassengerParking extends AbstrParking {

    public PassengerParking(int numOfPlaces) {
        super(numOfPlaces);
    }

    @Override
    public boolean addParking(Car car) {
        boolean rsl = false;
        if (car.getSizeCar() == 1) {
            for (int i = 0; i < places.length; i++) {
                if (places[i] == null) {
                    places[i] = car;
                    return true;
                }
            }
        } else {
            rsl = addParkingCargo(car);
        }
        return rsl;
    }

    public boolean addParkingCargo(Car car) {
        boolean rsl = false;
        int carSize = car.getSizeCar();
        int freePlaces = 0;
        for (int i = 0; i < places.length; i++) {
             if (places[i] == null) {
                freePlaces++;
                if (freePlaces == carSize) {
                    for (int j = i; j > i - carSize; j--) {
                        places[j] = car;
                    }
                    rsl = true;
                }
            } else {
                freePlaces = 0;
            }
        }
        return rsl;
    }
}
