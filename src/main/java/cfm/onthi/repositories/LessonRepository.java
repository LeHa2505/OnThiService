package cfm.onthi.repositories;


import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.DocumentDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtLesson;
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

public interface LessonRepository extends BaseRepository<LessonDTO> {
    List<LessonDTO> guestGetListByInputCondition(@NotNull InputCondition inputCondition);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class LessonRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<LessonDTO>, LessonRepository {
    OtLesson lesson = OtLesson.OT_LESSON.as("OtLesson");
    QuizRepository quizRepository;
    DocumentRepository documentRepository;
    ExerciseRepository exerciseRepository;

    public LessonRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                                QuizRepository quizRepository,
                                DocumentRepository documentRepository,
                                ExerciseRepository exerciseRepository) {
        super(dslContext, entityManager);
        this.quizRepository = quizRepository;
        this.documentRepository = documentRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<LessonDTO> getAll() {
        return List.of();
    }

    @Override
    public List<LessonDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(lesson.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        List<LessonDTO> courseDTOList = dslContext.select()
                .from(lesson).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(lesson), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    LessonDTO lessonDTO = new LessonDTO();

                    lessonDTO.idLesson = entry.getKey().getIdLesson();
                    lessonDTO.idCourse = entry.getKey().getIdCourse();
                    lessonDTO.lessonParent = entry.getKey().getLessonParent();
                    lessonDTO.lessonName = entry.getKey().getLessonName();
                    lessonDTO.linkVideo = entry.getKey().getLinkVideo();
                    lessonDTO.duration = entry.getKey().getDuration();
                    lessonDTO.order = entry.getKey().getOrder();
                    lessonDTO.continueTime = entry.getKey().getContinueTime();
                    lessonDTO.view = entry.getKey().getView();
                    lessonDTO.description = entry.getKey().getDescription();

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_LESSON = lessonDTO.idLesson;
                    lessonDTO.documentsInfo = documentRepository.getListByInputCondition(inputConditionIDCourse);

                    return lessonDTO;
                }).collect(Collectors.toList());

        return courseDTOList;
    }

    @Override
    public LessonDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_LESSON = id;

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(lesson.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<LessonDTO> lessonDTOList = dslContext.select()
                .from(lesson).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(lesson), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    LessonDTO lessonDTO = new LessonDTO();

                    lessonDTO.idLesson = entry.getKey().getIdLesson();
                    lessonDTO.idCourse = entry.getKey().getIdCourse();
                    lessonDTO.lessonParent = entry.getKey().getLessonParent();
                    lessonDTO.lessonName = entry.getKey().getLessonName();
                    lessonDTO.linkVideo = entry.getKey().getLinkVideo();
                    lessonDTO.duration = entry.getKey().getDuration();
                    lessonDTO.order = entry.getKey().getOrder();
                    lessonDTO.continueTime = entry.getKey().getContinueTime();
                    lessonDTO.view = entry.getKey().getView();
                    lessonDTO.description = entry.getKey().getDescription();

                    InputCondition inputConditionIDCourse = new InputCondition();
                    inputConditionIDCourse.ID_LESSON = lessonDTO.idLesson;
                    lessonDTO.documentsInfo = documentRepository.getListByInputCondition(inputConditionIDCourse);
                    lessonDTO.exerciseInfo = exerciseRepository.getListByInputCondition(inputConditionIDCourse);

                    return lessonDTO;
                }).collect(Collectors.toList());

        return lessonDTOList != null && !lessonDTOList.isEmpty() ? lessonDTOList.get(0) : null;
    }

    @Override
    public LessonDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    @Transactional
    public Boolean saveList(@NotNull List<LessonDTO> input) {
        try {
            for (LessonDTO item : input) {
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
    public Boolean save(@NotNull LessonDTO item) {
        try {
            dslContext.insertInto(lesson)
                    .set(lesson.ID_COURSE, item.idCourse)
                    .set(lesson.LESSON_PARENT, item.lessonParent)
                    .set(lesson.LESSON_NAME, item.lessonName)
                    .set(lesson.LINK_VIDEO, item.linkVideo)
                    .set(lesson.DURATION, item.duration)
                    .set(lesson.ORDER, item.order)
                    .set(lesson.CONTINUE_TIME, item.continueTime)
                    .set(lesson.VIEW, item.view)
                    .set(lesson.DESCRIPTION, item.description)
                    .set(lesson.CREATED_DATE, LocalDateTime.now())
                    .set(lesson.LAST_MODIFIED_BY, item.idCourse.toString())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LessonDTO> saveListAndReturn(@NotNull List<LessonDTO> input) {
        return List.of();
    }

    @Override
    public LessonDTO saveAndReturn(@NotNull LessonDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<LessonDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull LessonDTO item) {
        return null;
    }

    @Override
    public List<LessonDTO> mergeListAndReturn(@NotNull List<LessonDTO> input) {
        return List.of();
    }

    @Override
    public LessonDTO mergeAndReturn(@NotNull LessonDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull LessonDTO item) {
        try {
            int result = dslContext.update(lesson)
                    .set(lesson.ID_COURSE, item.idCourse)
                    .set(lesson.LESSON_PARENT, item.lessonParent)
                    .set(lesson.LESSON_NAME, item.lessonName)
                    .set(lesson.LINK_VIDEO, item.linkVideo)
                    .set(lesson.DURATION, item.duration)
                    .set(lesson.ORDER, item.order)
                    .set(lesson.CONTINUE_TIME, item.continueTime)
                    .set(lesson.VIEW, item.view)
                    .set(lesson.DESCRIPTION, item.description)
                    .set(lesson.LAST_MODIFIED_BY, item.idCourse.toString())
                    .set(lesson.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(lesson.ID_LESSON.eq(item.getIdLesson()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateList(@NotNull List<LessonDTO> input) {
        try {
            for (LessonDTO item : input) {
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
    public Boolean delete(@NotNull LessonDTO item) {
        try {
            int result = dslContext.deleteFrom(lesson)
                    .where(lesson.ID_LESSON.eq(item.getIdLesson()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<LessonDTO> input) {
        try {
            for (LessonDTO item : input) {
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
            int result = dslContext.deleteFrom(lesson)
                    .where(lesson.ID_LESSON.eq(id))
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
    public List<LessonDTO> guestGetListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_COURSE != null) {
            condition = condition.and(lesson.ID_COURSE.eq(inputCondition.ID_COURSE));
        }

        List<LessonDTO> lessonDTOList = dslContext.select()
                .from(lesson).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(lesson), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    LessonDTO lessonDTO = new LessonDTO();

                    lessonDTO.idLesson = entry.getKey().getIdLesson();
                    lessonDTO.idCourse = entry.getKey().getIdCourse();
                    lessonDTO.lessonParent = entry.getKey().getLessonParent();
                    lessonDTO.lessonName = entry.getKey().getLessonName();
                    lessonDTO.linkVideo = entry.getKey().getLinkVideo();
                    lessonDTO.duration = entry.getKey().getDuration();
                    lessonDTO.order = entry.getKey().getOrder();
                    lessonDTO.continueTime = entry.getKey().getContinueTime();
                    lessonDTO.view = entry.getKey().getView();
                    lessonDTO.description = entry.getKey().getDescription();

                    InputCondition quizInputCondition = new InputCondition();
                    quizInputCondition.ID_LESSON = lessonDTO.idLesson;
                    lessonDTO.exerciseInfo = exerciseRepository.getListByInputCondition(quizInputCondition);
                    lessonDTO.documentsInfo = documentRepository.getListByInputCondition(quizInputCondition);

                    return lessonDTO;
                }).collect(Collectors.toList());

        // Only keep linkVideo for the first 5 items
        for (int i = 5; i < lessonDTOList.size(); i++) {
            lessonDTOList.get(i).setLinkVideo(null);
            lessonDTOList.get(i).setView(null);
            lessonDTOList.get(i).setExerciseInfo(null);
        }

        return lessonDTOList;
    }

}