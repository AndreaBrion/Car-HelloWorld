package Unive.vehicles;

import Unive.vehicles.autovehicles.Car;
import Unive.vehicles.fuel.FuelType;
import org.junit.Test;

public class CarTester {
    //Vediamo JUnit
    @Test
    public void testAcc () throws TextException, ImpossibleAccellerateException {
        Car m = new Car(0, new FuelType("diesel", 0.15, 1.1));
        m.accellerate(10);
        if (m.getSpeed()!=10.0)
            throw new TextException("Wrong accelleration");
        //possiamo fare questo if anche come
        //assert m.getSpeed()==10.0
    }
}
