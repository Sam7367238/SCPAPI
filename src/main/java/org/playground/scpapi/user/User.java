package org.playground.scpapi.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.playground.scpapi.department.Department;
import org.playground.scpapi.userDepartment.UserDepartment;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "clearance_level")
    private Byte clearanceLevel;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Profile> profiles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserDepartment> userDepartments = new LinkedHashSet<>();
}