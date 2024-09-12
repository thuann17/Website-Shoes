package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId;
    String roleName;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    List<UserRole> userRole;
}
