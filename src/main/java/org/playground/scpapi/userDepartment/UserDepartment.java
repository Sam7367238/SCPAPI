package org.playground.scpapi.userDepartment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.playground.scpapi.department.Department;
import org.playground.scpapi.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "user_departments")
public class UserDepartment {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "joined")
    private LocalDateTime joined;
}