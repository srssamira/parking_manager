package br.com.estacionamento.parking_manager.controllers;

import br.com.estacionamento.parking_manager.controllers.dtos.ParkingDTO;
import br.com.estacionamento.parking_manager.controllers.dtos.LicensePlateDTO;
import br.com.estacionamento.parking_manager.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<?> displayParking() {
        return ResponseEntity.ok(parkingService.getVEHICLE());
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<?> getPriceToPay(String licensePlate) {
        try {
            ParkingDTO parkingDTO = parkingService.searchVEHICLE(licensePlate).get();

            parkingDTO.setMinuteCost(0.02);
            double totalPrice = parkingService.calculePriceToPay(parkingDTO);
            return ResponseEntity.ok(Map.of("totalPrice", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "vehicle not found"));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody LicensePlateDTO licensePlate) {
        try {
            ParkingDTO parkingDTO = parkingService.saveVEHICLE(licensePlate.getLicensePlateDTO());
            return ResponseEntity.status(201).body(parkingDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

}