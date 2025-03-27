package deliveryapp.app.service;


import deliveryapp.app.PackageStatus.PackageStatus;

import deliveryapp.app.PackageDTO.CourierDTO;
import deliveryapp.app.PackageDTO.ManagerDeliveredPackagesDTO;
import deliveryapp.app.PackageDTO.PackageDTO;
import deliveryapp.app.entity.Courier;
import deliveryapp.app.entity.Package; 
import deliveryapp.app.repository.CourierRepository;
import deliveryapp.app.repository.PackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private CourierRepository courierRepository;

    // CRUD operations for Package
    @Transactional
    public PackageDTO createPackage(PackageDTO packageDTO) {
        Courier courier = packageDTO.getCourierId() != null 
            ? courierRepository.findById(packageDTO.getCourierId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found")) 
            : null;
        
        Package Package = new   Package (
            courier, 
            packageDTO.getDeliveryAddress(), 
            packageDTO.isPayOnDelivery(), 
            PackageStatus.valueOf(packageDTO.getStatus())
        );
        
        Package  savedPackage = packageRepository.save(  Package );
        return mapPackageToDTO(savedPackage);
    }

    public PackageDTO getPackageById(Long id) {
    	  Package  foodPackage = packageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Package not found"));
        return mapPackageToDTO(foodPackage);
    }

    public List<PackageDTO> getAllPackages() {
        return packageRepository.findAll().stream()
            .map(this::mapPackageToDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public PackageDTO updatePackage(Long id, PackageDTO packageDTO) {
    	  Package  existingPackage = packageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Package not found"));
        
        // Update delivery address
        existingPackage.setDeliveryAddress(packageDTO.getDeliveryAddress());
        
        // Update courier if provided
        if (packageDTO.getCourierId() != null) {
            Courier courier = courierRepository.findById(packageDTO.getCourierId())
                .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
            existingPackage.setCourier(courier);
        }
        
        // Update pay on delivery
        existingPackage.setPayOnDelivery(packageDTO.isPayOnDelivery());
        
        // Update status
        existingPackage.setStatus(PackageStatus.valueOf(packageDTO.getStatus()));
        
        return mapPackageToDTO(packageRepository.save(existingPackage));
    }

    @Transactional
    public void deletePackage(Long id) {
    	  Package  foodPackage = packageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Package not found"));
        packageRepository.delete(foodPackage);
    }

    // Get packages for a specific courier
    public List<PackageDTO> getPackagesForCourier(Long courierId) {
        Courier courier = courierRepository.findById(courierId)
            .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        
        return packageRepository.findByCourier(courier).stream()
            .map(this::mapPackageToDTO)
            .collect(Collectors.toList());
    }

    // Get unassigned packages (with NEW status)
    public List<PackageDTO> getUnassignedPackages() {
        return packageRepository.findByStatus(PackageStatus.NEW).stream()
            .map(this::mapPackageToDTO)
            .collect(Collectors.toList());
    }

    // Utility method to map Package to PackageDTO
    private PackageDTO mapPackageToDTO(  Package   Package ) {
        return new PackageDTO(
        		  Package .getid(),
        		  Package .getCourier() != null ?   Package .getCourier().getId() : null,
            		  Package .getCreatedOn(),
            		  Package .getDeliveryAddress(),
            		  Package .isPayOnDelivery(),
            		  Package .getStatus().name()
        );
    }
}

