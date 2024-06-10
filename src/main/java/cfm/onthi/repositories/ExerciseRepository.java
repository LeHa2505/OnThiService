package cfm.onthi.repositories;

import cfm.onthi.dtos.ExerciseDTO;
import cfm.onthi.dtos.QuizDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtExercise;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface ExerciseRepository extends BaseRepository<ExerciseDTO>{
}
@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ExerciseRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<ExerciseDTO>, ExerciseRepository {
    OtExercise exercise = OtExercise.OT_EXERCISE.as("OtExercise");
    QuizRepository quizRepository;

    public ExerciseRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                  @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                  @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                                  QuizRepository quizRepository
    ) {
        super(dslContext, entityManager);
        this.quizRepository = quizRepository;
    }

    @Override
    public List<ExerciseDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ExerciseDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_EXERCISE != null) {
            condition = condition.and(exercise.ID_EXERCISE.eq(inputCondition.ID_EXERCISE));
        }

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(exercise.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<ExerciseDTO> exerciseDTOList = dslContext.select()
                .from(exercise).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(exercise), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ExerciseDTO exerciseDTO = new ExerciseDTO();

                    exerciseDTO.idExercise = entry.getKey().getIdExercise();
                    exerciseDTO.idLesson = entry.getKey().getIdLesson();
                    exerciseDTO.exerciseName = entry.getKey().getExerciseName();

                    InputCondition inputConditionIDLesson = new InputCondition();
                    inputConditionIDLesson.ID_EXERCISE = exerciseDTO.idExercise;
                    inputConditionIDLesson.ID_USER = inputCondition.ID_USER;
                    exerciseDTO.quizInfo = quizRepository.getListByInputCondition(inputConditionIDLesson);

                    return exerciseDTO;
                }).collect(Collectors.toList());

        return exerciseDTOList;
    }

    @Override
    public ExerciseDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();

        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_EXERCISE = id;

        if (inputCondition.ID_EXERCISE != null) {
            condition = condition.and(exercise.ID_EXERCISE.eq(inputCondition.ID_EXERCISE));
        }

        List<ExerciseDTO> exerciseDTOList = dslContext.select()
                .from(exercise).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(exercise), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    ExerciseDTO exerciseDTO = new ExerciseDTO();

                    exerciseDTO.idExercise = entry.getKey().getIdExercise();
                    exerciseDTO.idLesson = entry.getKey().getIdLesson();
                    exerciseDTO.exerciseName = entry.getKey().getExerciseName();

                    InputCondition inputConditionIDLesson = new InputCondition();
                    inputConditionIDLesson.ID_EXERCISE = exerciseDTO.idExercise;
                    inputConditionIDLesson.ID_USER = inputCondition.ID_USER;
                    exerciseDTO.quizInfo = quizRepository.getListByInputCondition(inputConditionIDLesson);

                    return exerciseDTO;
                }).collect(Collectors.toList());

        return exerciseDTOList != null && !exerciseDTOList.isEmpty() ? exerciseDTOList.get(0) : null;
    }

    @Override
    public ExerciseDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<ExerciseDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public List<ExerciseDTO> saveListAndReturn(@NotNull List<ExerciseDTO> input) {
        return List.of();
    }

    @Override
    public ExerciseDTO saveAndReturn(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<ExerciseDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public List<ExerciseDTO> mergeListAndReturn(@NotNull List<ExerciseDTO> input) {
        return List.of();
    }

    @Override
    public ExerciseDTO mergeAndReturn(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<ExerciseDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull ExerciseDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<ExerciseDTO> input) {
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
