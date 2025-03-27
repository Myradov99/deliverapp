package deliveryapp.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
   
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Courier manager;
    @Column(name = "email")
    private String email;
    public Courier() {}

    public Courier(String name, String email, Courier manager) {
        this.name = name;
        this.email = email;
        this.manager = manager;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
    
    public String getemail() {
    return email;
    }
    public void setemail(String email) {
    this.email=email;
    }

    public Courier getManager() {
        return manager;
    }
    public void setManager(Courier manager) {
        this.manager = manager;
    }
    

}