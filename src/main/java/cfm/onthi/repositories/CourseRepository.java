package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtCourse;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface CourseRepository extends BaseRepository<CourseDTO> {
    CourseDTO guestGetByID(@NotNull Long id);
    Boolean activeCourseExists(@NotNull Long id);
    Boolean inactiveCourseExists(@NotNull Long id);
    CourseDTO adminGetByID(@NotNull Long id);
    List<UserInfoDTO> getStudentsByTeacherId(@NotNull Long idTeacher);
    Boolean submitCourse(@NotNull Long id);
    Boolean unSubmitCourse(@NotNull Long id);
    Long getCourseIdMax();
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class CourseRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<CourseDTO>, CourseRepository {
    OtCourse course = OtCourse.OT_COURSE.as("OtCourse");
    UserRepository userRepository;
    LessonRepository lessonRepository;
    QuizRepository quizRepository;
    ReviewRepository reviewRepository;

    public CourseRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                                UserRepository userRepository,
                                LessonRepository lessonRepository,
                                QuizRepository quizRepository,
                                ReviewRepository reviewRepository
    ) {
        super(dslContext, entityManager);
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.quizRepository = quizRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<CourseDTO> getAll() {
        try {
            return dslContext.selectFrom(course)
                    .fetch()
                    .stream()
                    .map(record -> {
                        CourseDTO courseDTO = new CourseDTO();
                        courseDTO.id_course = record.getValue(course.ID_COURSE);
                        courseDTO.id_teacher = record.getValue(course.ID_TEACHER);
                        courseDTO.avatar_course = record.getValue(course.AVATAR_COURSE);
                        courseDTO.category_name = record.getValue(course.CATEGORY_NAME);
                        courseDTO.isSubmitted = record.getValue(course.IS_SUBMITTED);
                        courseDTO.course_name = record.getValue(course.COURSE_NAME);
                        courseDTO.type_course = record.getValue(course.TYPE_COURSE);
                        courseDTO.start_date = record.getValue(course.START_DATE);
                        courseDTO.end_date = record.getValue(course.END_DATE);
                        courseDTO.description = record.getValue(course.DESCRIPTION);
                        courseDTO.active = record.getValue(course.ACTIVE);
                        courseDTO.isCheck = record.getValue(course.IS_CHECK);
                        courseDTO.price = record.getValue(course.PRICE);
                        courseDTO.discount = record.getValue(course.DISCOUNT);
                        courseDTO.teacher_info = this.userRepository.getByID(record.getValue(course.ID_TEACHER));

                        InputCondition inputConditionIDCourse = new InputCondition();
                        inputConditionIDCourse.ID_COURSE = record.getValue(course.ID_COURSE);

                        List<LessonDTO> lessons = lessonRepository.getListByInputCondition(inputConditionIDCourse);
                        courseDTO.lesson_quantity = lessons.size();
                        courseDTO.quiz_quantity = 0;

                        for (LessonDTO lesson : lessons) {
                            InputCondition inputConditionIDLesson = new InputCondition();
                            inputConditionIDLesson.ID_LESSON = lesson.getIdLesson();
                            courseDTO.quiz_quantity += quizRepository.getListByInputCondition(inputConditionIDLesson).size();
                        }

                        return courseDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<CourseDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ACTIVE != null) {
            condition = condition.and(course.ACTIVE.eq(inputCondition.ACTIVE));
        }

        if (inputCondition.IS_CHECK != null) {
            condition = condition.and(course.IS_CHECK.eq(inputCondition.IS_CHECK));
        }

        if (inputCondition.ID_USER != null) {
            condition = condition.and(course.ID_TEACHER.eq(inputCondition.ID_USER));
        }

        if (inputCondition.LIST_CATEGORY_NAME != null && !inputCondition.LIST_CATEGORY_NAME.isEmpty()) {
            // Convert each CATEGORY_NAME in LIST_CATEGORY_NAME to lowercase
            List<String> lowerCaseCategoryNames = inputCondition.LIST_CATEGORY_NAME.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            // Add condition to compare lowercased CATEGORY_NAME
            condition = condition.and(course.CATEGORY_NAME.in(lowerCaseCategoryNames));
        }

        if (inputCondition.COURSE_NAME != null && !inputCondition.COURSE_NAME.isBlank() && !inputCondition.COURSE_NAME.isEmpty()) {
            condition = condition.and(course.COURSE_NAME.likeIgnoreCase("%" + inputCondition.COURSE_NAME + "%"));
        }

        if (inputCondition.TYPE_COURSE != null) {
            condition = condition.and(course.TYPE_COURSE.eq(inputCondition.TYPE_COURSE));
        }

        List<Long> teacherIds = new ArrayList<>();
        if (inputCondition.USERNAME != null && !inputCondition.USERNAME.isBlank() && !inputCondition.USERNAME.isEmpty()) {
            InputCondition inputConditionFindUserIds = new InputCondition();
            inputConditionFindUserIds.USERNAME = inputCondition.USERNAME;
            List<UserInfoDTO> teachers = userRepository.getListByInputCondition(inputConditionFindUserIds);

            teacherIds = teachers.stream().map(UserInfoDTO::getIdUser).collect(Collectors.toList());

            // Add condition to filter courses by teacher IDs
            if (!teacherIds.isEmpty()) {
                condition = condition.and(course.ID_TEACHER.in(teacherIds));
            }
        }

        List<CourseDTO> courseDTOList = dslContext.select()
                .from(course).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(course), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    CourseDTO courseDTO = new CourseDTO();

                    courseDTO.id_course = entry.getKey().getIdCourse();
                    courseDTO.id_teacher = entry.getKey().getIdTeacher();
                    courseDTO.active = entry.getKey().getActive();
                    courseDTO.avatar_course = entry.getKey().getAvatarCourse();
                    courseDTO.category_name = entry.getKey().getCategoryName();
                    courseDTO.isSubmitted = entry.getKey().getIsSubmitted();
                    courseDTO.course_name = entry.getKey().getCourseName();
                    courseDTO.type_course = entry.getKey().getTypeCourse();
                    courseDTO.start_date = entry.getKey().getStartDate();
                    courseDTO.end_date = entry.getKey().getEndDate();
                    courseDTO.description = entry.getKey().getDescription();
                    courseDTO.price = entry.getKey().getPrice();
                    courseDTO.discount = entry.getKey().getDiscount();
                    courseDTO.teacher_info = this.userRepository.getByID(entry.getKey().getIdTeacher());

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_COURSE = entry.getKey().getIdCourse();

                    List<LessonDTO> lessons = lessonRepository.getListByInputCondition(inputConditionIDCourse);
                    courseDTO.lesson_quantity = lessons.size();
                    courseDTO.quiz_quantity = 0;

                    for (LessonDTO lesson : lessons) {
                        InputCondition inputConditionIDLesson = new InputCondition();
                        inputConditionIDLesson.ID_LESSON = lesson.getIdLesson();
                        courseDTO.quiz_quantity += quizRepository.getListByInputCondition(inputConditionIDLesson).size();
                    }

                    return courseDTO;
                }).collect(Collectors.toList());

        return courseDTOList;
    }

    @Override
    public CourseDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();

        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_COURSE = id;
        inputCondition.ACTIVE = true;

        condition = condition.and(course.ID_COURSE.eq(inputCondition.ID_COURSE));
        condition = condition.and(course.ACTIVE.eq(inputCondition.ACTIVE));

        List<CourseDTO> courseDTOList = dslContext.select()
                .from(course).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(course), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    CourseDTO courseDTO = new CourseDTO();

                    courseDTO.id_course = entry.getKey().getIdCourse();
                    courseDTO.id_teacher = entry.getKey().getIdTeacher();
                    courseDTO.avatar_course = entry.getKey().getAvatarCourse();
                    courseDTO.active = entry.getKey().getActive();
                    courseDTO.isCheck = entry.getKey().getIsCheck();
                    courseDTO.category_name = entry.getKey().getCategoryName();
                    courseDTO.isSubmitted = entry.getKey().getIsSubmitted();
                    courseDTO.course_name = entry.getKey().getCourseName();
                    courseDTO.type_course = entry.getKey().getTypeCourse();
                    courseDTO.start_date = entry.getKey().getStartDate();
                    courseDTO.end_date = entry.getKey().getEndDate();
                    courseDTO.description = entry.getKey().getDescription();
                    courseDTO.price = entry.getKey().getPrice();
                    courseDTO.discount = entry.getKey().getDiscount();
                    courseDTO.teacher_info = this.userRepository.getByID(entry.getKey().getIdTeacher());

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_COURSE = entry.getKey().getIdCourse();

                    List<LessonDTO> lessons = lessonRepository.getListByInputCondition(inputConditionIDCourse);
                    courseDTO.lesson_quantity = lessons.size();
                    courseDTO.lesson_info = lessons;

                    courseDTO.quiz_quantity = 0;

                    for (LessonDTO lesson : lessons) {
                        InputCondition inputConditionIDLesson = new InputCondition();
                        inputConditionIDLesson.ID_LESSON = lesson.getIdLesson();
                        courseDTO.quiz_quantity += quizRepository.getListByInputCondition(inputConditionIDLesson).size();
                    }

                    return courseDTO;
                }).collect(Collectors.toList());

        return courseDTOList != null && !courseDTOList.isEmpty() ? courseDTOList.get(0) : null;
    }

    @Override
    public CourseDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    @Transactional
    public Boolean saveList(@NotNull List<CourseDTO> input) {
        try {
            for (CourseDTO item : input) {
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
    public Boolean save(@NotNull CourseDTO item) {
        try {
            dslContext.insertInto(course)
                    .set(course.ID_TEACHER, item.id_teacher)
                    .set(course.AVATAR_COURSE, item.avatar_course)
                    .set(course.ACTIVE, false)
                    .set(course.IS_CHECK, false)
                    .set(course.CATEGORY_NAME, item.category_name)
                    .set(course.IS_SUBMITTED, false)
                    .set(course.COURSE_NAME, item.course_name)
                    .set(course.TYPE_COURSE, item.type_course)
                    .set(course.START_DATE, item.start_date)
                    .set(course.END_DATE, item.end_date)
                    .set(course.DESCRIPTION, item.description)
                    .set(course.PRICE, item.price)
                    .set(course.DISCOUNT, item.discount)
                    .set(course.CREATED_DATE, LocalDateTime.now())
                    .set(course.LAST_MODIFIED_BY, item.id_teacher.toString())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CourseDTO> saveListAndReturn(@NotNull List<CourseDTO> input) {
        return List.of();
    }

    @Override
    public CourseDTO saveAndReturn(@NotNull CourseDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<CourseDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull CourseDTO item) {
        return null;
    }

    @Override
    public List<CourseDTO> mergeListAndReturn(@NotNull List<CourseDTO> input) {
        return List.of();
    }

    @Override
    public CourseDTO mergeAndReturn(@NotNull CourseDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull CourseDTO item) {
        try {
            int result = dslContext.update(course)
                    .set(course.ID_TEACHER, item.id_teacher)
                    .set(course.AVATAR_COURSE, item.avatar_course)
                    .set(course.CATEGORY_NAME, item.category_name)
                    .set(course.ACTIVE, item.active)
                    .set(course.IS_SUBMITTED, item.isSubmitted)
                    .set(course.COURSE_NAME, item.course_name)
                    .set(course.TYPE_COURSE, item.type_course)
                    .set(course.START_DATE, item.start_date)
                    .set(course.END_DATE, item.end_date)
                    .set(course.DESCRIPTION, item.description)
                    .set(course.PRICE, item.price)
                    .set(course.DISCOUNT, item.discount)
                    .set(course.LAST_MODIFIED_BY, item.id_teacher.toString())
                    .set(course.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(course.ID_COURSE.eq(item.getId_course()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateList(@NotNull List<CourseDTO> input) {
        try {
                for (CourseDTO item : input) {
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
    public Boolean delete(@NotNull CourseDTO item) {
        try {
            int result = dslContext.deleteFrom(course)
                    .where(course.ID_COURSE.eq(item.getId_course()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<CourseDTO> input) {
        try {
            for (CourseDTO item : input) {
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
            int result = dslContext.deleteFrom(course)
                    .where(course.ID_COURSE.eq(id))
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

    @Override
    public CourseDTO guestGetByID(@NotNull Long id) {
        Condition condition = trueCondition();

        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_COURSE = id;
        condition = condition.and(course.ID_COURSE.eq(inputCondition.ID_COURSE));

        List<CourseDTO> courseDTOList = dslContext.select()
                .from(course).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(course), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    CourseDTO courseDTO = new CourseDTO();

                    courseDTO.id_course = entry.getKey().getIdCourse();
                    courseDTO.id_teacher = entry.getKey().getIdTeacher();
                    courseDTO.avatar_course = entry.getKey().getAvatarCourse();
                    courseDTO.category_name = entry.getKey().getCategoryName();
                    courseDTO.isSubmitted = entry.getKey().getIsSubmitted();
                    courseDTO.course_name = entry.getKey().getCourseName();
                    courseDTO.type_course = entry.getKey().getTypeCourse();
                    courseDTO.start_date = entry.getKey().getStartDate();
                    courseDTO.end_date = entry.getKey().getEndDate();
                    courseDTO.description = entry.getKey().getDescription();
                    courseDTO.price = entry.getKey().getPrice();
                    courseDTO.discount = entry.getKey().getDiscount();
                    courseDTO.teacher_info = this.userRepository.getByID(entry.getKey().getIdTeacher());

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_COURSE = entry.getKey().getIdCourse();

                    List<LessonDTO> lessons = lessonRepository.guestGetListByInputCondition(inputConditionIDCourse);
                    courseDTO.lesson_quantity = lessons.size();
                    courseDTO.lesson_info = lessons;

                    courseDTO.quiz_quantity = 0;

                    for (LessonDTO lesson : lessons) {
                        InputCondition inputConditionIDLesson = new InputCondition();
                        inputConditionIDLesson.ID_LESSON = lesson.getIdLesson();
                        courseDTO.quiz_quantity += quizRepository.getListByInputCondition(inputConditionIDLesson).size();
                    }

                    return courseDTO;
                }).collect(Collectors.toList());

        return courseDTOList != null && !courseDTOList.isEmpty() ? courseDTOList.get(0) : null;
    }

    @Override
    public Boolean activeCourseExists(@NotNull Long id) {
        try {
            int result = dslContext.update(course)
                    .set(course.ACTIVE, true)
                    .set(course.IS_CHECK, true)
                    .where(course.ID_COURSE.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean inactiveCourseExists(@NotNull Long id) {
        try {
            int result = dslContext.update(course)
                    .set(course.ACTIVE, false)
                    .set(course.IS_CHECK, true)
                    .where(course.ID_COURSE.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CourseDTO adminGetByID(@NotNull Long id) {
        Condition condition = trueCondition();

        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_COURSE = id;

        condition = condition.and(course.ID_COURSE.eq(inputCondition.ID_COURSE));

        List<CourseDTO> courseDTOList = dslContext.select()
                .from(course).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(course), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    CourseDTO courseDTO = new CourseDTO();

                    courseDTO.id_course = entry.getKey().getIdCourse();
                    courseDTO.id_teacher = entry.getKey().getIdTeacher();
                    courseDTO.avatar_course = entry.getKey().getAvatarCourse();
                    courseDTO.active = entry.getKey().getActive();
                    courseDTO.isCheck = entry.getKey().getIsCheck();
                    courseDTO.category_name = entry.getKey().getCategoryName();
                    courseDTO.isSubmitted = entry.getKey().getIsSubmitted();
                    courseDTO.course_name = entry.getKey().getCourseName();
                    courseDTO.type_course = entry.getKey().getTypeCourse();
                    courseDTO.start_date = entry.getKey().getStartDate();
                    courseDTO.end_date = entry.getKey().getEndDate();
                    courseDTO.description = entry.getKey().getDescription();
                    courseDTO.price = entry.getKey().getPrice();
                    courseDTO.discount = entry.getKey().getDiscount();
                    courseDTO.teacher_info = this.userRepository.getByID(entry.getKey().getIdTeacher());

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_COURSE = entry.getKey().getIdCourse();

                    List<LessonDTO> lessons = lessonRepository.getListByInputCondition(inputConditionIDCourse);
                    courseDTO.lesson_quantity = lessons.size();
                    courseDTO.lesson_info = lessons;

                    courseDTO.quiz_quantity = 0;

                    for (LessonDTO lesson : lessons) {
                        InputCondition inputConditionIDLesson = new InputCondition();
                        inputConditionIDLesson.ID_LESSON = lesson.getIdLesson();
                        courseDTO.quiz_quantity += quizRepository.getListByInputCondition(inputConditionIDLesson).size();
                    }

                    return courseDTO;
                }).collect(Collectors.toList());

        return courseDTOList != null && !courseDTOList.isEmpty() ? courseDTOList.get(0) : null;
    }

    @Override
    public List<UserInfoDTO> getStudentsByTeacherId(@NotNull Long idTeacher) {
        OtUser user = OtUser.OT_USER.as("s");
        OtUserCourse userCourse = OtUserCourse.OT_USER_COURSE.as("sc");
        OtCourse course = OtCourse.OT_COURSE.as("c");

        try {
            return dslContext.select()
                    .from(user)
                    .join(userCourse).on(user.ID_USER.eq(userCourse.ID_USER))
                    .join(course).on(userCourse.ID_COURSE.eq(course.ID_COURSE))
                    .where(course.ID_TEACHER.eq(idTeacher))
                    .fetch()
                    .map(record -> {
                        UserInfoDTO studentDTO = new UserInfoDTO();
                        studentDTO.idUser = record.get(user.ID_USER);
                        studentDTO.idSchool = record.get(user.ID_SCHOOL);
                        studentDTO.typeUser = record.get(user.TYPE_USER);
                        studentDTO.username = record.get(user.USERNAME);
                        studentDTO.email = record.get(user.EMAIL);
                        studentDTO.phone = record.get(user.PHONE);
                        studentDTO.grade = record.get(user.GRADE);
                        studentDTO.gender = record.get(user.GENDER);
                        studentDTO.bod = record.get(user.BOD);
                        studentDTO.address = record.get(user.ADDRESS);
                        studentDTO.description = record.get(user.DESCRIPTION);
                        studentDTO.facebook = record.get(user.FACEBOOK);
                        studentDTO.instagram = record.get(user.INSTAGRAM);
                        studentDTO.avatar = record.get(user.AVATAR);
                        studentDTO.idCourse = record.get(course.ID_COURSE);
                        studentDTO.courseName = record.get(course.COURSE_NAME);
                        // Map other fields as necessary
                        return studentDTO;
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Boolean submitCourse(@NotNull Long id) {
        try {
            int result = dslContext.update(course)
                    .set(course.IS_SUBMITTED, true)
                    .where(course.ID_COURSE.eq(id))
                            .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean unSubmitCourse(@NotNull Long id) {
        try {
            int result = dslContext.update(course)
                    .set(course.IS_SUBMITTED, false)
                    .where(course.ID_COURSE.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long getCourseIdMax() {
        try {
            return dslContext.select(course.ID_COURSE.max())
                    .from(course)
                    .fetchOne(0, Long.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
