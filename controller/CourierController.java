package deliveryapp.app.controller;

import deliveryapp.app.PackageDTO.CourierDTO;
import deliveryapp.app.PackageDTO.ManagerDeliveredPackagesDTO;
import deliveryapp.app.PackageDTO.PackageDTO;
import deliveryapp.app.service.CourierService;
import deliveryapp.app.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {
    @Autowired
    private CourierService courierService;

    // CRUD Endpoints for Courier
    @PostMapping
    public ResponseEntity<CourierDTO> createCourier(@RequestBody CourierDTO courierDTO) {
        return ResponseEntity.ok(courierService.createCourier(courierDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourierDTO> getCourierById(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.getCourierById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourierDTO>> getAllCouriers() {
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourierDTO> updateCourier(
        @PathVariable Long id, 
        @RequestBody CourierDTO courierDTO
    ) {
        return ResponseEntity.ok(courierService.updateCourier(id, courierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return ResponseEntity.noContent().build();
    }

    // Custom Query Endpoints
    @GetMapping("/without-pending-packages")
    public ResponseEntity<List<CourierDTO>> getCouriersWithoutPendingPackages() {
        return ResponseEntity.ok(courierService.getCouriersWithoutPendingPackages());
    }

    @GetMapping("/managers-delivered-packages")
    public ResponseEntity<List<ManagerDeliveredPackagesDTO>> getManagersAndDeliveredPackageCount() {
        return ResponseEntity.ok(courierService.getManagersAndDeliveredPackageCount());
    }

    // Send Email Endpoint
    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmailToCouriers(
        @RequestBody Map<String, Object> payload
    ) {
        @SuppressWarnings("unchecked")
        List<Long> courierIds = (List<Long>) payload.get("courierIds");
        String subject = (String) payload.get("subject");
        String body = (String) payload.get("body");

        courierService.sendEmailToCouriers(courierIds, subject, body);
        return ResponseEntity.ok().build();
    }
}