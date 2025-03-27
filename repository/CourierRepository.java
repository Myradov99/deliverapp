package deliveryapp.app.repository;

import deliveryapp.app.entity.Courier; 	
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    // Custom query to find couriers without pending packages
    @Query("SELECT DISTINCT c FROM Courier c " +
           "WHERE c NOT IN (SELECT DISTINCT p.courier FROM Package p WHERE p.status = 'PENDING')")
    List<Courier> findCouriersWithoutPendingPackages();

    // Custom query to get managers and their delivered package count
    @Query("SELECT c AS manager, COUNT(p) AS deliveredPackages " +
           "FROM Courier c LEFT JOIN Package p ON c = p.courier AND p.status = 'DELIVERED' " +
           "WHERE c IN (SELECT DISTINCT manager FROM Courier) " +
           "GROUP BY c")
    List<Object[]> getManagersAndDeliveredPackageCount();
}