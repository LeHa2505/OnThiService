/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables;


import cfm.onthi.entities.Keys;
import cfm.onthi.entities.SOnthi;
import cfm.onthi.entities.tables.records.OtReviewRecord;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OtReview extends TableImpl<OtReviewRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s_onthi.ot_review</code>
     */
    public static final OtReview OT_REVIEW = new OtReview();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OtReviewRecord> getRecordType() {
        return OtReviewRecord.class;
    }

    /**
     * The column <code>s_onthi.ot_review.ID_REVIEW</code>.
     */
    public final TableField<OtReviewRecord, Long> ID_REVIEW = createField(DSL.name("ID_REVIEW"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>s_onthi.ot_review.ID_COURSE</code>.
     */
    public final TableField<OtReviewRecord, Long> ID_COURSE = createField(DSL.name("ID_COURSE"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_review.ID_USER</code>.
     */
    public final TableField<OtReviewRecord, Long> ID_USER = createField(DSL.name("ID_USER"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_review.CONTENT</code>.
     */
    public final TableField<OtReviewRecord, String> CONTENT = createField(DSL.name("CONTENT"), SQLDataType.VARCHAR(2000).nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_review.CREATED_DATE</code>.
     */
    public final TableField<OtReviewRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>s_onthi.ot_review.LAST_MODIFIED_BY</code>.
     */
    public final TableField<OtReviewRecord, String> LAST_MODIFIED_BY = createField(DSL.name("LAST_MODIFIED_BY"), SQLDataType.VARCHAR(50).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>s_onthi.ot_review.LAST_MODIFIED_DATE</code>.
     */
    public final TableField<OtReviewRecord, LocalDateTime> LAST_MODIFIED_DATE = createField(DSL.name("LAST_MODIFIED_DATE"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    private OtReview(Name alias, Table<OtReviewRecord> aliased) {
        this(alias, aliased, null);
    }

    private OtReview(Name alias, Table<OtReviewRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s_onthi.ot_review</code> table reference
     */
    public OtReview(String alias) {
        this(DSL.name(alias), OT_REVIEW);
    }

    /**
     * Create an aliased <code>s_onthi.ot_review</code> table reference
     */
    public OtReview(Name alias) {
        this(alias, OT_REVIEW);
    }

    /**
     * Create a <code>s_onthi.ot_review</code> table reference
     */
    public OtReview() {
        this(DSL.name("ot_review"), null);
    }

    public <O extends Record> OtReview(Table<O> child, ForeignKey<O, OtReviewRecord> key) {
        super(child, key, OT_REVIEW);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SOnthi.S_ONTHI;
    }

    @Override
    public Identity<OtReviewRecord, Long> getIdentity() {
        return (Identity<OtReviewRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OtReviewRecord> getPrimaryKey() {
        return Keys.KEY_OT_REVIEW_PRIMARY;
    }

    @Override
    public OtReview as(String alias) {
        return new OtReview(DSL.name(alias), this);
    }

    @Override
    public OtReview as(Name alias) {
        return new OtReview(alias, this);
    }

    @Override
    public OtReview as(Table<?> alias) {
        return new OtReview(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OtReview rename(String name) {
        return new OtReview(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtReview rename(Name name) {
        return new OtReview(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtReview rename(Table<?> name) {
        return new OtReview(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Long, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}