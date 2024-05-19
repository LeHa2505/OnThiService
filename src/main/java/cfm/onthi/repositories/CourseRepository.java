package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtCourse;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface CourseRepository extends BaseRepository<CourseDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class CourseRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<CourseDTO>, CourseRepository {
    OtCourse course = OtCourse.OT_COURSE.as("OtCourse");

    public CourseRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
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
                        courseDTO.category_name = record.getValue(course.CATEGORY_NAME);
                        courseDTO.schedule = record.getValue(course.SCHEDULE);
                        courseDTO.course_name = record.getValue(course.COURSE_NAME);
                        courseDTO.type_course = record.getValue(course.TYPE_COURSE);
                        courseDTO.start_date = record.getValue(course.START_DATE);
                        courseDTO.end_date = record.getValue(course.END_DATE);
                        courseDTO.duration = record.getValue(course.DURATION);
                        courseDTO.description = record.getValue(course.DESCRIPTION);
                        courseDTO.price = record.getValue(course.PRICE);
                        courseDTO.discount = record.getValue(course.DISCOUNT);
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

        if (inputCondition.CATEGORY_NAME != null && !inputCondition.CATEGORY_NAME.isBlank() && !inputCondition.CATEGORY_NAME.isEmpty()) {
            condition = condition.and(course.CATEGORY_NAME.likeIgnoreCase("%" + inputCondition.CATEGORY_NAME + "%"));
        }

        if (inputCondition.COURSE_NAME != null && !inputCondition.COURSE_NAME.isBlank() && !inputCondition.COURSE_NAME.isEmpty()) {
            condition = condition.and(course.COURSE_NAME.likeIgnoreCase("%" + inputCondition.COURSE_NAME + "%"));
        }

        if (inputCondition.TYPE_COURSE != null) {
            condition = condition.and(course.TYPE_COURSE.eq(inputCondition.TYPE_COURSE));
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
                    courseDTO.category_name = entry.getKey().getCategoryName();
                    courseDTO.schedule = entry.getKey().getSchedule();
                    courseDTO.course_name = entry.getKey().getCourseName();
                    courseDTO.type_course = entry.getKey().getTypeCourse();
                    courseDTO.start_date = entry.getKey().getStartDate();
                    courseDTO.end_date = entry.getKey().getEndDate();
                    courseDTO.duration = entry.getKey().getDuration();
                    courseDTO.description = entry.getKey().getDescription();
                    courseDTO.price = entry.getKey().getPrice();
                    courseDTO.discount = entry.getKey().getDiscount();

                    return courseDTO;
                }).collect(Collectors.toList());

        return courseDTOList;
    }

    @Override
    public CourseDTO getByID(@NotNull Long id) {
        try {
            return dslContext.selectFrom(course)
                    .where(course.ID_COURSE.eq(id))
                    .fetchOneInto(CourseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
                    .set(course.CATEGORY_NAME, item.category_name)
                    .set(course.SCHEDULE, item.schedule)
                    .set(course.COURSE_NAME, item.course_name)
                    .set(course.TYPE_COURSE, item.type_course)
                    .set(course.START_DATE, item.start_date)
                    .set(course.END_DATE, item.end_date)
                    .set(course.DURATION, item.duration)
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
                    .set(course.CATEGORY_NAME, item.category_name)
                    .set(course.SCHEDULE, item.schedule)
                    .set(course.COURSE_NAME, item.course_name)
                    .set(course.TYPE_COURSE, item.type_course)
                    .set(course.START_DATE, item.start_date)
                    .set(course.END_DATE, item.end_date)
                    .set(course.DURATION, item.duration)
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
}
