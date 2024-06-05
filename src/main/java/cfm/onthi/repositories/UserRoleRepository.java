package cfm.onthi.repositories;


import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.UserRoleDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtUserRole;
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

import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRoleDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UserRoleRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<UserRoleDTO>, UserRoleRepository {
    OtUserRole userRole = OtUserRole.OT_USER_ROLE.as("userRole");

    public UserRoleRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                  @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                  @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<UserRoleDTO> getAll() {
        return List.of();
    }

    @Override
    public List<UserRoleDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public UserRoleDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public UserRoleDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<UserRoleDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public List<UserRoleDTO> saveListAndReturn(@NotNull List<UserRoleDTO> input) {
        return List.of();
    }

    @Override
    public UserRoleDTO saveAndReturn(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<UserRoleDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public List<UserRoleDTO> mergeListAndReturn(@NotNull List<UserRoleDTO> input) {
        return List.of();
    }

    @Override
    public UserRoleDTO mergeAndReturn(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<UserRoleDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull UserRoleDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<UserRoleDTO> input) {
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
