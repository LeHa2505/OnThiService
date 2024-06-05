package cfm.onthi.services;

import cfm.onthi.dtos.GroupDTO;
import cfm.onthi.dtos.MessageDTO;
import cfm.onthi.dtos.MessageRecipientDTO;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.GroupRepository;
import cfm.onthi.repositories.MessageRecipientRepository;
import cfm.onthi.repositories.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public interface ChatService {
    ResponseDTO getMessagesByGroupId(Long groupId);
    ResponseDTO getGroupsByUserId(Long userId);
    ResponseDTO saveMessage(MessageDTO messageDTO);
}

@Service
class ChatServiceImpl extends BaseService implements ChatService {
    private MessageRepository messageRepository;
    private GroupRepository groupRepository;
    private MessageRecipientRepository messageRecipientRepository;

    public ChatServiceImpl(
            TransactionTemplate transactionTemplate,
            MessageRepository messageRepository,
            GroupRepository groupRepository,
            MessageRecipientRepository messageRecipientRepository
    ) {
        super();
        this.messageRepository = messageRepository;
        this.groupRepository = groupRepository;
        this.messageRecipientRepository = messageRecipientRepository;
    }

    @Override
    public ResponseDTO getMessagesByGroupId(Long groupId) {
        List<MessageDTO> messageDTOS = messageRepository.getMessagesByGroupId(groupId);
        return new ResponseDTO(true, "Lấy dữ liệu thành công!", messageDTOS);
    }

    @Override
    public ResponseDTO getGroupsByUserId(Long userId) {
        List<GroupDTO> groupDTOList = groupRepository.getGroupsByUserId(userId);
        return new ResponseDTO(true, "Lấy dữ liệu thành công!", groupDTOList);
    }

    @Override
    public ResponseDTO saveMessage(MessageDTO messageDTO) {
        if (messageRepository.save(messageDTO)) {
            Long insertedMessageId = messageRepository.getMaxId();
            MessageRecipientDTO messageRecipientDTO = new MessageRecipientDTO();
            messageRecipientDTO.setIdMessage(insertedMessageId);
            messageRecipientDTO.setIdRecipientGroup(messageDTO.idGroup);
            if (messageRecipientRepository.save(messageRecipientDTO)) {
                return new ResponseDTO(true, "Lưu tin nhắn thành công!", null);
            }
            else return new ResponseDTO(false, "Lưu tin nhắn không thành công!", null);

        }
        else {
            return new ResponseDTO(false, "Lưu tin nhắn không thành công!", null);
        }
    }

}