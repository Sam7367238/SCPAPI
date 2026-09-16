package org.playground.scpapi.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.playground.scpapi.media.Media;

@Getter
@Setter
@Entity(name = "profiles")
public class Profile {
    @Id
    @Column(name = "uuid", nullable = false, length = 16)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}