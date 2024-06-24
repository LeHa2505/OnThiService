package cfm.onthi.repositories;

import cfm.onthi.dtos.UserGroupDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtUserGroup;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserGroupRepository extends BaseRepository<UserGroupDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UserGroupRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<UserGroupDTO>, UserGroupRepository {
    OtUserGroup userGroup = OtUserGroup.OT_USER_GROUP.as("OtUserGroup");

    public UserGroupRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                   @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                   @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<UserGroupDTO> getAll() {
        return List.of();
    }

    @Override
    public List<UserGroupDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public UserGroupDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public UserGroupDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<UserGroupDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull UserGroupDTO item) {
        try {
            dslContext.insertInto(userGroup)
                    .set(userGroup.ID_GROUP, item.idGroup)
                    .set(userGroup.ID_USER, item.idUser)
                    .set(userGroup.CREATED_DATE, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserGroupDTO> saveListAndReturn(@NotNull List<UserGroupDTO> input) {
        return List.of();
    }

    @Override
    public UserGroupDTO saveAndReturn(@NotNull UserGroupDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<UserGroupDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull UserGroupDTO item) {
        return null;
    }

    @Override
    public List<UserGroupDTO> mergeListAndReturn(@NotNull List<UserGroupDTO> input) {
        return List.of();
    }

    @Override
    public UserGroupDTO mergeAndReturn(@NotNull UserGroupDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull UserGroupDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<UserGroupDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull UserGroupDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<UserGroupDTO> input) {
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