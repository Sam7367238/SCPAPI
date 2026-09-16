package org.playground.scpapi.department;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.playground.scpapi.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "user_departments")
public class UserDepartment {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "joined")
    private LocalDateTime joined;
}