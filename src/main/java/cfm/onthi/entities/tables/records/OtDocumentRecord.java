/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.records;


import cfm.onthi.entities.tables.OtDocument;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


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
public class OtDocumentRecord extends UpdatableRecordImpl<OtDocumentRecord> implements Record9<Long, Long, String, String, Long, Long, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s_onthi.ot_document.ID_DOCUMENT</code>.
     */
    public void setIdDocument(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.ID_DOCUMENT</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENT", nullable = false)
    public Long getIdDocument() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>s_onthi.ot_document.ID_LESSON</code>.
     */
    public void setIdLesson(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.ID_LESSON</code>.
     */
    @Column(name = "ID_LESSON", nullable = false)
    public Long getIdLesson() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOCUMENT_LINK</code>.
     */
    public void setDocumentLink(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOCUMENT_LINK</code>.
     */
    @Column(name = "DOCUMENT_LINK", nullable = false, length = 2000)
    public String getDocumentLink() {
        return (String) get(2);
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOCUMENT_NAME</code>.
     */
    public void setDocumentName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOCUMENT_NAME</code>.
     */
    @Column(name = "DOCUMENT_NAME", nullable = false, length = 500)
    public String getDocumentName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s_onthi.ot_document.TYPE_DOCUMENT</code>.
     */
    public void setTypeDocument(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.TYPE_DOCUMENT</code>.
     */
    @Column(name = "TYPE_DOCUMENT")
    public Long getTypeDocument() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>s_onthi.ot_document.DOWNLOAD_STATUS</code>.
     */
    public void setDownloadStatus(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.DOWNLOAD_STATUS</code>.
     */
    @Column(name = "DOWNLOAD_STATUS", nullable = false)
    public Long getDownloadStatus() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>s_onthi.ot_document.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>s_onthi.ot_document.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return (String) get(7);
    }

    /**
     * Setter for <code>s_onthi.ot_document.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>s_onthi.ot_document.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return (LocalDateTime) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, Long, String, String, Long, Long, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Long, Long, String, String, Long, Long, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return OtDocument.OT_DOCUMENT.ID_DOCUMENT;
    }

    @Override
    public Field<Long> field2() {
        return OtDocument.OT_DOCUMENT.ID_LESSON;
    }

    @Override
    public Field<String> field3() {
        return OtDocument.OT_DOCUMENT.DOCUMENT_LINK;
    }

    @Override
    public Field<String> field4() {
        return OtDocument.OT_DOCUMENT.DOCUMENT_NAME;
    }

    @Override
    public Field<Long> field5() {
        return OtDocument.OT_DOCUMENT.TYPE_DOCUMENT;
    }

    @Override
    public Field<Long> field6() {
        return OtDocument.OT_DOCUMENT.DOWNLOAD_STATUS;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return OtDocument.OT_DOCUMENT.CREATED_DATE;
    }

    @Override
    public Field<String> field8() {
        return OtDocument.OT_DOCUMENT.LAST_MODIFIED_BY;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return OtDocument.OT_DOCUMENT.LAST_MODIFIED_DATE;
    }

    @Override
    public Long component1() {
        return getIdDocument();
    }

    @Override
    public Long component2() {
        return getIdLesson();
    }

    @Override
    public String component3() {
        return getDocumentLink();
    }

    @Override
    public String component4() {
        return getDocumentName();
    }

    @Override
    public Long component5() {
        return getTypeDocument();
    }

    @Override
    public Long component6() {
        return getDownloadStatus();
    }

    @Override
    public LocalDateTime component7() {
        return getCreatedDate();
    }

    @Override
    public String component8() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime component9() {
        return getLastModifiedDate();
    }

    @Override
    public Long value1() {
        return getIdDocument();
    }

    @Override
    public Long value2() {
        return getIdLesson();
    }

    @Override
    public String value3() {
        return getDocumentLink();
    }

    @Override
    public String value4() {
        return getDocumentName();
    }

    @Override
    public Long value5() {
        return getTypeDocument();
    }

    @Override
    public Long value6() {
        return getDownloadStatus();
    }

    @Override
    public LocalDateTime value7() {
        return getCreatedDate();
    }

    @Override
    public String value8() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime value9() {
        return getLastModifiedDate();
    }

    @Override
    public OtDocumentRecord value1(Long value) {
        setIdDocument(value);
        return this;
    }

    @Override
    public OtDocumentRecord value2(Long value) {
        setIdLesson(value);
        return this;
    }

    @Override
    public OtDocumentRecord value3(String value) {
        setDocumentLink(value);
        return this;
    }

    @Override
    public OtDocumentRecord value4(String value) {
        setDocumentName(value);
        return this;
    }

    @Override
    public OtDocumentRecord value5(Long value) {
        setTypeDocument(value);
        return this;
    }

    @Override
    public OtDocumentRecord value6(Long value) {
        setDownloadStatus(value);
        return this;
    }

    @Override
    public OtDocumentRecord value7(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public OtDocumentRecord value8(String value) {
        setLastModifiedBy(value);
        return this;
    }

    @Override
    public OtDocumentRecord value9(LocalDateTime value) {
        setLastModifiedDate(value);
        return this;
    }

    @Override
    public OtDocumentRecord values(Long value1, Long value2, String value3, String value4, Long value5, Long value6, LocalDateTime value7, String value8, LocalDateTime value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtDocumentRecord
     */
    public OtDocumentRecord() {
        super(OtDocument.OT_DOCUMENT);
    }

    /**
     * Create a detached, initialised OtDocumentRecord
     */
    public OtDocumentRecord(Long idDocument, Long idLesson, String documentLink, String documentName, Long typeDocument, Long downloadStatus, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(OtDocument.OT_DOCUMENT);

        setIdDocument(idDocument);
        setIdLesson(idLesson);
        setDocumentLink(documentLink);
        setDocumentName(documentName);
        setTypeDocument(typeDocument);
        setDownloadStatus(downloadStatus);
        setCreatedDate(createdDate);
        setLastModifiedBy(lastModifiedBy);
        setLastModifiedDate(lastModifiedDate);
    }

    /**
     * Create a detached, initialised OtDocumentRecord
     */
    public OtDocumentRecord(cfm.onthi.entities.tables.pojos.OtDocument value) {
        super(OtDocument.OT_DOCUMENT);

        if (value != null) {
            setIdDocument(value.getIdDocument());
            setIdLesson(value.getIdLesson());
            setDocumentLink(value.getDocumentLink());
            setDocumentName(value.getDocumentName());
            setTypeDocument(value.getTypeDocument());
            setDownloadStatus(value.getDownloadStatus());
            setCreatedDate(value.getCreatedDate());
            setLastModifiedBy(value.getLastModifiedBy());
            setLastModifiedDate(value.getLastModifiedDate());
        }
    }
}
