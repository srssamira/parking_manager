package br.com.estacionamento.parking_manager.controllers;

import br.com.estacionamento.parking_manager.controllers.dtos.ParkingDTO;
import br.com.estacionamento.parking_manager.controllers.dtos.LicensePlate;
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
        return ResponseEntity.ok(parkingService.getVehicle());
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<?> getPriceToPay(@PathVariable String licensePlate) {
        try {
            ParkingDTO parkingDTO = parkingService.searchVehicle(licensePlate).orElseThrow(() -> new RuntimeException("vehicle not found"));
            parkingDTO.setMinuteCost(0.02);
            double totalPrice = parkingService.calculePriceToPay(parkingDTO);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteVehicle(@RequestParam(name = "licensePlate") String licensePlate) {
        try {
            parkingService.deleteVehicle(licensePlate);
            return ResponseEntity.status(201).body(Map.of("message", "vehicle deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{plateToSearch}")
    public ResponseEntity<?> updateVehicle (@PathVariable String plateToSearch, @RequestBody @Valid ParkingDTO updateParkingDTO) {
        try {
            parkingService.putVehicle(plateToSearch, updateParkingDTO);
            return ResponseEntity.status(201).body(Map.of("message", "vehicle updated"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", "vehicle not found to update"));
        }
    }

}