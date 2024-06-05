package cfm.onthi.repositories;

import cfm.onthi.dtos.MessageDTO;
import cfm.onthi.dtos.ProvinceDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtGroup;
import cfm.onthi.entities.tables.OtMessage;
import cfm.onthi.entities.tables.OtMessageRecipient;
import cfm.onthi.entities.tables.OtUser;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends BaseRepository<MessageDTO> {
    List<MessageDTO> getMessagesByGroupId(Long groupId);
    Long getMaxId();
}
@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class MessageRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<MessageDTO>, MessageRepository {
    OtMessage message = OtMessage.OT_MESSAGE.as("OtMessage");
    OtMessageRecipient messageRecipient = OtMessageRecipient.OT_MESSAGE_RECIPIENT.as("OtMessageRecipient");
    OtGroup group = OtGroup.OT_GROUP.as("g");
    OtUser user = OtUser.OT_USER.as("u");

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
    public List<MessageDTO> getMessagesByGroupId(Long groupId) {
        try {
            return dslContext.select(
                            message.ID_MESSAGE.as("idMessage"),
                            message.ID_CREATOR.as("idCreator"),
                            message.MESSAGE_BODY.as("messageBody"),
                            user.USERNAME.as("username"),
                            user.AVATAR.as("avatar")
                    )
                    .from(message)
                    .join(messageRecipient).on(message.ID_MESSAGE.eq(messageRecipient.ID_MESSAGE))
                    .join(group).on(messageRecipient.ID_RECIPIENT_GROUP.eq(group.ID_GROUP))
                    .join(user).on(message.ID_CREATOR.eq(user.ID_USER))
                    .where(group.ID_GROUP.eq(groupId))
                    .fetchInto(MessageDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean saveList(@NotNull List<MessageDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull MessageDTO item) {
        try {
            dslContext.insertInto(message)
                    .set(message.ID_CREATOR, item.idCreator)
                    .set(message.MESSAGE_BODY, item.messageBody)
                    .set(message.CREATED_DATE, LocalDateTime.now())
                    .set(message.LAST_MODIFIED_BY, item.idCreator.toString())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    @Override
    public Long getMaxId() {
        try {
            return dslContext.select(message.ID_MESSAGE.max())
                    .from(message)
                    .fetchOneInto(Long.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}