package deliveryapp.app.service;
import deliveryapp.app.PackageStatus.PackageStatus;

import deliveryapp.app.PackageDTO.CourierDTO;
import deliveryapp.app.PackageDTO.ManagerDeliveredPackagesDTO;
import deliveryapp.app.PackageDTO.PackageDTO;
import deliveryapp.app.entity.Courier;
import deliveryapp.app.entity.Courier; 
import deliveryapp.app.repository.CourierRepository;
import deliveryapp.app.repository.PackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private JavaMailSender mailSender;

    // CRUD operations for Courier
    @Transactional
    public CourierDTO createCourier(CourierDTO courierDTO) {
        Courier manager = courierDTO.getManagerId() != null 
            ? courierRepository.findById(courierDTO.getManagerId())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found")) 
            : null;
        
        Courier courier = new Courier(courierDTO.getName(), courierDTO.getEmail(), manager);
        Courier savedCourier = courierRepository.save(courier);
        
        return mapCourierToDTO(savedCourier);
    }

    public CourierDTO getCourierById(Long id) {
        Courier courier = courierRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        return mapCourierToDTO(courier);
    }

    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
            .map(this::mapCourierToDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public CourierDTO updateCourier(Long id, CourierDTO courierDTO) {
        Courier existingCourier = courierRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        
        existingCourier.setname(courierDTO.getName());
        existingCourier.setemail(courierDTO.getEmail());
        
        if (courierDTO.getManagerId() != null) {
            Courier manager = courierRepository.findById(courierDTO.getManagerId())
                .orElseThrow(() -> new EntityNotFoundException("Manager not found"));
            existingCourier.setManager(manager);
        }
        
        return mapCourierToDTO(courierRepository.save(existingCourier));
    }

    @Transactional
    public void deleteCourier(Long id) {
        Courier courier = courierRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Courier not found"));
        courierRepository.delete(courier);
    }

    // Utility method to map Courier to CourierDTO
    private CourierDTO mapCourierToDTO(Courier courier) {
        return new CourierDTO(
            courier.getId(), 
            courier.getname(), 
            courier.getemail(), 
            courier.getManager() != null ? courier.getManager().getId() : null
        );
    }

    // Custom queries
    public List<CourierDTO> getCouriersWithoutPendingPackages() {
        return courierRepository.findCouriersWithoutPendingPackages().stream()
            .map(this::mapCourierToDTO)
            .collect(Collectors.toList());
    }

    public List<ManagerDeliveredPackagesDTO> getManagersAndDeliveredPackageCount() {
        List<Object[]> results = courierRepository.getManagersAndDeliveredPackageCount();
        
        return results.stream()
            .map(result -> new ManagerDeliveredPackagesDTO(
                ((Courier)result[0]).getname(), 
                (Long)result[1]
            ))
            .collect(Collectors.toList());
    }

    // Send email functionality
    public void sendEmailToCouriers(List<Long> courierIds, String subject, String body) {
        List<Courier> couriers = courierRepository.findAllById(courierIds);
        
        for (Courier courier : couriers) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(courier.getemail());
            message.setSubject(subject);
            message.setText(body);
            
            mailSender.send(message);
        }
    }
}