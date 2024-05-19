/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables;


import cfm.onthi.entities.Indexes;
import cfm.onthi.entities.Keys;
import cfm.onthi.entities.SOnthi;
import cfm.onthi.entities.tables.records.OtQuizRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
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
public class OtQuiz extends TableImpl<OtQuizRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>s_onthi.ot_quiz</code>
     */
    public static final OtQuiz OT_QUIZ = new OtQuiz();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OtQuizRecord> getRecordType() {
        return OtQuizRecord.class;
    }

    /**
     * The column <code>s_onthi.ot_quiz.ID_QUIZ</code>.
     */
    public final TableField<OtQuizRecord, Long> ID_QUIZ = createField(DSL.name("ID_QUIZ"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>s_onthi.ot_quiz.ID_LESSON</code>.
     */
    public final TableField<OtQuizRecord, Long> ID_LESSON = createField(DSL.name("ID_LESSON"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_quiz.CONTENT_QUIZ</code>.
     */
    public final TableField<OtQuizRecord, String> CONTENT_QUIZ = createField(DSL.name("CONTENT_QUIZ"), SQLDataType.VARCHAR(2000).nullable(false), this, "");

    /**
     * The column <code>s_onthi.ot_quiz.CREATED_DATE</code>.
     */
    public final TableField<OtQuizRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("CREATED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>s_onthi.ot_quiz.LAST_MODIFIED_BY</code>.
     */
    public final TableField<OtQuizRecord, String> LAST_MODIFIED_BY = createField(DSL.name("LAST_MODIFIED_BY"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>s_onthi.ot_quiz.LAST_MODIFIED_DATE</code>.
     */
    public final TableField<OtQuizRecord, LocalDateTime> LAST_MODIFIED_DATE = createField(DSL.name("LAST_MODIFIED_DATE"), SQLDataType.LOCALDATETIME(6), this, "");

    private OtQuiz(Name alias, Table<OtQuizRecord> aliased) {
        this(alias, aliased, null);
    }

    private OtQuiz(Name alias, Table<OtQuizRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>s_onthi.ot_quiz</code> table reference
     */
    public OtQuiz(String alias) {
        this(DSL.name(alias), OT_QUIZ);
    }

    /**
     * Create an aliased <code>s_onthi.ot_quiz</code> table reference
     */
    public OtQuiz(Name alias) {
        this(alias, OT_QUIZ);
    }

    /**
     * Create a <code>s_onthi.ot_quiz</code> table reference
     */
    public OtQuiz() {
        this(DSL.name("ot_quiz"), null);
    }

    public <O extends Record> OtQuiz(Table<O> child, ForeignKey<O, OtQuizRecord> key) {
        super(child, key, OT_QUIZ);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SOnthi.S_ONTHI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.OT_QUIZ_ID_LESSON);
    }

    @Override
    public Identity<OtQuizRecord, Long> getIdentity() {
        return (Identity<OtQuizRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OtQuizRecord> getPrimaryKey() {
        return Keys.KEY_OT_QUIZ_PRIMARY;
    }

    @Override
    public List<ForeignKey<OtQuizRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OT_QUIZ_IBFK_1);
    }

    private transient OtLesson _otLesson;

    /**
     * Get the implicit join path to the <code>s_onthi.ot_lesson</code> table.
     */
    public OtLesson otLesson() {
        if (_otLesson == null)
            _otLesson = new OtLesson(this, Keys.OT_QUIZ_IBFK_1);

        return _otLesson;
    }

    @Override
    public OtQuiz as(String alias) {
        return new OtQuiz(DSL.name(alias), this);
    }

    @Override
    public OtQuiz as(Name alias) {
        return new OtQuiz(alias, this);
    }

    @Override
    public OtQuiz as(Table<?> alias) {
        return new OtQuiz(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OtQuiz rename(String name) {
        return new OtQuiz(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtQuiz rename(Name name) {
        return new OtQuiz(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OtQuiz rename(Table<?> name) {
        return new OtQuiz(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
