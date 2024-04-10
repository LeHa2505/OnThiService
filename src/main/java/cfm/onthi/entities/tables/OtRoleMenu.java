/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables;


import cfm.onthi.entities.Keys;
import cfm.onthi.entities.SOnthi;
import cfm.onthi.entities.tables.records.OtRoleMenuRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class OtRoleMenu extends TableImpl<OtRoleMenuRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s_onthi.ot_role_menu</code>
     */
    public static final OtRoleMenu OT_ROLE_MENU = new OtRoleMenu();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OtRoleMenuRecord> getRecordType() {
        return OtRoleMenuRecord.class;
    }

    /**
     * The column <code>s_onthi.ot_role_menu.ID_ROLE_MENU</code>.
     */
    public final TableField<OtRoleMenuRecord, Long> ID_ROLE_MENU = createField(DSL.name("ID_ROLE_MENU"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>s_onthi.ot_role_menu.ID_ROLE</code>.
     */
    public final TableField<OtRoleMenuRecord, Long> ID_ROLE = createField(DSL.name("ID_ROLE"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_role_menu.ID_MENU</code>.
     */
    public final TableField<OtRoleMenuRecord, Long> ID_MENU = createField(DSL.name("ID_MENU"), SQLDataType.BIGINT.nullable(false), this, "");

    private OtRoleMenu(Name alias, Table<OtRoleMenuRecord> aliased) {
        this(alias, aliased, null);
    }

    private OtRoleMenu(Name alias, Table<OtRoleMenuRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s_onthi.ot_role_menu</code> table reference
     */
    public OtRoleMenu(String alias) {
        this(DSL.name(alias), OT_ROLE_MENU);
    }

    /**
     * Create an aliased <code>s_onthi.ot_role_menu</code> table reference
     */
    public OtRoleMenu(Name alias) {
        this(alias, OT_ROLE_MENU);
    }

    /**
     * Create a <code>s_onthi.ot_role_menu</code> table reference
     */
    public OtRoleMenu() {
        this(DSL.name("ot_role_menu"), null);
    }

    public <O extends Record> OtRoleMenu(Table<O> child, ForeignKey<O, OtRoleMenuRecord> key) {
        super(child, key, OT_ROLE_MENU);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SOnthi.S_ONTHI;
    }

    @Override
    public Identity<OtRoleMenuRecord, Long> getIdentity() {
        return (Identity<OtRoleMenuRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OtRoleMenuRecord> getPrimaryKey() {
        return Keys.KEY_OT_ROLE_MENU_PRIMARY;
    }

    @Override
    public List<UniqueKey<OtRoleMenuRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_OT_ROLE_MENU_ID_ROLE, Keys.KEY_OT_ROLE_MENU_ID_MENU);
    }

    @Override
    public OtRoleMenu as(String alias) {
        return new OtRoleMenu(DSL.name(alias), this);
    }

    @Override
    public OtRoleMenu as(Name alias) {
        return new OtRoleMenu(alias, this);
    }

    @Override
    public OtRoleMenu as(Table<?> alias) {
        return new OtRoleMenu(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRoleMenu rename(String name) {
        return new OtRoleMenu(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRoleMenu rename(Name name) {
        return new OtRoleMenu(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtRoleMenu rename(Table<?> name) {
        return new OtRoleMenu(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}