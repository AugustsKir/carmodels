package com.work.carmodels.cars;

import com.work.carmodels.cars.domain.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    CarRepository carRepository;


    @Test
    public void canReturnCarList() {
        List<Car> carList = new ArrayList<>();
        Car car = new Car("BMW", "330i", 2005, "FDSF87324REW", "KI3451");
        Car car2 = new Car("BMW", "335i", 2006, "543FDRE45322", "FR21345");
        Car car3 = new Car("Honda", "Accord", 2009, "GHJHGJ35543", "KI4320");
        carList.add(car3);
        carList.add(car2);
        carList.add(car);

        when(carService.exportContents()).thenReturn(carList);

        List<Car> allCars = carService.exportContents();

        Assertions.assertEquals(3, allCars.size());

        verify(carRepository, times(1)).findAll();


    }

    @Test
    public void canAddCars() {
        Car car = new Car("BMW", "330i", 2005, "FDSF87324REW", "KI3451");
        carService.addCar(car);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void canImportData() {
        List<Car> carList = new ArrayList<>();
        Car car = new Car("BMW", "330i", 2005, "FDSF87324REW", "KI3451");
        Car car2 = new Car("BMW", "335i", 2006, "543FDRE45322", "FR21345");
        Car car3 = new Car("Honda", "Accord", 2009, "GHJHGJ35543", "KI4320");
        carList.add(car3);
        carList.add(car2);
        carList.add(car);

        carService.importContents(carList);

        verify(carRepository, times(1)).deleteAll();
        verify(carRepository, times(1)).saveAll(carList);


    }

    @Test
    public void canFindCars() {
        Car car = new Car("Honda", "Accord", 2009, "GHJHGJ35543", "KI4320");
        Car car2 = new Car("BMW", "335i", 2006, "543FDRE45322", "FR21345");
        List<Car> cars = new ArrayList<>(Arrays.asList(car, car2));
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> result = carService.findCars("honda");
        Assertions.assertEquals("Honda", result.get(0).getBrand());
    }
}
