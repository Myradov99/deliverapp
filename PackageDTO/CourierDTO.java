package deliveryapp.app.PackageDTO;

public class CourierDTO {
    private Long id;
    private String name;
    private String email;
    private Long managerId;

    // Constructors
    public CourierDTO() {}

    public CourierDTO(Long id, String name, String email, Long managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.managerId = managerId;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getManagerId() { return managerId; }
    public void setManagerId(Long managerId) { this.managerId = managerId; }
}

