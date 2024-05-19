/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.records;


import cfm.onthi.entities.tables.OtLesson;

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
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ot_lesson",
    schema = "s_onthi",
    indexes = {
        @Index(name = "ID_COURSE", columnList = "ID_COURSE ASC")
    }
)
public class OtLessonRecord extends UpdatableRecordImpl<OtLessonRecord> implements Record13<Long, Long, Long, String, Double, String, Long, Double, Long, String, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s_onthi.ot_lesson.ID_LESSON</code>.
     */
    public void setIdLesson(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.ID_LESSON</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LESSON", nullable = false)
    public Long getIdLesson() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.ID_COURSE</code>.
     */
    public void setIdCourse(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.ID_COURSE</code>.
     */
    @Column(name = "ID_COURSE", nullable = false)
    public Long getIdCourse() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.LESSON_PARENT</code>.
     */
    public void setLessonParent(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.LESSON_PARENT</code>.
     */
    @Column(name = "LESSON_PARENT")
    public Long getLessonParent() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.LESSON_NAME</code>.
     */
    public void setLessonName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.LESSON_NAME</code>.
     */
    @Column(name = "LESSON_NAME", nullable = false, length = 50)
    public String getLessonName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.DURATION</code>.
     */
    public void setDuration(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.DURATION</code>.
     */
    @Column(name = "DURATION")
    public Double getDuration() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.SUBJECT</code>.
     */
    public void setSubject(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.SUBJECT</code>.
     */
    @Column(name = "SUBJECT", length = 100)
    public String getSubject() {
        return (String) get(5);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.ORDER</code>.
     */
    public void setOrder(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.ORDER</code>.
     */
    @Column(name = "ORDER")
    public Long getOrder() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.CONTINUE_TIME</code>.
     */
    public void setContinueTime(Double value) {
        set(7, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.CONTINUE_TIME</code>.
     */
    @Column(name = "CONTINUE_TIME")
    public Double getContinueTime() {
        return (Double) get(7);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.VIEW</code>.
     */
    public void setView(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.VIEW</code>.
     */
    @Column(name = "VIEW", nullable = false)
    public Long getView() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.DESCRIPTION</code>.
     */
    @Column(name = "DESCRIPTION", length = 1000)
    public String getDescription() {
        return (String) get(9);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return (String) get(11);
    }

    /**
     * Setter for <code>s_onthi.ot_lesson.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>s_onthi.ot_lesson.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return (LocalDateTime) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row13<Long, Long, Long, String, Double, String, Long, Double, Long, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    public Row13<Long, Long, Long, String, Double, String, Long, Double, Long, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return OtLesson.OT_LESSON.ID_LESSON;
    }

    @Override
    public Field<Long> field2() {
        return OtLesson.OT_LESSON.ID_COURSE;
    }

    @Override
    public Field<Long> field3() {
        return OtLesson.OT_LESSON.LESSON_PARENT;
    }

    @Override
    public Field<String> field4() {
        return OtLesson.OT_LESSON.LESSON_NAME;
    }

    @Override
    public Field<Double> field5() {
        return OtLesson.OT_LESSON.DURATION;
    }

    @Override
    public Field<String> field6() {
        return OtLesson.OT_LESSON.SUBJECT;
    }

    @Override
    public Field<Long> field7() {
        return OtLesson.OT_LESSON.ORDER;
    }

    @Override
    public Field<Double> field8() {
        return OtLesson.OT_LESSON.CONTINUE_TIME;
    }

    @Override
    public Field<Long> field9() {
        return OtLesson.OT_LESSON.VIEW;
    }

    @Override
    public Field<String> field10() {
        return OtLesson.OT_LESSON.DESCRIPTION;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return OtLesson.OT_LESSON.CREATED_DATE;
    }

    @Override
    public Field<String> field12() {
        return OtLesson.OT_LESSON.LAST_MODIFIED_BY;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return OtLesson.OT_LESSON.LAST_MODIFIED_DATE;
    }

    @Override
    public Long component1() {
        return getIdLesson();
    }

    @Override
    public Long component2() {
        return getIdCourse();
    }

    @Override
    public Long component3() {
        return getLessonParent();
    }

    @Override
    public String component4() {
        return getLessonName();
    }

    @Override
    public Double component5() {
        return getDuration();
    }

    @Override
    public String component6() {
        return getSubject();
    }

    @Override
    public Long component7() {
        return getOrder();
    }

    @Override
    public Double component8() {
        return getContinueTime();
    }

    @Override
    public Long component9() {
        return getView();
    }

    @Override
    public String component10() {
        return getDescription();
    }

    @Override
    public LocalDateTime component11() {
        return getCreatedDate();
    }

    @Override
    public String component12() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime component13() {
        return getLastModifiedDate();
    }

    @Override
    public Long value1() {
        return getIdLesson();
    }

    @Override
    public Long value2() {
        return getIdCourse();
    }

    @Override
    public Long value3() {
        return getLessonParent();
    }

    @Override
    public String value4() {
        return getLessonName();
    }

    @Override
    public Double value5() {
        return getDuration();
    }

    @Override
    public String value6() {
        return getSubject();
    }

    @Override
    public Long value7() {
        return getOrder();
    }

    @Override
    public Double value8() {
        return getContinueTime();
    }

    @Override
    public Long value9() {
        return getView();
    }

    @Override
    public String value10() {
        return getDescription();
    }

    @Override
    public LocalDateTime value11() {
        return getCreatedDate();
    }

    @Override
    public String value12() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime value13() {
        return getLastModifiedDate();
    }

    @Override
    public OtLessonRecord value1(Long value) {
        setIdLesson(value);
        return this;
    }

    @Override
    public OtLessonRecord value2(Long value) {
        setIdCourse(value);
        return this;
    }

    @Override
    public OtLessonRecord value3(Long value) {
        setLessonParent(value);
        return this;
    }

    @Override
    public OtLessonRecord value4(String value) {
        setLessonName(value);
        return this;
    }

    @Override
    public OtLessonRecord value5(Double value) {
        setDuration(value);
        return this;
    }

    @Override
    public OtLessonRecord value6(String value) {
        setSubject(value);
        return this;
    }

    @Override
    public OtLessonRecord value7(Long value) {
        setOrder(value);
        return this;
    }

    @Override
    public OtLessonRecord value8(Double value) {
        setContinueTime(value);
        return this;
    }

    @Override
    public OtLessonRecord value9(Long value) {
        setView(value);
        return this;
    }

    @Override
    public OtLessonRecord value10(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public OtLessonRecord value11(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public OtLessonRecord value12(String value) {
        setLastModifiedBy(value);
        return this;
    }

    @Override
    public OtLessonRecord value13(LocalDateTime value) {
        setLastModifiedDate(value);
        return this;
    }

    @Override
    public OtLessonRecord values(Long value1, Long value2, Long value3, String value4, Double value5, String value6, Long value7, Double value8, Long value9, String value10, LocalDateTime value11, String value12, LocalDateTime value13) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtLessonRecord
     */
    public OtLessonRecord() {
        super(OtLesson.OT_LESSON);
    }

    /**
     * Create a detached, initialised OtLessonRecord
     */
    public OtLessonRecord(Long idLesson, Long idCourse, Long lessonParent, String lessonName, Double duration, String subject, Long order, Double continueTime, Long view, String description, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(OtLesson.OT_LESSON);

        setIdLesson(idLesson);
        setIdCourse(idCourse);
        setLessonParent(lessonParent);
        setLessonName(lessonName);
        setDuration(duration);
        setSubject(subject);
        setOrder(order);
        setContinueTime(continueTime);
        setView(view);
        setDescription(description);
        setCreatedDate(createdDate);
        setLastModifiedBy(lastModifiedBy);
        setLastModifiedDate(lastModifiedDate);
    }

    /**
     * Create a detached, initialised OtLessonRecord
     */
    public OtLessonRecord(cfm.onthi.entities.tables.pojos.OtLesson value) {
        super(OtLesson.OT_LESSON);

        if (value != null) {
            setIdLesson(value.getIdLesson());
            setIdCourse(value.getIdCourse());
            setLessonParent(value.getLessonParent());
            setLessonName(value.getLessonName());
            setDuration(value.getDuration());
            setSubject(value.getSubject());
            setOrder(value.getOrder());
            setContinueTime(value.getContinueTime());
            setView(value.getView());
            setDescription(value.getDescription());
            setCreatedDate(value.getCreatedDate());
            setLastModifiedBy(value.getLastModifiedBy());
            setLastModifiedDate(value.getLastModifiedDate());
        }
    }
}
