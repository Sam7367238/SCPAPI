package org.playground.scpapi.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restoration_tokens")
public class RestorationToken {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose")
    private RestorationTokenType purpose;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "expiration")
    private LocalDateTime expiration;

    @Column(name = "created")
    private LocalDateTime created;

    public UUID getUserUuid() {
        return user.getUuid();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiration);
    }
}