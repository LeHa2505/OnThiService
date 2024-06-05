package cfm.onthi.repositories;

import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.ReviewDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtReview;
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

public interface ReviewRepository extends BaseRepository<ReviewDTO> {
    List<ReviewDTO> getByIDCourse(Long id);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ReviewRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<ReviewDTO>, ReviewRepository {
    OtReview review = OtReview.OT_REVIEW.as("OtReview");
    UserRepository userRepository;
    ReviewUserRepository reviewUserRepository;

    public ReviewRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                                UserRepository userRepository,
                                ReviewUserRepository reviewUserRepository
    ) {
        super(dslContext, entityManager);
        this.userRepository = userRepository;
        this.reviewUserRepository = reviewUserRepository;
    }

    @Override
    public List<ReviewDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ReviewDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(review.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        if (inputCondition.ID_REVIEW != null) {
            condition = condition.and(review.ID_REVIEW.eq(inputCondition.ID_REVIEW));
        }

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(review.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<ReviewDTO> reviewDTOList = dslContext.select()
                .from(review).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(review), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ReviewDTO reviewDTO = new ReviewDTO();

                    reviewDTO.idReview = entry.getKey().getIdReview();
                    reviewDTO.idCourse = entry.getKey().getIdCourse();
                    reviewDTO.idUser = entry.getKey().getIdUser();
                    reviewDTO.content = entry.getKey().getContent();
                    reviewDTO.rating = entry.getKey().getRating();
                    reviewDTO.like = entry.getKey().getLike();
                    reviewDTO.dislike = entry.getKey().getDislike();
                    reviewDTO.createdDate = entry.getKey().getCreatedDate();
                    reviewDTO.userInfo = userRepository.getByID(reviewDTO.idUser);
                    reviewDTO.idLesson = entry.getKey().getIdLesson();

                    InputCondition inputConditionIDReview = new InputCondition();
                    inputConditionIDReview.ID_REVIEW = reviewDTO.idReview;
                    reviewDTO.userInteractList = reviewUserRepository.getListByInputCondition(inputConditionIDReview);

                    return reviewDTO;
                }).collect(Collectors.toList());

        return reviewDTOList;
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
        try {
            dslContext.insertInto(review)
                    .set(review.ID_COURSE, item.getIdCourse())
                    .set(review.ID_USER, item.getIdUser())
                    .set(review.ID_LESSON, item.getIdLesson())
                    .set(review.CONTENT, item.getContent())
                    .set(review.RATING, item.rating)
                    .set(review.LIKE, item.like == null ? 0 : item.like)
                    .set(review.DISLIKE, item.dislike == null ? 0 : item.dislike)
                    .set(review.CREATED_DATE, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            int result = dslContext.update(review)
                    .set(review.ID_COURSE, item.getIdCourse())
                    .set(review.ID_USER, item.getIdUser())
                    .set(review.CONTENT, item.getContent())
                    .set(review.ID_LESSON, item.getIdLesson())
                    .set(review.RATING, item.rating)
                    .set(review.LIKE, item.like)
                    .set(review.DISLIKE, item.dislike)
                    .set(review.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(review.ID_REVIEW.eq(item.getIdReview()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            int result = dslContext.deleteFrom(review)
                    .where(review.ID_REVIEW.eq(id))
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
    public List<ReviewDTO> getByIDCourse(Long id) {
        // Khởi tạo điều kiện ban đầu là true
        Condition condition = trueCondition();

        // Tạo đối tượng InputCondition và gán giá trị ID_COURSE từ tham số đầu vào
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_COURSE = id;

        // Nếu ID_COURSE không null, thêm điều kiện ID_COURSE bằng với giá trị của inputCondition.ID_COURSE
        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(review.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        // Thêm điều kiện ID_LESSON phải null
        condition = condition.and(review.ID_LESSON.isNull());

        // Thực hiện truy vấn, ánh xạ kết quả và tạo danh sách ReviewDTO
        List<ReviewDTO> reviewDTOList = dslContext.select()
                .from(review)
                .where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(review), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ReviewDTO reviewDTO = new ReviewDTO();

                    reviewDTO.idReview = entry.getKey().getIdReview();
                    reviewDTO.idCourse = entry.getKey().getIdCourse();
                    reviewDTO.idUser = entry.getKey().getIdUser();
                    reviewDTO.content = entry.getKey().getContent();
                    reviewDTO.rating = entry.getKey().getRating();
                    reviewDTO.like = entry.getKey().getLike();
                    reviewDTO.dislike = entry.getKey().getDislike();
                    reviewDTO.createdDate = entry.getKey().getCreatedDate();
                    reviewDTO.userInfo = userRepository.getByID(reviewDTO.idUser);
                    reviewDTO.idLesson = entry.getKey().getIdLesson();

                    InputCondition inputConditionIDReview = new InputCondition();
                    inputConditionIDReview.ID_REVIEW = reviewDTO.idReview;
                    reviewDTO.userInteractList = reviewUserRepository.getListByInputCondition(inputConditionIDReview);

                    return reviewDTO;
                }).collect(Collectors.toList());

        return reviewDTOList;
    }

}
