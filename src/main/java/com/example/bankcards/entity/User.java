package com.example.bankcards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public UUID id;

    @Column(name = "NAME", unique = true)
    @Size(max = 100)
    private String name;

    @Column(name = "ENCRYPTED_PASSWORD", length = 60, nullable = false)
    @Size(max = 60, min = 60)
    private String encryptedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<Role> roles;

}
