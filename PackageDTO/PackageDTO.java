package deliveryapp.app.PackageDTO;

import java.time.LocalDateTime;

public class PackageDTO {
    private Long id;
    private Long courierId;
    private LocalDateTime createdOn;
    private String deliveryAddress;
    private boolean payOnDelivery;
    private String status;

    // Constructors
    public PackageDTO() {}

    public PackageDTO(Long id, Long courierId, LocalDateTime createdOn, 
                      String deliveryAddress, boolean payOnDelivery, String status) {
        this.id = id;
        this.courierId = courierId;
        this.createdOn = createdOn;
        this.deliveryAddress = deliveryAddress;
        this.payOnDelivery = payOnDelivery;
        this.status = status;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCourierId() { return courierId; }
    public void setCourierId(Long courierId) { this.courierId = courierId; }
    public LocalDateTime getCreatedOn() { return createdOn; }
    public void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public boolean isPayOnDelivery() { return payOnDelivery; }
    public void setPayOnDelivery(boolean payOnDelivery) { this.payOnDelivery = payOnDelivery; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
