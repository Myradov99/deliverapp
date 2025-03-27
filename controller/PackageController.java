package deliveryapp.app.controller;
import deliveryapp.app.PackageDTO.CourierDTO;
import deliveryapp.app.PackageStatus.PackageStatus;
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
@RequestMapping("/api/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    // CRUD Endpoints for Package
    @PostMapping
    public ResponseEntity<PackageDTO> createPackage(@RequestBody PackageDTO packageDTO) {
        return ResponseEntity.ok(packageService.createPackage(packageDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageDTO> getPackageById(@PathVariable Long id) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @GetMapping
    public ResponseEntity<List<PackageDTO>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageDTO> updatePackage(
        @PathVariable Long id, 
        @RequestBody PackageDTO packageDTO
    ) {
        return ResponseEntity.ok(packageService.updatePackage(id, packageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }

    // Additional Package-related Endpoints
    @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<PackageDTO>> getPackagesForCourier(@PathVariable Long courierId) {
        return ResponseEntity.ok(packageService.getPackagesForCourier(courierId));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<PackageDTO>> getUnassignedPackages() {
        return ResponseEntity.ok(packageService.getUnassignedPackages());
    }
}
