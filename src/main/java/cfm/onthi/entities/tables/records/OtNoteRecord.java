/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.records;


import cfm.onthi.entities.tables.OtNote;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ot_note",
    schema = "s_onthi"
)
public class OtNoteRecord extends UpdatableRecordImpl<OtNoteRecord> implements Record8<Long, Long, Long, String, Double, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s_onthi.ot_note.ID_NOTE</code>.
     */
    public void setIdNote(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.ID_NOTE</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTE", nullable = false)
    public Long getIdNote() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>s_onthi.ot_note.ID_USER</code>.
     */
    public void setIdUser(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.ID_USER</code>.
     */
    @Column(name = "ID_USER", nullable = false)
    public Long getIdUser() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>s_onthi.ot_note.ID_LESSON</code>.
     */
    public void setIdLesson(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.ID_LESSON</code>.
     */
    @Column(name = "ID_LESSON", nullable = false)
    public Long getIdLesson() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>s_onthi.ot_note.CONTENT</code>.
     */
    public void setContent(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.CONTENT</code>.
     */
    @Column(name = "CONTENT", length = 500)
    public String getContent() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s_onthi.ot_note.NOTE_TIME</code>.
     */
    public void setNoteTime(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.NOTE_TIME</code>.
     */
    @Column(name = "NOTE_TIME", nullable = false)
    public Double getNoteTime() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>s_onthi.ot_note.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>s_onthi.ot_note.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return (String) get(6);
    }

    /**
     * Setter for <code>s_onthi.ot_note.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>s_onthi.ot_note.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Long, Long, String, Double, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, Long, Long, String, Double, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return OtNote.OT_NOTE.ID_NOTE;
    }

    @Override
    public Field<Long> field2() {
        return OtNote.OT_NOTE.ID_USER;
    }

    @Override
    public Field<Long> field3() {
        return OtNote.OT_NOTE.ID_LESSON;
    }

    @Override
    public Field<String> field4() {
        return OtNote.OT_NOTE.CONTENT;
    }

    @Override
    public Field<Double> field5() {
        return OtNote.OT_NOTE.NOTE_TIME;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return OtNote.OT_NOTE.CREATED_DATE;
    }

    @Override
    public Field<String> field7() {
        return OtNote.OT_NOTE.LAST_MODIFIED_BY;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return OtNote.OT_NOTE.LAST_MODIFIED_DATE;
    }

    @Override
    public Long component1() {
        return getIdNote();
    }

    @Override
    public Long component2() {
        return getIdUser();
    }

    @Override
    public Long component3() {
        return getIdLesson();
    }

    @Override
    public String component4() {
        return getContent();
    }

    @Override
    public Double component5() {
        return getNoteTime();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedDate();
    }

    @Override
    public String component7() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime component8() {
        return getLastModifiedDate();
    }

    @Override
    public Long value1() {
        return getIdNote();
    }

    @Override
    public Long value2() {
        return getIdUser();
    }

    @Override
    public Long value3() {
        return getIdLesson();
    }

    @Override
    public String value4() {
        return getContent();
    }

    @Override
    public Double value5() {
        return getNoteTime();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedDate();
    }

    @Override
    public String value7() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime value8() {
        return getLastModifiedDate();
    }

    @Override
    public OtNoteRecord value1(Long value) {
        setIdNote(value);
        return this;
    }

    @Override
    public OtNoteRecord value2(Long value) {
        setIdUser(value);
        return this;
    }

    @Override
    public OtNoteRecord value3(Long value) {
        setIdLesson(value);
        return this;
    }

    @Override
    public OtNoteRecord value4(String value) {
        setContent(value);
        return this;
    }

    @Override
    public OtNoteRecord value5(Double value) {
        setNoteTime(value);
        return this;
    }

    @Override
    public OtNoteRecord value6(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public OtNoteRecord value7(String value) {
        setLastModifiedBy(value);
        return this;
    }

    @Override
    public OtNoteRecord value8(LocalDateTime value) {
        setLastModifiedDate(value);
        return this;
    }

    @Override
    public OtNoteRecord values(Long value1, Long value2, Long value3, String value4, Double value5, LocalDateTime value6, String value7, LocalDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtNoteRecord
     */
    public OtNoteRecord() {
        super(OtNote.OT_NOTE);
    }

    /**
     * Create a detached, initialised OtNoteRecord
     */
    public OtNoteRecord(Long idNote, Long idUser, Long idLesson, String content, Double noteTime, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(OtNote.OT_NOTE);

        setIdNote(idNote);
        setIdUser(idUser);
        setIdLesson(idLesson);
        setContent(content);
        setNoteTime(noteTime);
        setCreatedDate(createdDate);
        setLastModifiedBy(lastModifiedBy);
        setLastModifiedDate(lastModifiedDate);
    }

    /**
     * Create a detached, initialised OtNoteRecord
     */
    public OtNoteRecord(cfm.onthi.entities.tables.pojos.OtNote value) {
        super(OtNote.OT_NOTE);

        if (value != null) {
            setIdNote(value.getIdNote());
            setIdUser(value.getIdUser());
            setIdLesson(value.getIdLesson());
            setContent(value.getContent());
            setNoteTime(value.getNoteTime());
            setCreatedDate(value.getCreatedDate());
            setLastModifiedBy(value.getLastModifiedBy());
            setLastModifiedDate(value.getLastModifiedDate());
        }
    }
}
