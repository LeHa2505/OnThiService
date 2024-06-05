package cfm.onthi.repositories;

import cfm.onthi.dtos.MessageRecipientDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
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

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRecipientRepository extends BaseRepository<MessageRecipientDTO> {
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class MessageRecipientRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<MessageRecipientDTO>, MessageRecipientRepository {
    OtMessageRecipient messageRecipient = OtMessageRecipient.OT_MESSAGE_RECIPIENT.as("OtMessageRecipient");

    public MessageRecipientRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                          @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                          @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<MessageRecipientDTO> getAll() {
        return List.of();
    }

    @Override
    public List<MessageRecipientDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public MessageRecipientDTO getByID(@NotNull Long id) {
        return null;
    }

    @Override
    public MessageRecipientDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    public Boolean saveList(@NotNull List<MessageRecipientDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull MessageRecipientDTO item) {
        try {
            dslContext.insertInto(messageRecipient)
                    .set(messageRecipient.ID_MESSAGE, item.idMessage)
                    .set(messageRecipient.ID_RECIPIENT_GROUP, item.idRecipientGroup)
                    .set(messageRecipient.CREATED_DATE, LocalDateTime.now())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MessageRecipientDTO> saveListAndReturn(@NotNull List<MessageRecipientDTO> input) {
        return List.of();
    }

    @Override
    public MessageRecipientDTO saveAndReturn(@NotNull MessageRecipientDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<MessageRecipientDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull MessageRecipientDTO item) {
        return null;
    }

    @Override
    public List<MessageRecipientDTO> mergeListAndReturn(@NotNull List<MessageRecipientDTO> input) {
        return List.of();
    }

    @Override
    public MessageRecipientDTO mergeAndReturn(@NotNull MessageRecipientDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull MessageRecipientDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<MessageRecipientDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull MessageRecipientDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<MessageRecipientDTO> input) {
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
