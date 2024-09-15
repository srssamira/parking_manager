package br.com.estacionamento.parking_manager.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LicensePlateDTO {

    @NotBlank(message = "numero da placa não pode ser nulo")
    @NotNull(message = "numero da placa obrigatório")
    @Size(min = 7, max = 7, message = "formato da placa invalido")
    private String licensePlate;

    public LicensePlateDTO() {
    }

    public String getLicensePlateDTO() {
        return licensePlate;
    }

    public void setLicensePlateDTO(String licensePlateDTO) {
        this.licensePlate = licensePlateDTO;
    }
}
