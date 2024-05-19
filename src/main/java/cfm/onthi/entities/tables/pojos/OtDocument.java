/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ot_document",
    schema = "s_onthi",
    indexes = {
        @Index(name = "ID_LESSON", columnList = "ID_LESSON ASC")
    }
)
public class OtDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDocument;
    private Long idLesson;
    private String documentLink;
    private String documentName;
    private Long typeDocument;
    private Long downloadStatus;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public OtDocument() {}

    public OtDocument(OtDocument value) {
        this.idDocument = value.idDocument;
        this.idLesson = value.idLesson;
        this.documentLink = value.documentLink;
        this.documentName = value.documentName;
        this.typeDocument = value.typeDocument;
        this.downloadStatus = value.downloadStatus;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public OtDocument(
        Long idDocument,
        Long idLesson,
        String documentLink,
        String documentName,
        Long typeDocument,
        Long downloadStatus,
        LocalDateTime createdDate,
        String lastModifiedBy,
        LocalDateTime lastModifiedDate
    ) {
        this.idDocument = idDocument;
        this.idLesson = idLesson;
        this.documentLink = documentLink;
        this.documentName = documentName;
        this.typeDocument = typeDocument;
        this.downloadStatus = downloadStatus;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Getter for <code>s_onthi.ot_document.ID_DOCUMENT</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENT", nullable = false)
    public Long getIdDocument() {
        return this.idDocument;
    }

    /**
     * Setter for <code>s_onthi.ot_document.ID_DOCUMENT</code>.
     */
    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    /**
     * Getter for <code>s_onthi.ot_document.ID_LESSON</code>.
     */
    @Column(name = "ID_LESSON", nullable = false)
    public Long getIdLesson() {
        return this.idLesson;
    }

    /**
     * Setter for <code>s_onthi.ot_document.ID_LESSON</code>.
     */
    public void setIdLesson(Long idLesson) {
        this.idLesson = idLesson;
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOCUMENT_LINK</code>.
     */
    @Column(name = "DOCUMENT_LINK", nullable = false, length = 2000)
    public String getDocumentLink() {
        return this.documentLink;
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOCUMENT_LINK</code>.
     */
    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOCUMENT_NAME</code>.
     */
    @Column(name = "DOCUMENT_NAME", nullable = false, length = 500)
    public String getDocumentName() {
        return this.documentName;
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOCUMENT_NAME</code>.
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * Getter for <code>s_onthi.ot_document.TYPE_DOCUMENT</code>.
     */
    @Column(name = "TYPE_DOCUMENT")
    public Long getTypeDocument() {
        return this.typeDocument;
    }

    /**
     * Setter for <code>s_onthi.ot_document.TYPE_DOCUMENT</code>.
     */
    public void setTypeDocument(Long typeDocument) {
        this.typeDocument = typeDocument;
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOWNLOAD_STATUS</code>.
     */
    @Column(name = "DOWNLOAD_STATUS", nullable = false)
    public Long getDownloadStatus() {
        return this.downloadStatus;
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOWNLOAD_STATUS</code>.
     */
    public void setDownloadStatus(Long downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    /**
     * Getter for <code>s_onthi.ot_document.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Setter for <code>s_onthi.ot_document.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for <code>s_onthi.ot_document.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Setter for <code>s_onthi.ot_document.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Getter for <code>s_onthi.ot_document.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Setter for <code>s_onthi.ot_document.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final OtDocument other = (OtDocument) obj;
        if (this.idDocument == null) {
            if (other.idDocument != null)
                return false;
        }
        else if (!this.idDocument.equals(other.idDocument))
            return false;
        if (this.idLesson == null) {
            if (other.idLesson != null)
                return false;
        }
        else if (!this.idLesson.equals(other.idLesson))
            return false;
        if (this.documentLink == null) {
            if (other.documentLink != null)
                return false;
        }
        else if (!this.documentLink.equals(other.documentLink))
            return false;
        if (this.documentName == null) {
            if (other.documentName != null)
                return false;
        }
        else if (!this.documentName.equals(other.documentName))
            return false;
        if (this.typeDocument == null) {
            if (other.typeDocument != null)
                return false;
        }
        else if (!this.typeDocument.equals(other.typeDocument))
            return false;
        if (this.downloadStatus == null) {
            if (other.downloadStatus != null)
                return false;
        }
        else if (!this.downloadStatus.equals(other.downloadStatus))
            return false;
        if (this.createdDate == null) {
            if (other.createdDate != null)
                return false;
        }
        else if (!this.createdDate.equals(other.createdDate))
            return false;
        if (this.lastModifiedBy == null) {
            if (other.lastModifiedBy != null)
                return false;
        }
        else if (!this.lastModifiedBy.equals(other.lastModifiedBy))
            return false;
        if (this.lastModifiedDate == null) {
            if (other.lastModifiedDate != null)
                return false;
        }
        else if (!this.lastModifiedDate.equals(other.lastModifiedDate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.idDocument == null) ? 0 : this.idDocument.hashCode());
        result = prime * result + ((this.idLesson == null) ? 0 : this.idLesson.hashCode());
        result = prime * result + ((this.documentLink == null) ? 0 : this.documentLink.hashCode());
        result = prime * result + ((this.documentName == null) ? 0 : this.documentName.hashCode());
        result = prime * result + ((this.typeDocument == null) ? 0 : this.typeDocument.hashCode());
        result = prime * result + ((this.downloadStatus == null) ? 0 : this.downloadStatus.hashCode());
        result = prime * result + ((this.createdDate == null) ? 0 : this.createdDate.hashCode());
        result = prime * result + ((this.lastModifiedBy == null) ? 0 : this.lastModifiedBy.hashCode());
        result = prime * result + ((this.lastModifiedDate == null) ? 0 : this.lastModifiedDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OtDocument (");

        sb.append(idDocument);
        sb.append(", ").append(idLesson);
        sb.append(", ").append(documentLink);
        sb.append(", ").append(documentName);
        sb.append(", ").append(typeDocument);
        sb.append(", ").append(downloadStatus);
        sb.append(", ").append(createdDate);
        sb.append(", ").append(lastModifiedBy);
        sb.append(", ").append(lastModifiedDate);

        sb.append(")");
        return sb.toString();
    }
}
