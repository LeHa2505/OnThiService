package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.NoteDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtNote;
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

public interface NoteRepository extends BaseRepository<NoteDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class NoteRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<NoteDTO>, NoteRepository {
    OtNote note = OtNote.OT_NOTE.as("OtNote");

    public NoteRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                              @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                              @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<NoteDTO> getAll() {
        return List.of();
    }

    @Override
    public List<NoteDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_NOTE != null) {
            condition = condition.and(note.ID_NOTE.eq(inputCondition.ID_NOTE));
        }

        if (inputCondition.ID_USER != null) {
            condition = condition.and(note.ID_USER.eq(inputCondition.ID_USER));
        }

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(note.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<NoteDTO> noteDTOList = dslContext.select()
                .from(note).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(note), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    NoteDTO noteDTO = new NoteDTO();

                    noteDTO.idNote = entry.getKey().getIdNote();
                    noteDTO.idUser = entry.getKey().getIdUser();
                    noteDTO.idLesson = entry.getKey().getIdLesson();
                    noteDTO.content = entry.getKey().getContent();
                    noteDTO.noteTime = entry.getKey().getNoteTime();

                    return noteDTO;
                }).collect(Collectors.toList());
        return noteDTOList;
    }

    @Override
    public NoteDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public NoteDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_NOTE != null) {
            condition = condition.and(note.ID_NOTE.eq(inputCondition.ID_NOTE));
        }

        if (inputCondition.ID_USER != null) {
            condition = condition.and(note.ID_USER.eq(inputCondition.ID_USER));
        }

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(note.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<NoteDTO> noteDTOList = dslContext.select()
                .from(note).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(note), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    NoteDTO noteDTO = new NoteDTO();

                    noteDTO.idNote = entry.getKey().getIdNote();
                    noteDTO.idUser = entry.getKey().getIdUser();
                    noteDTO.idLesson = entry.getKey().getIdLesson();
                    noteDTO.content = entry.getKey().getContent();
                    noteDTO.noteTime = entry.getKey().getNoteTime();

                    return noteDTO;
                }).collect(Collectors.toList());

        return noteDTOList != null && !noteDTOList.isEmpty() ? noteDTOList.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<NoteDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull NoteDTO item) {
        try {
            dslContext.insertInto(note)
                    .set(note.ID_LESSON, item.idLesson)
                    .set(note.NOTE_TIME, item.noteTime)
                    .set(note.ID_USER, item.idUser)
                    .set(note.CONTENT, item.content)
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NoteDTO> saveListAndReturn(@NotNull List<NoteDTO> input) {
        return List.of();
    }

    @Override
    public NoteDTO saveAndReturn(@NotNull NoteDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<NoteDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull NoteDTO item) {
        return null;
    }

    @Override
    public List<NoteDTO> mergeListAndReturn(@NotNull List<NoteDTO> input) {
        return List.of();
    }

    @Override
    public NoteDTO mergeAndReturn(@NotNull NoteDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull NoteDTO item) {
        try {
            int result = dslContext.update(note)
                    .set(note.ID_LESSON, item.idLesson)
                    .set(note.NOTE_TIME, item.noteTime)
                    .set(note.ID_USER, item.idUser)
                    .set(note.CONTENT, item.content)
                    .where(note.ID_NOTE.eq(item.getIdNote()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<NoteDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull NoteDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<NoteDTO> input) {
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
