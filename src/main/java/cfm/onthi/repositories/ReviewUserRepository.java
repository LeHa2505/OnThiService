package cfm.onthi.repositories;

import cfm.onthi.dtos.ReviewDTO;
import cfm.onthi.dtos.ReviewUserDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtReviewUser;
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

public interface ReviewUserRepository extends BaseRepository<ReviewUserDTO> {
    Boolean deleteByListID(List<Long> ids);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ReviewUserRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<ReviewUserDTO>, ReviewUserRepository {
    OtReviewUser reviewUser = OtReviewUser.OT_REVIEW_USER.as("OtReviewUser");

    public ReviewUserRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                    @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                    @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider
                                    ) {
        super(dslContext, entityManager);
    }

    @Override
    public List<ReviewUserDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ReviewUserDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_REVIEW != null) {
            condition = condition.and(reviewUser.ID_REVIEW.eq(inputCondition.ID_REVIEW));
        }

        List<ReviewUserDTO> reviewUserDTOList = dslContext.select()
                .from(reviewUser).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(reviewUser), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ReviewUserDTO reviewUserDTO = new ReviewUserDTO();

                    reviewUserDTO.idReviewUser = entry.getKey().getIdReviewUser();
                    reviewUserDTO.idReview = entry.getKey().getIdReview();
                    reviewUserDTO.idUser = entry.getKey().getIdUser();
                    reviewUserDTO.action = entry.getKey().getAction();

                    return reviewUserDTO;
                }).collect(Collectors.toList());

        return reviewUserDTOList;
    }

    @Override
    public ReviewUserDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public ReviewUserDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_REVIEW != null) {
            condition = condition.and(reviewUser.ID_REVIEW.eq(inputCondition.ID_REVIEW));
        }

//        if (inputCondition.ID_USER != null)

        List<ReviewUserDTO> reviewUserDTOList = dslContext.select()
                .from(reviewUser).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(reviewUser), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ReviewUserDTO reviewUserDTO = new ReviewUserDTO();

                    reviewUserDTO.idReviewUser = entry.getKey().getIdReviewUser();
                    reviewUserDTO.idReview = entry.getKey().getIdReview();
                    reviewUserDTO.idUser = entry.getKey().getIdUser();
                    reviewUserDTO.action = entry.getKey().getAction();

                    return reviewUserDTO;
                }).collect(Collectors.toList());

        return reviewUserDTOList != null && !reviewUserDTOList.isEmpty() ? reviewUserDTOList.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<ReviewUserDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull ReviewUserDTO item) {
        try {
            dslContext.insertInto(reviewUser)
                    .set(reviewUser.ID_USER, item.getIdUser())
                    .set(reviewUser.ID_REVIEW, item.getIdReview())
                    .set(reviewUser.ACTION, item.action)
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ReviewUserDTO> saveListAndReturn(@NotNull List<ReviewUserDTO> input) {
        return List.of();
    }

    @Override
    public ReviewUserDTO saveAndReturn(@NotNull ReviewUserDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<ReviewUserDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull ReviewUserDTO item) {
        return null;
    }

    @Override
    public List<ReviewUserDTO> mergeListAndReturn(@NotNull List<ReviewUserDTO> input) {
        return List.of();
    }

    @Override
    public ReviewUserDTO mergeAndReturn(@NotNull ReviewUserDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull ReviewUserDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<ReviewUserDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull ReviewUserDTO item) {
        try {
            int result = dslContext.deleteFrom(reviewUser)
                    .where(reviewUser.ID_REVIEW_USER.eq(item.getIdReviewUser()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<ReviewUserDTO> input) {
        try {
            for (ReviewUserDTO item : input) {
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
            int result = dslContext.deleteFrom(reviewUser)
                    .where(reviewUser.ID_REVIEW_USER.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteListByID(@NotNull List<Long> input) {
        return null;
    }

    @Override
    public Boolean deleteByListID(List<Long> ids) {
        try {
            for (Long id : ids) {
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
