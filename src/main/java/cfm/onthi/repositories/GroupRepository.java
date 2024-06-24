package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.GroupDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtGroup;
import cfm.onthi.entities.tables.OtUser;
import cfm.onthi.entities.tables.OtUserGroup;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface GroupRepository extends BaseRepository<GroupDTO> {
    List<GroupDTO> getGroupsByUserId(Long userId);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class GroupRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<GroupDTO>, GroupRepository {
    OtGroup group = OtGroup.OT_GROUP.as("OtGroup");
    OtUser user = OtUser.OT_USER.as("OtUser");
    OtUserGroup userGroup = OtUserGroup.OT_USER_GROUP.as("OtUserGroup");

    public GroupRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                               @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                               @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<GroupDTO> getAll() {
        return List.of();
    }

    @Override
    public List<GroupDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_GROUP != null) {
            condition = condition.and(group.ID_GROUP.eq(inputCondition.ID_GROUP));
        }

        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(group.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        List<GroupDTO> groupDTOList = dslContext.select()
                .from(group).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(group), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    GroupDTO groupDTO = new GroupDTO();

                    groupDTO.idGroup = entry.getKey().getIdGroup();
                    groupDTO.idCourse = entry.getKey().getIdCourse();
                    groupDTO.groupName = entry.getKey().getGroupName();
                    groupDTO.avatarGroup = entry.getKey().getAvatarGroup();

                    return groupDTO;
                }).collect(Collectors.toList());

        return groupDTOList;
    }

    @Override
    public List<GroupDTO> getGroupsByUserId(Long userId) {
        try {
            return dslContext.select(
                            group.ID_GROUP.as("idGroup"),
                            group.ID_COURSE.as("idCourse"),
                            group.GROUP_NAME.as("groupName"),
                            group.AVATAR_GROUP.as("avatarGroup")
                    )
                    .from(group)
                    .join(userGroup).on(group.ID_GROUP.eq(userGroup.ID_GROUP))
                    .where(userGroup.ID_USER.eq(userId))
                    .fetchInto(GroupDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO getByID(@NotNull Long id) {
        try {
            return dslContext.selectFrom(group)
                    .where(group.ID_GROUP.eq(id))
                    .fetchOneInto(GroupDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_GROUP != null) {
            condition = condition.and(group.ID_GROUP.eq(inputCondition.ID_GROUP));
        }

        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(group.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        List<GroupDTO> groupDTOList = dslContext.select()
                .from(group).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(group), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    GroupDTO groupDTO = new GroupDTO();

                    groupDTO.idGroup = entry.getKey().getIdGroup();
                    groupDTO.idCourse = entry.getKey().getIdCourse();
                    groupDTO.groupName = entry.getKey().getGroupName();
                    groupDTO.avatarGroup = entry.getKey().getAvatarGroup();

                    return groupDTO;
                }).collect(Collectors.toList());

        return groupDTOList != null && !groupDTOList.isEmpty() ? groupDTOList.get(0) : null;
    }

    @Override
    @Transactional
    public Boolean saveList(@NotNull List<GroupDTO> input) {
        try {
            for (GroupDTO item : input) {
                Boolean result = save(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean save(@NotNull GroupDTO item) {
        try {
            dslContext.insertInto(group)
                    .set(group.GROUP_NAME, item.groupName)
                    .set(group.AVATAR_GROUP, item.avatarGroup)
                    .set(group.ID_COURSE, item.idCourse)
                    .set(group.CREATED_DATE, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GroupDTO> saveListAndReturn(@NotNull List<GroupDTO> input) {
        return List.of();
    }

    @Override
    public GroupDTO saveAndReturn(@NotNull GroupDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<GroupDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull GroupDTO item) {
        return null;
    }

    @Override
    public List<GroupDTO> mergeListAndReturn(@NotNull List<GroupDTO> input) {
        return List.of();
    }

    @Override
    public GroupDTO mergeAndReturn(@NotNull GroupDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull GroupDTO item) {
        try {
            int result = dslContext.update(group)
                    .set(group.GROUP_NAME, item.groupName)
                    .set(group.AVATAR_GROUP, item.avatarGroup)
                    .set(group.ID_COURSE, item.idCourse)
                    .set(group.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(group.ID_GROUP.eq(item.getIdGroup()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<GroupDTO> input) {
        try {
            for (GroupDTO item : input) {
                Boolean result = update(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(@NotNull GroupDTO item) {
        try {
            int result = dslContext.deleteFrom(group)
                    .where(group.ID_GROUP.eq(item.getIdGroup()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<GroupDTO> input) {
        try {
            for (GroupDTO item : input) {
                Boolean result = delete(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteByID(@NotNull Long id) {
        try {
            int result = dslContext.deleteFrom(group)
                    .where(group.ID_GROUP.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteListByID(@NotNull List<Long> input) {
        try {
            for (Long id : input) {
                Boolean result = deleteByID(id);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
