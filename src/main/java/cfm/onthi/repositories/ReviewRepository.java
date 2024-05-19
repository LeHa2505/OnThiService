package cfm.onthi.repositories;

import cfm.onthi.dtos.ReviewDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtReview;
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

public interface ReviewRepository extends BaseRepository<ReviewDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ReviewRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<ReviewDTO>, ReviewRepository {
    OtReview review = OtReview.OT_REVIEW.as("OtReview");

    public ReviewRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<ReviewDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ReviewDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public ReviewDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public ReviewDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<ReviewDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public List<ReviewDTO> saveListAndReturn(@NotNull List<ReviewDTO> input) {
        return List.of();
    }

    @Override
    public ReviewDTO saveAndReturn(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<ReviewDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public List<ReviewDTO> mergeListAndReturn(@NotNull List<ReviewDTO> input) {
        return List.of();
    }

    @Override
    public ReviewDTO mergeAndReturn(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<ReviewDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull ReviewDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<ReviewDTO> input) {
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
