package org.playground.scpapi.media;

import java.util.UUID;

public record MediaDto(
        UUID uuid,
        String storedFileName,
        long size
) {
}
