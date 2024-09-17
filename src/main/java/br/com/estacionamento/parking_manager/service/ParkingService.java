package br.com.estacionamento.parking_manager.service;
import br.com.estacionamento.parking_manager.controllers.dtos.LicensePlate;
import br.com.estacionamento.parking_manager.controllers.dtos.ParkingDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    List<ParkingDTO> VEHICLE = new ArrayList<>();

    public List<ParkingDTO> getVehicle() {
        return VEHICLE;
    }

    public Optional<ParkingDTO> searchVehicle(String plate) {
        return VEHICLE.stream()
                .filter(parkingDTO -> parkingDTO.getVehicleLicensePlate().equalsIgnoreCase(plate))
                .findFirst();
    }

    public ParkingDTO saveVehicle(String plate) {
        Optional<ParkingDTO> vehicleOptional = searchVehicle(plate);

        if (vehicleOptional.isPresent()) {
            throw new RuntimeException("vehicle already exist");
        }

        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setVehicleLicensePlate(plate);
        parkingDTO.setEntryTime(LocalDateTime.now());
        parkingDTO.setExitTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59)));
        VEHICLE.add(parkingDTO);

        return parkingDTO;
    }

    public double calculePriceToPay(ParkingDTO parkingDTO) {
        return Duration.between(parkingDTO.getEntryTime(), parkingDTO.getExitTime()).toMinutes() * parkingDTO.getMinuteCost();
    }

    public void deleteVehicle(String plateToSearch) {
        VEHICLE = VEHICLE.stream().filter(plate -> !plate.getVehicleLicensePlate().equals(plateToSearch)).collect(Collectors.toList());
    }
}
