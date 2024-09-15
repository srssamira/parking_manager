package br.com.estacionamento.parking_manager.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingDTO {

    private String VehicleLicensePlate;

    @NotNull
    @NotBlank
    private LocalDateTime entryTime;

    @NotNull
    @NotBlank
    private LocalDateTime exitTime;

    @NotNull
    @NotBlank
    private double minuteCost;

    public ParkingDTO() {
    }

    public String getVehicleLicensePlate() {
        return VehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        VehicleLicensePlate = vehicleLicensePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setMinuteCost(double minuteCost) {
        this.minuteCost = minuteCost;
    }

    public double getMinuteCost() {
        return minuteCost;
    }
}
