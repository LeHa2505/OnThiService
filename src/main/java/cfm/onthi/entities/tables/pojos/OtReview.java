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
    name = "ot_review",
    schema = "s_onthi",
    indexes = {
        @Index(name = "ID_COURSE", columnList = "ID_COURSE ASC")
    }
)
public class OtReview implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idReview;
    private Long idCourse;
    private Long idUser;
    private String content;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public OtReview() {}

    public OtReview(OtReview value) {
        this.idReview = value.idReview;
        this.idCourse = value.idCourse;
        this.idUser = value.idUser;
        this.content = value.content;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public OtReview(
        Long idReview,
        Long idCourse,
        Long idUser,
        String content,
        LocalDateTime createdDate,
        String lastModifiedBy,
        LocalDateTime lastModifiedDate
    ) {
        this.idReview = idReview;
        this.idCourse = idCourse;
        this.idUser = idUser;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Getter for <code>s_onthi.ot_review.ID_REVIEW</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REVIEW", nullable = false)
    public Long getIdReview() {
        return this.idReview;
    }

    /**
     * Setter for <code>s_onthi.ot_review.ID_REVIEW</code>.
     */
    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    /**
     * Getter for <code>s_onthi.ot_review.ID_COURSE</code>.
     */
    @Column(name = "ID_COURSE", nullable = false)
    public Long getIdCourse() {
        return this.idCourse;
    }

    /**
     * Setter for <code>s_onthi.ot_review.ID_COURSE</code>.
     */
    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    /**
     * Getter for <code>s_onthi.ot_review.ID_USER</code>.
     */
    @Column(name = "ID_USER", nullable = false)
    public Long getIdUser() {
        return this.idUser;
    }

    /**
     * Setter for <code>s_onthi.ot_review.ID_USER</code>.
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * Getter for <code>s_onthi.ot_review.CONTENT</code>.
     */
    @Column(name = "CONTENT", nullable = false, length = 2000)
    public String getContent() {
        return this.content;
    }

    /**
     * Setter for <code>s_onthi.ot_review.CONTENT</code>.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for <code>s_onthi.ot_review.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Setter for <code>s_onthi.ot_review.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for <code>s_onthi.ot_review.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Setter for <code>s_onthi.ot_review.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Getter for <code>s_onthi.ot_review.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Setter for <code>s_onthi.ot_review.LAST_MODIFIED_DATE</code>.
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
        final OtReview other = (OtReview) obj;
        if (this.idReview == null) {
            if (other.idReview != null)
                return false;
        }
        else if (!this.idReview.equals(other.idReview))
            return false;
        if (this.idCourse == null) {
            if (other.idCourse != null)
                return false;
        }
        else if (!this.idCourse.equals(other.idCourse))
            return false;
        if (this.idUser == null) {
            if (other.idUser != null)
                return false;
        }
        else if (!this.idUser.equals(other.idUser))
            return false;
        if (this.content == null) {
            if (other.content != null)
                return false;
        }
        else if (!this.content.equals(other.content))
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
        result = prime * result + ((this.idReview == null) ? 0 : this.idReview.hashCode());
        result = prime * result + ((this.idCourse == null) ? 0 : this.idCourse.hashCode());
        result = prime * result + ((this.idUser == null) ? 0 : this.idUser.hashCode());
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        result = prime * result + ((this.createdDate == null) ? 0 : this.createdDate.hashCode());
        result = prime * result + ((this.lastModifiedBy == null) ? 0 : this.lastModifiedBy.hashCode());
        result = prime * result + ((this.lastModifiedDate == null) ? 0 : this.lastModifiedDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OtReview (");

        sb.append(idReview);
        sb.append(", ").append(idCourse);
        sb.append(", ").append(idUser);
        sb.append(", ").append(content);
        sb.append(", ").append(createdDate);
        sb.append(", ").append(lastModifiedBy);
        sb.append(", ").append(lastModifiedDate);

        sb.append(")");
        return sb.toString();
    }
}
