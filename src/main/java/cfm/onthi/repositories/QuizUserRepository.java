package cfm.onthi.repositories;

import cfm.onthi.dtos.NoteDTO;
import cfm.onthi.dtos.QuizUserDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtQuizUser;
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

public interface QuizUserRepository extends BaseRepository<QuizUserDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class QuizUserRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<QuizUserDTO>, QuizUserRepository {
    OtQuizUser quizUser = OtQuizUser.OT_QUIZ_USER.as("OtQuizUser");

    public QuizUserRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                  @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                  @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<QuizUserDTO> getAll() {
        return List.of();
    }

    @Override
    public List<QuizUserDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_QUIZ != null) {
            condition = condition.and(quizUser.ID_QUIZ.eq(inputCondition.ID_QUIZ));
        }

        if (inputCondition.ID_USER != null) {
            condition = condition.and(quizUser.ID_USER.eq(inputCondition.ID_USER));
        }

        List<QuizUserDTO> quizUserDTOList = dslContext.select()
                .from(quizUser).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(quizUser), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    QuizUserDTO quizUserDTO = new QuizUserDTO();

                    quizUserDTO.idQuiz = entry.getKey().getIdQuiz();
                    quizUserDTO.idUser = entry.getKey().getIdUser();
                    quizUserDTO.userAnswer = entry.getKey().getUserAnswer();

                    return quizUserDTO;
                }).collect(Collectors.toList());
        return quizUserDTOList;
    }

    @Override
    public QuizUserDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public QuizUserDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<QuizUserDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull QuizUserDTO item) {
        try {
            dslContext.insertInto(quizUser)
                    .set(quizUser.ID_QUIZ, item.idQuiz)
                    .set(quizUser.ID_USER, item.idUser)
                    .set(quizUser.USER_ANSWER, item.userAnswer)
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<QuizUserDTO> saveListAndReturn(@NotNull List<QuizUserDTO> input) {
        return List.of();
    }

    @Override
    public QuizUserDTO saveAndReturn(@NotNull QuizUserDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<QuizUserDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull QuizUserDTO item) {
        return null;
    }

    @Override
    public List<QuizUserDTO> mergeListAndReturn(@NotNull List<QuizUserDTO> input) {
        return List.of();
    }

    @Override
    public QuizUserDTO mergeAndReturn(@NotNull QuizUserDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull QuizUserDTO item) {
        try {
            int result = dslContext.update(quizUser)
                    .set(quizUser.ID_QUIZ, item.idQuiz)
                    .set(quizUser.ID_USER, item.idUser)
                    .set(quizUser.USER_ANSWER, item.userAnswer)
                    .where(quizUser.ID_QUIZ_USER.eq(item.getIdQuizUser()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<QuizUserDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull QuizUserDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<QuizUserDTO> input) {
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
