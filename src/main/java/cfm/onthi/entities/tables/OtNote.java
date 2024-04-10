/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables;


import cfm.onthi.entities.Keys;
import cfm.onthi.entities.SOnthi;
import cfm.onthi.entities.tables.records.OtNoteRecord;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
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
public class OtNote extends TableImpl<OtNoteRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s_onthi.ot_note</code>
     */
    public static final OtNote OT_NOTE = new OtNote();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OtNoteRecord> getRecordType() {
        return OtNoteRecord.class;
    }

    /**
     * The column <code>s_onthi.ot_note.ID_NOTE</code>.
     */
    public final TableField<OtNoteRecord, Long> ID_NOTE = createField(DSL.name("ID_NOTE"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>s_onthi.ot_note.ID_USER</code>.
     */
    public final TableField<OtNoteRecord, Long> ID_USER = createField(DSL.name("ID_USER"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_note.ID_LESSON</code>.
     */
    public final TableField<OtNoteRecord, Long> ID_LESSON = createField(DSL.name("ID_LESSON"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_note.CONTENT</code>.
     */
    public final TableField<OtNoteRecord, String> CONTENT = createField(DSL.name("CONTENT"), SQLDataType.VARCHAR(500).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>s_onthi.ot_note.NOTE_TIME</code>.
     */
    public final TableField<OtNoteRecord, Double> NOTE_TIME = createField(DSL.name("NOTE_TIME"), SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_note.CREATED_DATE</code>.
     */
    public final TableField<OtNoteRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>s_onthi.ot_note.LAST_MODIFIED_BY</code>.
     */
    public final TableField<OtNoteRecord, String> LAST_MODIFIED_BY = createField(DSL.name("LAST_MODIFIED_BY"), SQLDataType.VARCHAR(50).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>s_onthi.ot_note.LAST_MODIFIED_DATE</code>.
     */
    public final TableField<OtNoteRecord, LocalDateTime> LAST_MODIFIED_DATE = createField(DSL.name("LAST_MODIFIED_DATE"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    private OtNote(Name alias, Table<OtNoteRecord> aliased) {
        this(alias, aliased, null);
    }

    private OtNote(Name alias, Table<OtNoteRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s_onthi.ot_note</code> table reference
     */
    public OtNote(String alias) {
        this(DSL.name(alias), OT_NOTE);
    }

    /**
     * Create an aliased <code>s_onthi.ot_note</code> table reference
     */
    public OtNote(Name alias) {
        this(alias, OT_NOTE);
    }

    /**
     * Create a <code>s_onthi.ot_note</code> table reference
     */
    public OtNote() {
        this(DSL.name("ot_note"), null);
    }

    public <O extends Record> OtNote(Table<O> child, ForeignKey<O, OtNoteRecord> key) {
        super(child, key, OT_NOTE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SOnthi.S_ONTHI;
    }

    @Override
    public Identity<OtNoteRecord, Long> getIdentity() {
        return (Identity<OtNoteRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OtNoteRecord> getPrimaryKey() {
        return Keys.KEY_OT_NOTE_PRIMARY;
    }

    @Override
    public OtNote as(String alias) {
        return new OtNote(DSL.name(alias), this);
    }

    @Override
    public OtNote as(Name alias) {
        return new OtNote(alias, this);
    }

    @Override
    public OtNote as(Table<?> alias) {
        return new OtNote(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OtNote rename(String name) {
        return new OtNote(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtNote rename(Name name) {
        return new OtNote(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtNote rename(Table<?> name) {
        return new OtNote(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Long, Long, String, Double, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Long, ? super Long, ? super Long, ? super String, ? super Double, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Long, ? super Long, ? super Long, ? super String, ? super Double, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
