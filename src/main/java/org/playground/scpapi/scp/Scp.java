package org.playground.scpapi.scp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.playground.scpapi.media.Media;
import org.playground.scpapi.user.User;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "scps")
public class Scp {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "clearance_level")
    private Byte clearanceLevel;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created")
    private LocalDateTime created;
}