package com.poly.be.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "username")
    User user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
