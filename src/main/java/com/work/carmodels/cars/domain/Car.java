package com.work.carmodels.cars.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(name = "car_brand")
    private String brand;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Column(name = "car_model")
    private String model;
    @NotNull
    @Column(name = "car_year")
    private int year;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Id
    private String vin;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Column(name = "car_regnum")
    private String regNum;

    protected Car() {
    }

    public Car(String brand, String model, int year, String vin, String regNum) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vin = vin;
        this.regNum = regNum;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String make) {
        this.brand = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(vin, car.vin) && Objects.equals(regNum, car.regNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, vin, regNum);
    }
}
