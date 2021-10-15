package vehicles;
import vehicles.fuel.FuelTank;
import vehicles.fuel.FuelType;

    public class Race {
    public static int race (Vechicle v1, Vechicle v2) {
        return 1;
    }
    public static void main(String[] args) {
        FuelType petrol = new  FuelType("Diesel", 0.02, 1.8);
        FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
        Car myCar = new Car (new FuelType("diesel", 1.4, 0.01));
        FuelTank two_lt = new FuelTank(diesel, 2);
        FuelTank three_lt = new FuelTank(diesel, 3);
        diesel.setCostPerLiter(1.35);
        myCar.refuel(two_lt);
        double increase = 100;
        myCar.accellerate(increase);
        Car yourCar = new Car(diesel);
        yourCar.refuel(three_lt);
        yourCar = myCar;
        myCar.brake(30);
        yourCar.accellerate(50);
        myCar.brake(200);
        Bicycle B = new Bicycle(0, 1 ,1);
        race(myCar, B); // anche se questo metodo non fa nulla comunque possiamo passare qualsiasi veicolo!
        Truck T = new Truck(diesel);
        myCar.accellerate(9999999);
        boolean result = myCar.isFuelEmpty();
        result = yourCar.isFuelEmpty();
        //T avrà i metodi di car, i propri e quelli di vehicle, come B avrà i metodi della superclasse (vehicle) più i propri (quelli di bicycle -> posti in grassetto nel menù a tendina)


    }
}
