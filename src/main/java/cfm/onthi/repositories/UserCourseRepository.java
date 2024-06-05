package cfm.onthi.repositories;

import cfm.onthi.dtos.UserCourseDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtUserCourse;
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

public interface UserCourseRepository extends BaseRepository<UserCourseDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UserCourseRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<UserCourseDTO>, UserCourseRepository {
    OtUserCourse userCourse = OtUserCourse.OT_USER_COURSE.as("OtUserCourse");

    public UserCourseRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                    @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                    @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<UserCourseDTO> getAll() {
        return List.of();
    }

    @Override
    public List<UserCourseDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public UserCourseDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public UserCourseDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<UserCourseDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public List<UserCourseDTO> saveListAndReturn(@NotNull List<UserCourseDTO> input) {
        return List.of();
    }

    @Override
    public UserCourseDTO saveAndReturn(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<UserCourseDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public List<UserCourseDTO> mergeListAndReturn(@NotNull List<UserCourseDTO> input) {
        return List.of();
    }

    @Override
    public UserCourseDTO mergeAndReturn(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<UserCourseDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull UserCourseDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<UserCourseDTO> input) {
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
