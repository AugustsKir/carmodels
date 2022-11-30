package com.work.carmodels.cars;

import com.work.carmodels.cars.domain.Car;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{brand}")
    private List<Car> findCars(@PathVariable String brand) {
        return carService.findCars(brand);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/export")
    private List<Car> exportContents() {
        return carService.exportContents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/add")
    private Car addCar(@RequestBody @Valid Car car) {
        return carService.addCar(car);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/import")
    private void importContents(@RequestBody List<@Valid Car> contents) {
        carService.importContents(contents);
    }


}
