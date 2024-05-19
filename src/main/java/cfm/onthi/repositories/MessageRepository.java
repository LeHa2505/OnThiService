package cfm.onthi.repositories;

import cfm.onthi.dtos.MessageDTO;
import cfm.onthi.dtos.ProvinceDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtMessage;
import cfm.onthi.entities.tables.OtMessageRecipient;
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

public interface MessageRepository extends BaseRepository<MessageDTO> {
}
@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class MessageRepositoryImpl extends BaseRepositoryImpl implements MessageRepository {
    OtMessage message = OtMessage.OT_MESSAGE.as("OtMessage");

    public MessageRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                 @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                 @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<MessageDTO> getAll() {
        return List.of();
    }

    @Override
    public List<MessageDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public MessageDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public MessageDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<MessageDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public List<MessageDTO> saveListAndReturn(@NotNull List<MessageDTO> input) {
        return List.of();
    }

    @Override
    public MessageDTO saveAndReturn(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<MessageDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public List<MessageDTO> mergeListAndReturn(@NotNull List<MessageDTO> input) {
        return List.of();
    }

    @Override
    public MessageDTO mergeAndReturn(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<MessageDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull MessageDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<MessageDTO> input) {
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