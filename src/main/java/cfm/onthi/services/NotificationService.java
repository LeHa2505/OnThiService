package cfm.onthi.services;

import cfm.onthi.dtos.NotificationDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public interface NotificationService {
    ResponseDTO createNotification(NotificationDTO notificationDTO);
    ResponseDTO getListNotification(Long idUser);
    ResponseDTO updateNotification(NotificationDTO notificationDTO);
}

@Service
class NotificationServiceImpl extends BaseService implements NotificationService {
    NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            TransactionTemplate transactionTemplate,
            NotificationRepository notificationRepository
    ) {
        super();
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ResponseDTO createNotification(NotificationDTO notificationDTO) {
        if (notificationRepository.save(notificationDTO)) {
            return new ResponseDTO(true, "Đã gửi thông báo tới giáo viên!", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO getListNotification(Long idUser) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_USER = idUser;
        List<NotificationDTO> notificationDTOList = notificationRepository.getListByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", notificationDTOList);
    }

    @Override
    public ResponseDTO updateNotification(NotificationDTO notificationDTO) {
        if (notificationRepository.update(notificationDTO)) {
            return new ResponseDTO(true, "Đã đánh dấu đọc thông báo!", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }
}
