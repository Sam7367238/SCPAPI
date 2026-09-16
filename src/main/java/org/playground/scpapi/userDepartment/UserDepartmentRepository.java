package org.playground.scpapi.userDepartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDepartmentRepository extends JpaRepository<UserDepartment, UUID> {
}