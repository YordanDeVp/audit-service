package com.example.audit_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.audit_service.entity.AuditLog;
import com.example.audit_service.service.AuditLogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogService service;

    @PostMapping
    public AuditLog create(@Valid @RequestBody Map<String, Object> body) {
        AuditLog log = AuditLog.builder()
                .type((String) body.get("type"))
                .entityId(String.valueOf(body.get("entityId")))
                .actor((String) body.get("actor"))
                .payload(body.get("payload").toString())
                .build();
        return service.save(log);
    }

    @GetMapping("/by-entity/{id}")
    public List<AuditLog> getByEntity(@PathVariable String id) {
        return service.getByEntity(id);
    }

    @GetMapping("/recent")
    public List<AuditLog> getRecent(@RequestParam(defaultValue = "100") int limit) {
        return service.getRecent(limit);
    }
}
