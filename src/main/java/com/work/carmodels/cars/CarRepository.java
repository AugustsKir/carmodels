package com.work.carmodels.cars;

import com.work.carmodels.cars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findCarsByVin(String vin);

    List<Car> findCarsByRegNum(String regNum);

}