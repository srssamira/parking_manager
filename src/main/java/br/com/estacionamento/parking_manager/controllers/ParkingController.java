package br.com.estacionamento.parking_manager.controllers;

import br.com.estacionamento.parking_manager.controllers.dtos.ParkingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private static List<ParkingDTO> vehicle = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> getVehicle() {
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/vehicle_license_plate/{vehicleLicensePlate}")
    public ResponseEntity<?> getPriceToPay(@PathVariable String vehicleLicensePlate) {
        try {
            ParkingDTO parkingDTO = vehicle.stream().filter(vehicle -> vehicle.getVehicleLicensePlate().equals(vehicleLicensePlate)).findFirst().get();
            parkingDTO.setMinuteCost(0.02);
            double totalPrice = parkingDTO.getTotalPrice();
            return ResponseEntity.ok(Map.of("totalPrice", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "vehicle not found"));
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public static void saveVehicle(@RequestBody ParkingDTO parkingDTO) {
        vehicle.add(parkingDTO);
    }

}