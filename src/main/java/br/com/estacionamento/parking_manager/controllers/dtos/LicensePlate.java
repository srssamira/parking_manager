package br.com.estacionamento.parking_manager.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LicensePlate {

    @NotBlank(message = "numero da placa não pode ser nulo")
    @NotNull(message = "numero da placa obrigatório")
    @Size(min = 7, max = 7, message = "formato da placa invalido")
    @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$", message = "formato da placa invalido, siga o padrão mercosul")
    private String licensePlate;

    public LicensePlate() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlateDTO) {
        this.licensePlate = licensePlateDTO;
    }
}
