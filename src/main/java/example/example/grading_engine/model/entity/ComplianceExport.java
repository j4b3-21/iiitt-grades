package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.complianceexport.ExportScope;
import example.example.grading_engine.enums.complianceexport.ExportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "compliance_exports")
public class ComplianceExport {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by", nullable = false)
    private User requestedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "export_type", nullable = false)
    private ExportType exportType;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_identifier", nullable = false)
    private ExportScope scopeIdentifier;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;

    @Column(name = "data_hash", nullable = false)
    private String dataHash;
}
