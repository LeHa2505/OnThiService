package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.UserCourseDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface UserCourseRepository extends BaseRepository<UserCourseDTO> {
    List<UserInfoDTO> findUsersByCourseId(@NotNull Long courseId);
    Long getMaxUserCourseId();
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UserCourseRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<UserCourseDTO>, UserCourseRepository {
    OtUserCourse userCourse = OtUserCourse.OT_USER_COURSE.as("OtUserCourse");
    CourseRepository courseRepository;
    UserRepository userRepository;

    public UserCourseRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                    @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                    @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                                    CourseRepository courseRepository,
                                    UserRepository userRepository) {
        super(dslContext, entityManager);
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserCourseDTO> getAll() {
        return List.of();
    }

    @Override
    public List<UserCourseDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_USER != null) {
            condition = condition.and(userCourse.ID_USER.eq(inputCondition.ID_USER));
        }

        if (inputCondition.ID_USER_COURSE != null) {
            condition = condition.and(userCourse.ID_USER_COURSE.eq(inputCondition.ID_USER_COURSE));
        }

        List<UserCourseDTO> userCourseDTOList = dslContext.select()
                .from(userCourse).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(userCourse), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    UserCourseDTO userCourseDTO = new UserCourseDTO();

                    userCourseDTO.idUserCourse = entry.getKey().getIdUserCourse();
                    userCourseDTO.idCourse = entry.getKey().getIdCourse();
                    userCourseDTO.idUser = entry.getKey().getIdUser();
                    userCourseDTO.learnedLesson = entry.getKey().getLearnedLesson();
                    userCourseDTO.learningLesson = entry.getKey().getLearningLesson();
                    userCourseDTO.timeSchedule = entry.getKey().getTimeSchedule();
                    userCourseDTO.courseInfo = courseRepository.getByID(userCourseDTO.idCourse);
                    userCourseDTO.classmates = findUsersByCourseId(userCourseDTO.idCourse);

                    return userCourseDTO;
                }).collect(Collectors.toList());

        return userCourseDTOList;
    }

    @Override
    public UserCourseDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public UserCourseDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_USER != null) {
            condition = condition.and(userCourse.ID_USER.eq(inputCondition.ID_USER));
        }

        if (inputCondition.ID_USER_COURSE != null) {
            condition = condition.and(userCourse.ID_USER_COURSE.eq(inputCondition.ID_USER_COURSE));
        }

        List<UserCourseDTO> userCourseDTOList = dslContext.select()
                .from(userCourse).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(userCourse), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    UserCourseDTO userCourseDTO = new UserCourseDTO();

                    userCourseDTO.idUserCourse = entry.getKey().getIdUserCourse();
                    userCourseDTO.idCourse = entry.getKey().getIdCourse();
                    userCourseDTO.idUser = entry.getKey().getIdUser();
                    userCourseDTO.learnedLesson = entry.getKey().getLearnedLesson();
                    userCourseDTO.learningLesson = entry.getKey().getLearningLesson();
                    userCourseDTO.timeSchedule = entry.getKey().getTimeSchedule();
                    userCourseDTO.courseInfo = courseRepository.getByID(userCourseDTO.idCourse);
                    userCourseDTO.classmates = findUsersByCourseId(userCourseDTO.idCourse);

                    return userCourseDTO;
                }).collect(Collectors.toList());

        return userCourseDTOList != null && !userCourseDTOList.isEmpty() ? userCourseDTOList.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<UserCourseDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull UserCourseDTO item) {
        try {
            dslContext.insertInto(userCourse)
                    .set(userCourse.ID_COURSE, item.idCourse)
                    .set(userCourse.ID_USER, item.idUser)
                    .set(userCourse.LEARNING_LESSON, item.learningLesson)
                    .set(userCourse.LEARNED_LESSON, item.learnedLesson)
                    .set(userCourse.TIME_SCHEDULE, item.timeSchedule)
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            int result = dslContext.update(userCourse)
                    .set(userCourse.ID_COURSE, item.idCourse)
                    .set(userCourse.ID_USER, item.idUser)
                    .set(userCourse.LEARNING_LESSON, item.learningLesson)
                    .set(userCourse.LEARNED_LESSON, item.learnedLesson)
                    .where(userCourse.ID_USER_COURSE.eq(item.getIdUserCourse()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    @Override
    public List<UserInfoDTO> findUsersByCourseId(@NotNull Long courseId) {
        Condition condition = trueCondition();
        InputCondition inputConditionCourseId = new InputCondition();
        inputConditionCourseId.ID_COURSE = courseId;

        condition = condition.and(userCourse.ID_COURSE.eq(inputConditionCourseId.ID_COURSE));

        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();

        List<UserCourseDTO> userCourseDTOList = dslContext.select()
                .from(userCourse).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(userCourse), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    UserCourseDTO userCourseDTO = new UserCourseDTO();

                    userCourseDTO.idUser = entry.getKey().getIdUser();
                    userInfoDTOList.add(userRepository.getByID(userCourseDTO.idUser));

                    return userCourseDTO;
                }).collect(Collectors.toList());

        return userInfoDTOList;
    }

    @Override
    public Long getMaxUserCourseId() {
        try {
            return dslContext.select(userCourse.ID_USER_COURSE.max())
                    .from(userCourse)
                    .fetchOne(0, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
