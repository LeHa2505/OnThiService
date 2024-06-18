package cfm.onthi.repositories;

import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.QuizDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtQuiz;
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

public interface QuizRepository extends BaseRepository<QuizDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class QuizRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<QuizDTO>, QuizRepository {
    OtQuiz quiz = OtQuiz.OT_QUIZ.as("OtQuiz");
    QuizUserRepository quizUserRepository;

    public QuizRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                              @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                              @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
                              QuizUserRepository quizUserRepository
    ) {
        super(dslContext, entityManager);
        this.quizUserRepository = quizUserRepository;
    }

    @Override
    public List<QuizDTO> getAll() {
        return List.of();
    }

    @Override
    public List<QuizDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_EXERCISE != null) {
            condition = condition.and(quiz.ID_EXERCISE.eq(inputCondition.ID_EXERCISE));
        }

        List<QuizDTO> quizDTOList = dslContext.select()
                .from(quiz).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(quiz), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    QuizDTO quizDTO = new QuizDTO();

                    quizDTO.idQuiz = entry.getKey().getIdQuiz();
                    quizDTO.idExercise = entry.getKey().getIdExercise();
                    quizDTO.contentQuiz = entry.getKey().getContentQuiz();
                    quizDTO.quizStatus = entry.getKey().getQuizStatus();
                    quizDTO.quizType = entry.getKey().getQuizType();
                    quizDTO.answer = entry.getKey().getAnswer();
                    quizDTO.description = entry.getKey().getDescription();
                    quizDTO.order = entry.getKey().getOrder();
                    quizDTO.options = entry.getKey().getOptions();

                    InputCondition inputConditionIDQuiz = new InputCondition();
                    inputConditionIDQuiz.ID_QUIZ = entry.getKey().getIdQuiz();
                    inputConditionIDQuiz.ID_USER = inputCondition.ID_USER;
                    quizDTO.quizUserInfo = quizUserRepository.getListByInputCondition(inputConditionIDQuiz);

                    return quizDTO;
                }).collect(Collectors.toList());

        return quizDTOList;
    }

    @Override
    public QuizDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public QuizDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<QuizDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public List<QuizDTO> saveListAndReturn(@NotNull List<QuizDTO> input) {
        return List.of();
    }

    @Override
    public QuizDTO saveAndReturn(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<QuizDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public List<QuizDTO> mergeListAndReturn(@NotNull List<QuizDTO> input) {
        return List.of();
    }

    @Override
    public QuizDTO mergeAndReturn(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<QuizDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull QuizDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<QuizDTO> input) {
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
