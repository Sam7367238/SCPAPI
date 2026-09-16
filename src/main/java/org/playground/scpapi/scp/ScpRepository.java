package org.playground.scpapi.scp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScpRepository extends JpaRepository<Scp, UUID> {
}