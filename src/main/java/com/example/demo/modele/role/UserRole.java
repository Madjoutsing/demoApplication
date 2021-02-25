package com.example.demo.modele.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User_Role",
        uniqueConstraints = { @UniqueConstraint(name = "USER_ROLE_UK", columnNames = {"User_id", "Role_id"})})
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_id", nullable = false)
    private AppRole appRole;

    public UserRole(AppUser user, AppRole appRole) {
        this.appUser = user;
        this.appRole = appRole;
    }
}
