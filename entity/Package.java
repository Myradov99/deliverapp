
package deliveryapp.app.entity;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import deliveryapp.app.PackageStatus.PackageStatus;

@Entity
public class Package {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @ManyToOne
    @JoinColumn(name = "courier_id",nullable=false)
    private Courier courier;
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
   
    private String deliveryAddress;
    private boolean payOnDelivery;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  PackageStatus status;
    
    public Package() {}

    public Package(Courier courier, String deliveryAddress, boolean payOnDelivery, PackageStatus status) {
        this.courier = courier;
        this.createdOn = LocalDateTime.now();
        this.deliveryAddress = deliveryAddress;
        this.payOnDelivery = payOnDelivery;
        this.status = status;
    }
    
    public Long getid() {
    return id;
    }
    public void setid(Long id) {
    	this.id=id;
    }
    
    public Courier getCourier() {
        return courier;
    }
    public void setCourier(Courier courier) {
        this.courier = courier;
    }
    
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
/*    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }*/
    
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    
    
    public boolean isPayOnDelivery() {
        return payOnDelivery;
    }
    
    public void setPayOnDelivery(boolean payOnDelivery) {
        this.payOnDelivery = payOnDelivery;
    }
    

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

}