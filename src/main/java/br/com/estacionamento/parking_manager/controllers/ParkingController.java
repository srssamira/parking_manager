package br.com.estacionamento.parking_manager.controllers;

import br.com.estacionamento.parking_manager.controllers.dtos.ExitTimeDTO;
import br.com.estacionamento.parking_manager.controllers.dtos.ParkingDTO;
import br.com.estacionamento.parking_manager.controllers.dtos.LicensePlate;
import br.com.estacionamento.parking_manager.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<?> displayParking() {
        return ResponseEntity.ok(parkingService.getVehicle());
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<?> getPriceToPay(@PathVariable String licensePlate, @RequestBody @Valid ExitTimeDTO exitTimeDTO) {
        try {
            ParkingDTO parkingDTO = parkingService.searchVehicle(licensePlate).orElseThrow(() -> new RuntimeException("vehicle not found"));
            parkingDTO.setMinuteCost(0.02);
            double totalPrice = Duration.between(parkingDTO.getEntryTime(), parkingService.updateVehicleExitTime(licensePlate, exitTimeDTO)).toMinutes() * parkingDTO.getMinuteCost();
            return ResponseEntity.ok(Map.of("totalPrice", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "vehicle not found"));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveVehicle(@RequestBody @Valid LicensePlate licensePlate) {
        try {
            ParkingDTO parkingDTO = parkingService.saveVehicle(licensePlate.getLicensePlate());
            return ResponseEntity.status(201).body(parkingDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVehicle(@RequestParam(name = "licensePlate") String licensePlate) {
        try {
            parkingService.deleteVehicle(licensePlate);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @PatchMapping("/{licensePlate}/exit-time")
    public ResponseEntity<?> updateExitTime(@PathVariable String licensePlate, @RequestBody @Valid ExitTimeDTO exitTimeDTO) {
        try {
            parkingService.updateVehicleExitTime(licensePlate, exitTimeDTO);
            return ResponseEntity.status(200).body(Map.of("message", "success"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }
}