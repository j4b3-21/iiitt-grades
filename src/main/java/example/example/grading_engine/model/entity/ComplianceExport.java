package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.complianceexport.ExportScope;
import example.example.grading_engine.enums.complianceexport.ExportType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public ComplianceExport() {
    }

    public ComplianceExport(
            UUID id,
            User requestedBy,
            ExportType exportType,
            ExportScope scopeIdentifier,
            LocalDateTime generatedAt,
            String dataHash
    ) {
        this.id = id;
        this.requestedBy = requestedBy;
        this.exportType = exportType;
        this.scopeIdentifier = scopeIdentifier;
        this.generatedAt = generatedAt;
        this.dataHash = dataHash;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    public ExportType getExportType() {
        return exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    public ExportScope getScopeIdentifier() {
        return scopeIdentifier;
    }

    public void setScopeIdentifier(ExportScope scopeIdentifier) {
        this.scopeIdentifier = scopeIdentifier;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplianceExport that = (ComplianceExport) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // toString
    @Override
    public String toString() {
        return "ComplianceExport{" +
                "id=" + id +
                ", exportType=" + exportType +
                ", scopeIdentifier=" + scopeIdentifier +
                ", generatedAt=" + generatedAt +
                ", dataHash='" + dataHash + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private User requestedBy;
        private ExportType exportType;
        private ExportScope scopeIdentifier;
        private LocalDateTime generatedAt;
        private String dataHash;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder requestedBy(User requestedBy) {
            this.requestedBy = requestedBy;
            return this;
        }

        public Builder exportType(ExportType exportType) {
            this.exportType = exportType;
            return this;
        }

        public Builder scopeIdentifier(ExportScope scopeIdentifier) {
            this.scopeIdentifier = scopeIdentifier;
            return this;
        }

        public Builder generatedAt(LocalDateTime generatedAt) {
            this.generatedAt = generatedAt;
            return this;
        }

        public Builder dataHash(String dataHash) {
            this.dataHash = dataHash;
            return this;
        }

        public ComplianceExport build() {
            return new ComplianceExport(
                    id,
                    requestedBy,
                    exportType,
                    scopeIdentifier,
                    generatedAt,
                    dataHash
            );
        }
    }
}
