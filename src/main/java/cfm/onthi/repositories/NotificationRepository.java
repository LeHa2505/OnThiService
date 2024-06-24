package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.NotificationDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtNotification;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.N;
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

public interface NotificationRepository extends BaseRepository<NotificationDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class NotificationRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<NotificationDTO>, NotificationRepository {
    OtNotification notification = OtNotification.OT_NOTIFICATION.as("OtNotification");

    public NotificationRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                      @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                      @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<NotificationDTO> getAll() {
        return List.of();
    }

    @Override
    public List<NotificationDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_USER != null) {
            condition = condition.and(notification.ID_USER.eq(inputCondition.ID_USER));
        }

        List<NotificationDTO> notificationDTOList = dslContext.select()
                .from(notification).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(notification), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    NotificationDTO notificationDTO = new NotificationDTO();

                    notificationDTO.idNotification = entry.getKey().getIdNotification();
                    notificationDTO.idUser = entry.getKey().getIdUser();
                    notificationDTO.content = entry.getKey().getContent();
                    notificationDTO.typeNotification = entry.getKey().getTypeNotification();
                    notificationDTO.isSeen = entry.getKey().getIsSeen();
                    notificationDTO.createdDate = entry.getKey().getCreatedDate();

                    return notificationDTO;
                }).collect(Collectors.toList());

        return notificationDTOList;
    }

    @Override
    public NotificationDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public NotificationDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<NotificationDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull NotificationDTO item) {
        try {
            dslContext.insertInto(notification)
                    .set(notification.ID_USER, item.idUser)
                    .set(notification.IS_SEEN, item.isSeen)
                    .set(notification.TYPE_NOTIFICATION, item.typeNotification)
                    .set(notification.CONTENT, item.content)
                    .set(notification.CREATED_DATE, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NotificationDTO> saveListAndReturn(@NotNull List<NotificationDTO> input) {
        return List.of();
    }

    @Override
    public NotificationDTO saveAndReturn(@NotNull NotificationDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<NotificationDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull NotificationDTO item) {
        return null;
    }

    @Override
    public List<NotificationDTO> mergeListAndReturn(@NotNull List<NotificationDTO> input) {
        return List.of();
    }

    @Override
    public NotificationDTO mergeAndReturn(@NotNull NotificationDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull NotificationDTO item) {
        try {
            int result = dslContext.update(notification)
                    .set(notification.ID_USER, item.idUser)
                    .set(notification.IS_SEEN, item.isSeen)
                    .set(notification.TYPE_NOTIFICATION, item.typeNotification)
                    .set(notification.CONTENT, item.content)
                    .where(notification.ID_NOTIFICATION.eq(item.getIdNotification()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<NotificationDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull NotificationDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<NotificationDTO> input) {
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
}