package com.example.springbootprojectdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDto {
    private String name;

    @JsonProperty("car_number")
    private String carNumber;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "name='" + name + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
