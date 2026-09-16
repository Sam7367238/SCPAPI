package org.playground.scpapi.department;

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

@Getter
@Setter
@Entity(name = "departments")
public class Department {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}