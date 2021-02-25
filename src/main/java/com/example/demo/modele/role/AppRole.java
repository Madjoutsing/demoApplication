package com.example.demo.modele.role;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "App_Role",
        uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_name")})
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name = "Role_id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
}
