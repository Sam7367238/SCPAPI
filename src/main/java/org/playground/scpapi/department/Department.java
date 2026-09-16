package org.playground.scpapi.department;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.playground.scpapi.media.Media;
import org.playground.scpapi.user.User;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "departments")
public class Department {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}