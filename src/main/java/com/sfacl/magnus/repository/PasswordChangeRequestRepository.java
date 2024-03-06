package com.sfacl.magnus.repository;

import com.sfacl.magnus.entity.RequestPasswordChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordChangeRequestRepository extends JpaRepository<RequestPasswordChange,Long> {
}
