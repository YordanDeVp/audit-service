package com.example.audit_service.service;

import com.example.audit_service.entity.AuditLog;
import com.example.audit_service.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLog save(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    public List<AuditLog> getByEntity(String entityId) {
        return repository.findByEntityIdOrderByCreatedAtDesc(entityId);
    }

    public List<AuditLog> getRecent(int limit) {
        return repository.findAll().stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(limit)
                .toList();
    }
}
