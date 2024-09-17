package br.com.estacionamento.parking_manager.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingDTO {

    @NotBlank(message = "numero da placa não pode ser nulo")
    @NotNull(message = "numero da placa obrigatório")
    @Size(min = 7, max = 7, message = "formato da placa invalido")
    @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$", message = "formato da placa invalido, siga o padrão mercosul")
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
