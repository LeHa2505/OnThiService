/*
 * This file is generated by jOOQ.
 */
package cfm.onthi.entities.tables.records;


import cfm.onthi.entities.tables.OtMenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ot_menu",
    schema = "s_onthi"
)
public class OtMenuRecord extends UpdatableRecordImpl<OtMenuRecord> implements Record15<Long, String, String, String, String, Long, Long, String, String, String, String, Boolean, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>s_onthi.ot_menu.ID_MENU</code>.
     */
    public void setIdMenu(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.ID_MENU</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENU", nullable = false)
    public Long getIdMenu() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_BADGE</code>.
     */
    public void setMenuBadge(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_BADGE</code>.
     */
    @Column(name = "MENU_BADGE", length = 255)
    public String getMenuBadge() {
        return (String) get(1);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_BADGE_CLASS</code>.
     */
    public void setMenuBadgeClass(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_BADGE_CLASS</code>.
     */
    @Column(name = "MENU_BADGE_CLASS", length = 255)
    public String getMenuBadgeClass() {
        return (String) get(2);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_ICON</code>.
     */
    public void setMenuIcon(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_ICON</code>.
     */
    @Column(name = "MENU_ICON", length = 255)
    public String getMenuIcon() {
        return (String) get(3);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_NAME</code>.
     */
    public void setMenuName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_NAME</code>.
     */
    @Column(name = "MENU_NAME", nullable = false, length = 255)
    public String getMenuName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_ORDER</code>.
     */
    public void setMenuOrder(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_ORDER</code>.
     */
    @Column(name = "MENU_ORDER", nullable = false)
    public Long getMenuOrder() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_PARENT</code>.
     */
    public void setMenuParent(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_PARENT</code>.
     */
    @Column(name = "MENU_PARENT")
    public Long getMenuParent() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_PRJ_NAME</code>.
     */
    public void setMenuPrjName(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_PRJ_NAME</code>.
     */
    @Column(name = "MENU_PRJ_NAME", length = 255)
    public String getMenuPrjName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_ROUTER_LINK</code>.
     */
    public void setMenuRouterLink(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_ROUTER_LINK</code>.
     */
    @Column(name = "MENU_ROUTER_LINK", nullable = false, length = 255)
    public String getMenuRouterLink() {
        return (String) get(8);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_TARGET</code>.
     */
    public void setMenuTarget(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_TARGET</code>.
     */
    @Column(name = "MENU_TARGET", length = 255)
    public String getMenuTarget() {
        return (String) get(9);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.MENU_URL</code>.
     */
    public void setMenuUrl(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.MENU_URL</code>.
     */
    @Column(name = "MENU_URL", length = 255)
    public String getMenuUrl() {
        return (String) get(10);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.ACTIVE</code>.
     */
    public void setActive(Boolean value) {
        set(11, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.ACTIVE</code>.
     */
    @Column(name = "ACTIVE", nullable = false)
    public Boolean getActive() {
        return (Boolean) get(11);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.CREATED_DATE</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.CREATED_DATE</code>.
     */
    @Column(name = "CREATED_DATE", precision = 6)
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.LAST_MODIFIED_BY</code>.
     */
    public void setLastModifiedBy(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.LAST_MODIFIED_BY</code>.
     */
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    public String getLastModifiedBy() {
        return (String) get(13);
    }

    /**
     * Setter for <code>s_onthi.ot_menu.LAST_MODIFIED_DATE</code>.
     */
    public void setLastModifiedDate(LocalDateTime value) {
        set(14, value);
    }

    /**
     * Getter for <code>s_onthi.ot_menu.LAST_MODIFIED_DATE</code>.
     */
    @Column(name = "LAST_MODIFIED_DATE", precision = 6)
    public LocalDateTime getLastModifiedDate() {
        return (LocalDateTime) get(14);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row15<Long, String, String, String, String, Long, Long, String, String, String, String, Boolean, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    @Override
    public Row15<Long, String, String, String, String, Long, Long, String, String, String, String, Boolean, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row15) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return OtMenu.OT_MENU.ID_MENU;
    }

    @Override
    public Field<String> field2() {
        return OtMenu.OT_MENU.MENU_BADGE;
    }

    @Override
    public Field<String> field3() {
        return OtMenu.OT_MENU.MENU_BADGE_CLASS;
    }

    @Override
    public Field<String> field4() {
        return OtMenu.OT_MENU.MENU_ICON;
    }

    @Override
    public Field<String> field5() {
        return OtMenu.OT_MENU.MENU_NAME;
    }

    @Override
    public Field<Long> field6() {
        return OtMenu.OT_MENU.MENU_ORDER;
    }

    @Override
    public Field<Long> field7() {
        return OtMenu.OT_MENU.MENU_PARENT;
    }

    @Override
    public Field<String> field8() {
        return OtMenu.OT_MENU.MENU_PRJ_NAME;
    }

    @Override
    public Field<String> field9() {
        return OtMenu.OT_MENU.MENU_ROUTER_LINK;
    }

    @Override
    public Field<String> field10() {
        return OtMenu.OT_MENU.MENU_TARGET;
    }

    @Override
    public Field<String> field11() {
        return OtMenu.OT_MENU.MENU_URL;
    }

    @Override
    public Field<Boolean> field12() {
        return OtMenu.OT_MENU.ACTIVE;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return OtMenu.OT_MENU.CREATED_DATE;
    }

    @Override
    public Field<String> field14() {
        return OtMenu.OT_MENU.LAST_MODIFIED_BY;
    }

    @Override
    public Field<LocalDateTime> field15() {
        return OtMenu.OT_MENU.LAST_MODIFIED_DATE;
    }

    @Override
    public Long component1() {
        return getIdMenu();
    }

    @Override
    public String component2() {
        return getMenuBadge();
    }

    @Override
    public String component3() {
        return getMenuBadgeClass();
    }

    @Override
    public String component4() {
        return getMenuIcon();
    }

    @Override
    public String component5() {
        return getMenuName();
    }

    @Override
    public Long component6() {
        return getMenuOrder();
    }

    @Override
    public Long component7() {
        return getMenuParent();
    }

    @Override
    public String component8() {
        return getMenuPrjName();
    }

    @Override
    public String component9() {
        return getMenuRouterLink();
    }

    @Override
    public String component10() {
        return getMenuTarget();
    }

    @Override
    public String component11() {
        return getMenuUrl();
    }

    @Override
    public Boolean component12() {
        return getActive();
    }

    @Override
    public LocalDateTime component13() {
        return getCreatedDate();
    }

    @Override
    public String component14() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime component15() {
        return getLastModifiedDate();
    }

    @Override
    public Long value1() {
        return getIdMenu();
    }

    @Override
    public String value2() {
        return getMenuBadge();
    }

    @Override
    public String value3() {
        return getMenuBadgeClass();
    }

    @Override
    public String value4() {
        return getMenuIcon();
    }

    @Override
    public String value5() {
        return getMenuName();
    }

    @Override
    public Long value6() {
        return getMenuOrder();
    }

    @Override
    public Long value7() {
        return getMenuParent();
    }

    @Override
    public String value8() {
        return getMenuPrjName();
    }

    @Override
    public String value9() {
        return getMenuRouterLink();
    }

    @Override
    public String value10() {
        return getMenuTarget();
    }

    @Override
    public String value11() {
        return getMenuUrl();
    }

    @Override
    public Boolean value12() {
        return getActive();
    }

    @Override
    public LocalDateTime value13() {
        return getCreatedDate();
    }

    @Override
    public String value14() {
        return getLastModifiedBy();
    }

    @Override
    public LocalDateTime value15() {
        return getLastModifiedDate();
    }

    @Override
    public OtMenuRecord value1(Long value) {
        setIdMenu(value);
        return this;
    }

    @Override
    public OtMenuRecord value2(String value) {
        setMenuBadge(value);
        return this;
    }

    @Override
    public OtMenuRecord value3(String value) {
        setMenuBadgeClass(value);
        return this;
    }

    @Override
    public OtMenuRecord value4(String value) {
        setMenuIcon(value);
        return this;
    }

    @Override
    public OtMenuRecord value5(String value) {
        setMenuName(value);
        return this;
    }

    @Override
    public OtMenuRecord value6(Long value) {
        setMenuOrder(value);
        return this;
    }

    @Override
    public OtMenuRecord value7(Long value) {
        setMenuParent(value);
        return this;
    }

    @Override
    public OtMenuRecord value8(String value) {
        setMenuPrjName(value);
        return this;
    }

    @Override
    public OtMenuRecord value9(String value) {
        setMenuRouterLink(value);
        return this;
    }

    @Override
    public OtMenuRecord value10(String value) {
        setMenuTarget(value);
        return this;
    }

    @Override
    public OtMenuRecord value11(String value) {
        setMenuUrl(value);
        return this;
    }

    @Override
    public OtMenuRecord value12(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public OtMenuRecord value13(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public OtMenuRecord value14(String value) {
        setLastModifiedBy(value);
        return this;
    }

    @Override
    public OtMenuRecord value15(LocalDateTime value) {
        setLastModifiedDate(value);
        return this;
    }

    @Override
    public OtMenuRecord values(Long value1, String value2, String value3, String value4, String value5, Long value6, Long value7, String value8, String value9, String value10, String value11, Boolean value12, LocalDateTime value13, String value14, LocalDateTime value15) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtMenuRecord
     */
    public OtMenuRecord() {
        super(OtMenu.OT_MENU);
    }

    /**
     * Create a detached, initialised OtMenuRecord
     */
    public OtMenuRecord(Long idMenu, String menuBadge, String menuBadgeClass, String menuIcon, String menuName, Long menuOrder, Long menuParent, String menuPrjName, String menuRouterLink, String menuTarget, String menuUrl, Boolean active, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(OtMenu.OT_MENU);

        setIdMenu(idMenu);
        setMenuBadge(menuBadge);
        setMenuBadgeClass(menuBadgeClass);
        setMenuIcon(menuIcon);
        setMenuName(menuName);
        setMenuOrder(menuOrder);
        setMenuParent(menuParent);
        setMenuPrjName(menuPrjName);
        setMenuRouterLink(menuRouterLink);
        setMenuTarget(menuTarget);
        setMenuUrl(menuUrl);
        setActive(active);
        setCreatedDate(createdDate);
        setLastModifiedBy(lastModifiedBy);
        setLastModifiedDate(lastModifiedDate);
    }

    /**
     * Create a detached, initialised OtMenuRecord
     */
    public OtMenuRecord(cfm.onthi.entities.tables.pojos.OtMenu value) {
        super(OtMenu.OT_MENU);

        if (value != null) {
            setIdMenu(value.getIdMenu());
            setMenuBadge(value.getMenuBadge());
            setMenuBadgeClass(value.getMenuBadgeClass());
            setMenuIcon(value.getMenuIcon());
            setMenuName(value.getMenuName());
            setMenuOrder(value.getMenuOrder());
            setMenuParent(value.getMenuParent());
            setMenuPrjName(value.getMenuPrjName());
            setMenuRouterLink(value.getMenuRouterLink());
            setMenuTarget(value.getMenuTarget());
            setMenuUrl(value.getMenuUrl());
            setActive(value.getActive());
            setCreatedDate(value.getCreatedDate());
            setLastModifiedBy(value.getLastModifiedBy());
            setLastModifiedDate(value.getLastModifiedDate());
        }
    }
}
