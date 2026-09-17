package org.playground.scpapi.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.playground.scpapi.media.Media;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "profiles")
public class Profile {
    @Id
    @Column(name = "uuid", nullable = false, length = 16)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media media;

    @OneToOne(mappedBy = "profile")
    private User user;
}