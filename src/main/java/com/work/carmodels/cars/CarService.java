package com.work.carmodels.cars;

import com.work.carmodels.cars.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Car addCar(Car car) {
        if (carRepository.findAll().stream().anyMatch(c -> c.getVin().equals(car.getVin()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car with the same VIN number already exists!");
        } else if (carRepository.findAll().stream().anyMatch(c -> c.getRegNum().equals(car.getRegNum()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car with the same registration number exists!");
        } else {
            return carRepository.save(car);
        }

    }

    public List<Car> findCars(String reqBrand) {
        List<Car> output = carRepository.findAll().stream().filter(car -> car.getBrand().equalsIgnoreCase(reqBrand)).toList();
        if (output.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cars were found!");
        } else {
            return output;
        }
    }

    public List<Car> exportContents() {
        return carRepository.findAll();
    }

    public void importContents(List<Car> contents) {
        carRepository.deleteAll();
        carRepository.saveAll(contents);
    }

}
