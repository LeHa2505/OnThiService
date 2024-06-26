/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables;


import cfm.onthi.entities.Keys;
import cfm.onthi.entities.SOnthi;
import cfm.onthi.entities.tables.records.OtRoleRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
public class OtRole extends TableImpl<OtRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s_onthi.ot_role</code>
     */
    public static final OtRole OT_ROLE = new OtRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OtRoleRecord> getRecordType() {
        return OtRoleRecord.class;
    }

    /**
     * The column <code>s_onthi.ot_role.ID_ROLE</code>.
     */
    public final TableField<OtRoleRecord, Long> ID_ROLE = createField(DSL.name("ID_ROLE"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>s_onthi.ot_role.ROLE_DESCRIBE</code>.
     */
    public final TableField<OtRoleRecord, String> ROLE_DESCRIBE = createField(DSL.name("ROLE_DESCRIBE"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>s_onthi.ot_role.ROLE_KEY</code>.
     */
    public final TableField<OtRoleRecord, String> ROLE_KEY = createField(DSL.name("ROLE_KEY"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_role.ROLE_NAME</code>.
     */
    public final TableField<OtRoleRecord, String> ROLE_NAME = createField(DSL.name("ROLE_NAME"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_role.ACTIVE</code>.
     */
    public final TableField<OtRoleRecord, Boolean> ACTIVE = createField(DSL.name("ACTIVE"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_role.CREATED_DATE</code>.
     */
    public final TableField<OtRoleRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>s_onthi.ot_role.LAST_MODIFIED_BY</code>.
     */
    public final TableField<OtRoleRecord, String> LAST_MODIFIED_BY = createField(DSL.name("LAST_MODIFIED_BY"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>s_onthi.ot_role.LAST_MODIFIED_DATE</code>.
     */
    public final TableField<OtRoleRecord, LocalDateTime> LAST_MODIFIED_DATE = createField(DSL.name("LAST_MODIFIED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    private OtRole(Name alias, Table<OtRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private OtRole(Name alias, Table<OtRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s_onthi.ot_role</code> table reference
     */
    public OtRole(String alias) {
        this(DSL.name(alias), OT_ROLE);
    }

    /**
     * Create an aliased <code>s_onthi.ot_role</code> table reference
     */
    public OtRole(Name alias) {
        this(alias, OT_ROLE);
    }

    /**
     * Create a <code>s_onthi.ot_role</code> table reference
     */
    public OtRole() {
        this(DSL.name("ot_role"), null);
    }

    public <O extends Record> OtRole(Table<O> child, ForeignKey<O, OtRoleRecord> key) {
        super(child, key, OT_ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SOnthi.S_ONTHI;
    }

    @Override
    public Identity<OtRoleRecord, Long> getIdentity() {
        return (Identity<OtRoleRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OtRoleRecord> getPrimaryKey() {
        return Keys.KEY_OT_ROLE_PRIMARY;
    }

    @Override
    public List<ForeignKey<OtRoleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OT_ROLE_IBFK_1);
    }

    private transient OtUserRole _otUserRole;

    /**
     * Get the implicit join path to the <code>s_onthi.ot_user_role</code>
     * table.
     */
    public OtUserRole otUserRole() {
        if (_otUserRole == null)
            _otUserRole = new OtUserRole(this, Keys.OT_ROLE_IBFK_1);

        return _otUserRole;
    }

    @Override
    public OtRole as(String alias) {
        return new OtRole(DSL.name(alias), this);
    }

    @Override
    public OtRole as(Name alias) {
        return new OtRole(alias, this);
    }

    @Override
    public OtRole as(Table<?> alias) {
        return new OtRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRole rename(String name) {
        return new OtRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRole rename(Name name) {
        return new OtRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRole rename(Table<?> name) {
        return new OtRole(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Boolean, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Long, ? super String, ? super String, ? super String, ? super Boolean, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Long, ? super String, ? super String, ? super String, ? super Boolean, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
