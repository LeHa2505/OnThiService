package cfm.onthi.repositories;

import cfm.onthi.dtos.NoteDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoteRepository extends BaseRepository<NoteDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class NoteRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<NoteDTO>, NoteRepository {

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
        return List.of();
    }

    @Override
    public NoteDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public NoteDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<NoteDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull NoteDTO item) {
        return null;
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
        return null;
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
