/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.records;


import cfm.onthi.entities.tables.OtUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record21;
import org.jooq.Row21;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ot_user",
    schema = "s_onthi",
    indexes = {
        @Index(name = "ID_SCHOOL", columnList = "ID_SCHOOL ASC")
    }
)
public class OtUserRecord extends UpdatableRecordImpl<OtUserRecord> implements Record21<Long, Long, Long, String, String, String, String, Long, Long, LocalDate, String, String, String, String, String, Long, Double, Boolean, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s_onthi.ot_user.ID_USER</code>.
     */
    public void setIdUser(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.ID_USER</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER", nullable = false)
    public Long getIdUser() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>s_onthi.ot_user.ID_SCHOOL</code>.
     */
    public void setIdSchool(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.ID_SCHOOL</code>.
     */
    @Column(name = "ID_SCHOOL")
    public Long getIdSchool() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>s_onthi.ot_user.TYPE_USER</code>.
     */
    public void setTypeUser(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.TYPE_USER</code>.
     */
    @Column(name = "TYPE_USER")
    public Long getTypeUser() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>s_onthi.ot_user.USERNAME</code>.
     */
    public void setUsername(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.USERNAME</code>.
     */
    @Column(name = "USERNAME", nullable = false, length = 50)
    public String getUsername() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s_onthi.ot_user.PASSWORD</code>.
     */
    public void setPassword(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.PASSWORD</code>.
     */
    @Column(name = "PASSWORD", nullable = false, length = 5000)
    public String getPassword() {
        return (String) get(4);
    }

    /**
     * Setter for <code>s_onthi.ot_user.EMAIL</code>.
     */
    public void setEmail(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.EMAIL</code>.
     */
    @Column(name = "EMAIL", nullable = false, length = 50)
    public String getEmail() {
        return (String) get(5);
    }

    /**
     * Setter for <code>s_onthi.ot_user.PHONE</code>.
     */
    public void setPhone(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.PHONE</code>.
     */
    @Column(name = "PHONE", length = 15)
    public String getPhone() {
        return (String) get(6);
    }

    /**
     * Setter for <code>s_onthi.ot_user.GRADE</code>.
     */
    public void setGrade(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.GRADE</code>.
     */
    @Column(name = "GRADE")
    public Long getGrade() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>s_onthi.ot_user.GENDER</code>.
     */
    public void setGender(Long value) {
        set(8, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.GENDER</code>.
     */
    @Column(name = "GENDER")
    public Long getGender() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>s_onthi.ot_user.BOD</code>.
     */
    public void setBod(LocalDate value) {
        set(9, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.BOD</code>.
     */
    @Column(name = "BOD")
    public LocalDate getBod() {
        return (LocalDate) get(9);
    }

    /**
     * Setter for <code>s_onthi.ot_user.ADDRESS</code>.
     */
    public void setAddress(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.ADDRESS</code>.
     */
    @Column(name = "ADDRESS", length = 500)
    public String getAddress() {
        return (String) get(10);
    }

    /**
     * Setter for <code>s_onthi.ot_user.AVATAR</code>.
     */
    public void setAvatar(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.AVATAR</code>.
     */
    @Column(name = "AVATAR", length = 5000)
    public String getAvatar() {
        return (String) get(11);
    }

    /**
     * Setter for <code>s_onthi.ot_user.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.DESCRIPTION</code>.
     */
    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription() {
        return (String) get(12);
    }

    /**
     * Setter for <code>s_onthi.ot_user.FACEBOOK</code>.
     */
    public void setFacebook(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.FACEBOOK</code>.
     */
    @Column(name = "FACEBOOK", length = 1000)
    public String getFacebook() {
        return (String) get(13);
    }

    /**
     * Setter for <code>s_onthi.ot_user.INSTAGRAM</code>.
     */
    public void setInstagram(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.INSTAGRAM</code>.
     */
    @Column(name = "INSTAGRAM", length = 1000)
    public String getInstagram() {
        return (String) get(14);
    }

    /**
     * Setter for <code>s_onthi.ot_user.ID_COURSE_LEARNING</code>.
     */
    public void setIdCourseLearning(Long value) {
        set(15, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.ID_COURSE_LEARNING</code>.
     */
    @Column(name = "ID_COURSE_LEARNING")
    public Long getIdCourseLearning() {
        return (Long) get(15);
    }

    /**
     * Setter for <code>s_onthi.ot_user.PAUSE_TIME</code>.
     */
    public void setPauseTime(Double value) {
        set(16, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.PAUSE_TIME</code>.
     */
    @Column(name = "PAUSE_TIME")
    public Double getPauseTime() {
        return (Double) get(16);
    }

    /**
     * Setter for <code>s_onthi.ot_user.ACTIVE</code>.
     */
    public void setActive(Boolean value) {
        set(17, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.ACTIVE</code>.
     */
    @Column(name = "ACTIVE", nullable = false)
    public Boolean getActive() {
        return (Boolean) get(17);
    }

    /**
     * Setter for <code>s_onthi.ot_user.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(18, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(18);
    }

    /**
     * Setter for <code>s_onthi.ot_user.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return (String) get(19);
    }

    /**
     * Setter for <code>s_onthi.ot_user.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime value) {
        set(20, value);
    }

    /**
     * Getter for <code>s_onthi.ot_user.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return (LocalDateTime) get(20);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record21 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row21<Long, Long, Long, String, String, String, String, Long, Long, LocalDate, String, String, String, String, String, Long, Double, Boolean, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    @Override
    public Row21<Long, Long, Long, String, String, String, String, Long, Long, LocalDate, String, String, String, String, String, Long, Double, Boolean, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row21) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return OtUser.OT_USER.ID_USER;
    }

    @Override
    public Field<Long> field2() {
        return OtUser.OT_USER.ID_SCHOOL;
    }

    @Override
    public Field<Long> field3() {
        return OtUser.OT_USER.TYPE_USER;
    }

    @Override
    public Field<String> field4() {
        return OtUser.OT_USER.USERNAME;
    }

    @Override
    public Field<String> field5() {
        return OtUser.OT_USER.PASSWORD;
    }

    @Override
    public Field<String> field6() {
        return OtUser.OT_USER.EMAIL;
    }

    @Override
    public Field<String> field7() {
        return OtUser.OT_USER.PHONE;
    }

    @Override
    public Field<Long> field8() {
        return OtUser.OT_USER.GRADE;
    }

    @Override
    public Field<Long> field9() {
        return OtUser.OT_USER.GENDER;
    }

    @Override
    public Field<LocalDate> field10() {
        return OtUser.OT_USER.BOD;
    }

    @Override
    public Field<String> field11() {
        return OtUser.OT_USER.ADDRESS;
    }

    @Override
    public Field<String> field12() {
        return OtUser.OT_USER.AVATAR;
    }

    @Override
    public Field<String> field13() {
        return OtUser.OT_USER.DESCRIPTION;
    }

    @Override
    public Field<String> field14() {
        return OtUser.OT_USER.FACEBOOK;
    }

    @Override
    public Field<String> field15() {
        return OtUser.OT_USER.INSTAGRAM;
    }

    @Override
    public Field<Long> field16() {
        return OtUser.OT_USER.ID_COURSE_LEARNING;
    }

    @Override
    public Field<Double> field17() {
        return OtUser.OT_USER.PAUSE_TIME;
    }

    @Override
    public Field<Boolean> field18() {
        return OtUser.OT_USER.ACTIVE;
    }

    @Override
    public Field<LocalDateTime> field19() {
        return OtUser.OT_USER.CREATED_DATE;
    }

    @Override
    public Field<String> field20() {
        return OtUser.OT_USER.LAST_MODIFIED_BY;
    }

    @Override
    public Field<LocalDateTime> field21() {
        return OtUser.OT_USER.LAST_MODIFIED_DATE;
    }

    @Override
    public Long component1() {
        return getIdUser();
    }

    @Override
    public Long component2() {
        return getIdSchool();
    }

    @Override
    public Long component3() {
        return getTypeUser();
    }

    @Override
    public String component4() {
        return getUsername();
    }

    @Override
    public String component5() {
        return getPassword();
    }

    @Override
    public String component6() {
        return getEmail();
    }

    @Override
    public String component7() {
        return getPhone();
    }

    @Override
    public Long component8() {
        return getGrade();
    }

    @Override
    public Long component9() {
        return getGender();
    }

    @Override
    public LocalDate component10() {
        return getBod();
    }

    @Override
    public String component11() {
        return getAddress();
    }

    @Override
    public String component12() {
        return getAvatar();
    }

    @Override
    public String component13() {
        return getDescription();
    }

    @Override
    public String component14() {
        return getFacebook();
    }

    @Override
    public String component15() {
        return getInstagram();
    }

    @Override
    public Long component16() {
        return getIdCourseLearning();
    }

    @Override
    public Double component17() {
        return getPauseTime();
    }

    @Override
    public Boolean component18() {
        return getActive();
    }

    @Override
    public LocalDateTime component19() {
        return getCreatedDate();
    }

    @Override
    public String component20() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime component21() {
        return getLastModifiedDate();
    }

    @Override
    public Long value1() {
        return getIdUser();
    }

    @Override
    public Long value2() {
        return getIdSchool();
    }

    @Override
    public Long value3() {
        return getTypeUser();
    }

    @Override
    public String value4() {
        return getUsername();
    }

    @Override
    public String value5() {
        return getPassword();
    }

    @Override
    public String value6() {
        return getEmail();
    }

    @Override
    public String value7() {
        return getPhone();
    }

    @Override
    public Long value8() {
        return getGrade();
    }

    @Override
    public Long value9() {
        return getGender();
    }

    @Override
    public LocalDate value10() {
        return getBod();
    }

    @Override
    public String value11() {
        return getAddress();
    }

    @Override
    public String value12() {
        return getAvatar();
    }

    @Override
    public String value13() {
        return getDescription();
    }

    @Override
    public String value14() {
        return getFacebook();
    }

    @Override
    public String value15() {
        return getInstagram();
    }

    @Override
    public Long value16() {
        return getIdCourseLearning();
    }

    @Override
    public Double value17() {
        return getPauseTime();
    }

    @Override
    public Boolean value18() {
        return getActive();
    }

    @Override
    public LocalDateTime value19() {
        return getCreatedDate();
    }

    @Override
    public String value20() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime value21() {
        return getLastModifiedDate();
    }

    @Override
    public OtUserRecord value1(Long value) {
        setIdUser(value);
        return this;
    }

    @Override
    public OtUserRecord value2(Long value) {
        setIdSchool(value);
        return this;
    }

    @Override
    public OtUserRecord value3(Long value) {
        setTypeUser(value);
        return this;
    }

    @Override
    public OtUserRecord value4(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public OtUserRecord value5(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public OtUserRecord value6(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public OtUserRecord value7(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public OtUserRecord value8(Long value) {
        setGrade(value);
        return this;
    }

    @Override
    public OtUserRecord value9(Long value) {
        setGender(value);
        return this;
    }

    @Override
    public OtUserRecord value10(LocalDate value) {
        setBod(value);
        return this;
    }

    @Override
    public OtUserRecord value11(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public OtUserRecord value12(String value) {
        setAvatar(value);
        return this;
    }

    @Override
    public OtUserRecord value13(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public OtUserRecord value14(String value) {
        setFacebook(value);
        return this;
    }

    @Override
    public OtUserRecord value15(String value) {
        setInstagram(value);
        return this;
    }

    @Override
    public OtUserRecord value16(Long value) {
        setIdCourseLearning(value);
        return this;
    }

    @Override
    public OtUserRecord value17(Double value) {
        setPauseTime(value);
        return this;
    }

    @Override
    public OtUserRecord value18(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public OtUserRecord value19(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public OtUserRecord value20(String value) {
        setLastModifiedBy(value);
        return this;
    }

    @Override
    public OtUserRecord value21(LocalDateTime value) {
        setLastModifiedDate(value);
        return this;
    }

    @Override
    public OtUserRecord values(Long value1, Long value2, Long value3, String value4, String value5, String value6, String value7, Long value8, Long value9, LocalDate value10, String value11, String value12, String value13, String value14, String value15, Long value16, Double value17, Boolean value18, LocalDateTime value19, String value20, LocalDateTime value21) {
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
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtUserRecord
     */
    public OtUserRecord() {
        super(OtUser.OT_USER);
    }

    /**
     * Create a detached, initialised OtUserRecord
     */
    public OtUserRecord(Long idUser, Long idSchool, Long typeUser, String username, String password, String email, String phone, Long grade, Long gender, LocalDate bod, String address, String avatar, String description, String facebook, String instagram, Long idCourseLearning, Double pauseTime, Boolean active, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(OtUser.OT_USER);

        setIdUser(idUser);
        setIdSchool(idSchool);
        setTypeUser(typeUser);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setGrade(grade);
        setGender(gender);
        setBod(bod);
        setAddress(address);
        setAvatar(avatar);
        setDescription(description);
        setFacebook(facebook);
        setInstagram(instagram);
        setIdCourseLearning(idCourseLearning);
        setPauseTime(pauseTime);
        setActive(active);
        setCreatedDate(createdDate);
        setLastModifiedBy(lastModifiedBy);
        setLastModifiedDate(lastModifiedDate);
    }

    /**
     * Create a detached, initialised OtUserRecord
     */
    public OtUserRecord(cfm.onthi.entities.tables.pojos.OtUser value) {
        super(OtUser.OT_USER);

        if (value != null) {
            setIdUser(value.getIdUser());
            setIdSchool(value.getIdSchool());
            setTypeUser(value.getTypeUser());
            setUsername(value.getUsername());
            setPassword(value.getPassword());
            setEmail(value.getEmail());
            setPhone(value.getPhone());
            setGrade(value.getGrade());
            setGender(value.getGender());
            setBod(value.getBod());
            setAddress(value.getAddress());
            setAvatar(value.getAvatar());
            setDescription(value.getDescription());
            setFacebook(value.getFacebook());
            setInstagram(value.getInstagram());
            setIdCourseLearning(value.getIdCourseLearning());
            setPauseTime(value.getPauseTime());
            setActive(value.getActive());
            setCreatedDate(value.getCreatedDate());
            setLastModifiedBy(value.getLastModifiedBy());
            setLastModifiedDate(value.getLastModifiedDate());
        }
    }
}
