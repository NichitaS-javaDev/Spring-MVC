package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends BaseAuditEntity {
    @Column(length = 20)
    @Size(min = 3)
    @NotBlank
    private String username;
    @Column(length = 30)
    @Size(min = 7)
    @NotBlank
    private String fullName;
    @Email
    @Column(length = 20)
    @Size(min = 10)
    private String email;
    @NonNull
    private boolean isEnabled;
    @ManyToMany
    @JoinTable(
            name = "userRoles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();

}
