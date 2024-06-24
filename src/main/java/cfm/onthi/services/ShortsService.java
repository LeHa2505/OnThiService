package cfm.onthi.services;

import cfm.onthi.dtos.ShortDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.ShortRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShortsService {
    ResponseDTO getShortsByTeacherId(Long id);
    ResponseDTO addShort(ShortDTO shortDTO);
    ResponseDTO deleteShort(Long id);
    ResponseDTO getShortsByUserId(Long id);
    ResponseDTO getShortById(Long id);
}

@Service
class ShortsServiceImpl extends BaseService implements ShortsService {
    private ShortRepository shortRepository;

    public ShortsServiceImpl(ShortRepository shortRepository) {
        super();
        this.shortRepository = shortRepository;
    }

    @Override
    public ResponseDTO getShortsByTeacherId(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_USER = id;
        List<ShortDTO> shortDTOList = shortRepository.getListByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", shortDTOList);
    }

    @Override
    public ResponseDTO addShort(ShortDTO shortDTO) {
        if (shortRepository.save(shortDTO)) {
            return new ResponseDTO(true, "Tạo video ngắn thành công!", null);
        } else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO deleteShort(Long id) {
        ShortDTO shortDTO = new ShortDTO();
        shortDTO.id_short = id;
        if (shortRepository.delete(shortDTO)) {
            return new ResponseDTO(true, "Xóa video ngắn thành công!", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO getShortsByUserId(Long id) {
        return new ResponseDTO(true, "OK!", shortRepository.getShortsByUserId(id));
    }

    @Override
    public ResponseDTO getShortById(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_SHORT = id;
        return new ResponseDTO(true, "OK!", shortRepository.getByInputCondition(inputCondition));
    }
}
