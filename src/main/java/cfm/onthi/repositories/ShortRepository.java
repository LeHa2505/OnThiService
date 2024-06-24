package cfm.onthi.repositories;

import cfm.onthi.dtos.ShortDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtCourse;
import cfm.onthi.entities.tables.OtShorts;
import cfm.onthi.entities.tables.OtUser;
import cfm.onthi.entities.tables.OtUserCourse;
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

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface ShortRepository extends BaseRepository<ShortDTO>{
    List<ShortDTO> getShortsByUserId(Long userId);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ShortRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<ShortDTO>, ShortRepository {
    OtShorts shorts = OtShorts.OT_SHORTS.as("OtShorts");
    OtUser user = OtUser.OT_USER.as("OtUser");
    OtCourse course = OtCourse.OT_COURSE.as("OtCourse");
    OtUserCourse userCourse = OtUserCourse.OT_USER_COURSE.as("OtUserCourse");
    UserRepository userRepository;

    public ShortRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                               @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                               @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                               UserRepository userRepository) {
        super(dslContext, entityManager);
        this.userRepository = userRepository;
    }

    @Override
    public List<ShortDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ShortDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_SHORT != null) {
            condition = condition.and(shorts.ID_SHORT.eq(inputCondition.ID_SHORT));
        }

        if (inputCondition.ID_USER != null) {
            condition = condition.and(shorts.ID_USER.eq(inputCondition.ID_USER));
        }

        List<ShortDTO> shortDTOList = dslContext.select()
                .from(shorts).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(shorts), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ShortDTO shortDTO = new ShortDTO();

                    shortDTO.id_short = entry.getKey().getIdShort();
                    shortDTO.id_user = entry.getKey().getIdUser();
                    shortDTO.title = entry.getKey().getTitle();
                    shortDTO.thumbnail = entry.getKey().getThumbnail();
                    shortDTO.video_link = entry.getKey().getVideoLink();
                    shortDTO.description = entry.getKey().getDescription();
                    shortDTO.created_at = entry.getKey().getCreatedAt();
                    shortDTO.modified_at = entry.getKey().getModifiedAt();

                    return shortDTO;
                }).collect(Collectors.toList());

        return shortDTOList;
    }

    @Override
    public ShortDTO getByID(@NotNull Long id) {
        try {
            return dslContext.selectFrom(shorts)
                    .where(shorts.ID_SHORT.eq(id))
                    .fetchOneInto(ShortDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ShortDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_SHORT != null) {
            condition = condition.and(shorts.ID_SHORT.eq(inputCondition.ID_SHORT));
        }

        List<ShortDTO> shortDTOList = dslContext.select()
                .from(shorts).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(shorts), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ShortDTO shortDTO = new ShortDTO();

                    shortDTO.id_short = entry.getKey().getIdShort();
                    shortDTO.id_user = entry.getKey().getIdUser();
                    shortDTO.title = entry.getKey().getTitle();
                    shortDTO.thumbnail = entry.getKey().getThumbnail();
                    shortDTO.video_link = entry.getKey().getVideoLink();
                    shortDTO.description = entry.getKey().getDescription();
                    shortDTO.created_at = entry.getKey().getCreatedAt();
                    shortDTO.modified_at = entry.getKey().getModifiedAt();
                    shortDTO.teacher_info = userRepository.getByID(shortDTO.id_user);

                    return shortDTO;
                }).collect(Collectors.toList());

        return shortDTOList != null && !shortDTOList.isEmpty() ? shortDTOList.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<ShortDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull ShortDTO item) {
        try {
            dslContext.insertInto(shorts)
                    .set(shorts.ID_USER, item.id_user)
                    .set(shorts.VIDEO_LINK, item.video_link)
                    .set(shorts.THUMBNAIL, item.thumbnail)
                    .set(shorts.TITLE, item.title)
                    .set(shorts.DESCRIPTION, item.description)
                    .set(shorts.CREATED_AT, LocalDateTime.now())
                    .set(shorts.MODIFIED_AT, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ShortDTO> saveListAndReturn(@NotNull List<ShortDTO> input) {
        return List.of();
    }

    @Override
    public ShortDTO saveAndReturn(@NotNull ShortDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<ShortDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull ShortDTO item) {
        return null;
    }

    @Override
    public List<ShortDTO> mergeListAndReturn(@NotNull List<ShortDTO> input) {
        return List.of();
    }

    @Override
    public ShortDTO mergeAndReturn(@NotNull ShortDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull ShortDTO item) {
        try {
            int result = dslContext.update(shorts)
                    .set(shorts.ID_USER, item.id_user)
                    .set(shorts.VIDEO_LINK, item.video_link)
                    .set(shorts.THUMBNAIL, item.thumbnail)
                    .set(shorts.TITLE, item.title)
                    .set(shorts.DESCRIPTION, item.description)
                    .set(shorts.MODIFIED_AT, LocalDateTime.now())
                    .where(shorts.ID_SHORT.eq(item.getId_short()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<ShortDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull ShortDTO item) {
        try {
            int result = dslContext.deleteFrom(shorts)
                    .where(shorts.ID_SHORT.eq(item.getId_short()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<ShortDTO> input) {
        return null;
    }

    @Override
    public Boolean deleteByID(@NotNull Long id) {
        return null;
    }

    @Override
    public Boolean deleteListByID(@NotNull List<Long> input) {
        return null;
    }

    @Override
    public List<ShortDTO> getShortsByUserId(Long userId) {
        try {
            return dslContext.select(
                            shorts.ID_SHORT.as("id_short"),
                            shorts.ID_USER.as("id_user"),
                            shorts.VIDEO_LINK.as("video_link"),
                            shorts.THUMBNAIL.as("thumbnail"),
                            shorts.TITLE.as("title"),
                            shorts.DESCRIPTION.as("description"),
                            shorts.CREATED_AT.as("created_at"),
                            shorts.MODIFIED_AT.as("modified_at")
                    )
                    .from(shorts)
                    .join(user).on(user.ID_USER.eq(shorts.ID_USER))
                    .join(course).on(course.ID_TEACHER.eq(user.ID_USER))
                    .join(userCourse).on(userCourse.ID_COURSE.eq(course.ID_COURSE))
                    .where(userCourse.ID_USER.eq(userId))
                    .fetchInto(ShortDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
