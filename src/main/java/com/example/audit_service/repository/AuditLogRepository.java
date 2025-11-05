package com.example.audit_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.audit_service.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityIdOrderByCreatedAtDesc(String entityId);
}
