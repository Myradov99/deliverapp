package deliveryapp.app.PackageDTO;

public class ManagerDeliveredPackagesDTO {
    private String managerName;
    private Long deliveredPackages;

    // Constructors
    public ManagerDeliveredPackagesDTO() {}

    public ManagerDeliveredPackagesDTO(String managerName, Long deliveredPackages) {
        this.managerName = managerName;
        this.deliveredPackages = deliveredPackages;
    }

    // Getters and setters
    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public Long getDeliveredPackages() { return deliveredPackages; }
    public void setDeliveredPackages(Long deliveredPackages) { this.deliveredPackages = deliveredPackages; }
}
