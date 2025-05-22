package com.example.bankcards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
