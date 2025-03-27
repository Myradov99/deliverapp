package deliveryapp.app.repository;
import deliveryapp.app.entity.Courier; 	
import deliveryapp.app.entity.Package; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import deliveryapp.app.entity.*;
import deliveryapp.app.PackageStatus.*;
import java.util.List;
@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    // Find packages for a specific courier
    List<Package> findByCourier(Courier courier);

    // Find unassigned (NEW) packages
    List<Package> findByStatus(PackageStatus status);
}