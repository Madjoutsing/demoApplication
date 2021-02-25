package com.example.demo.modele.role;

import com.example.demo.modele.Etudiant;
import com.example.demo.modele.MembrePedagogie;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "App_User",
        uniqueConstraints = @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name"))
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "User_id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encrypted_Password", length = 128, nullable = false)
    private String encryptedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

    private String role;

    @OneToOne
    private Etudiant etudiant;

    @OneToOne
    private MembrePedagogie membrePedagogie;

    public AppUser(String mail, String encryptePassword, String role, boolean b, Etudiant etudiant) {
        this.userName = mail;
        this.encryptedPassword = encryptePassword;
        this.enabled = b;
        this.role = role;
        this.etudiant = etudiant;
    }

    public AppUser(Long userId, String userName, String encryptedPassword, boolean enabled, String role, Etudiant etudiant, MembrePedagogie membrePedagogie) {
        this.userId = userId;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.enabled = enabled;
        this.role = role;
        this.etudiant = etudiant;
        this.membrePedagogie = membrePedagogie;
    }

    public AppUser(String userName, String encryptedPassword, boolean enabled, String role, MembrePedagogie membrePedagogie) {
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.enabled = enabled;
        this.role = role;
        this.membrePedagogie = membrePedagogie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
