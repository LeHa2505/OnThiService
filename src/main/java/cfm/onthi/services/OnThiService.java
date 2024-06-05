package cfm.onthi.services;

import cfm.onthi.dtos.RegisterDTO;
import cfm.onthi.dtos.base.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


public interface OnThiService {
    ResponseDTO getUerInfoService(RegisterDTO registerDTO);

}

@Lazy
@Service
class OnThiServiceImpl extends BaseService implements OnThiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TransactionTemplate transactionTemplate;

    public OnThiServiceImpl(
            TransactionTemplate transactionTemplate
    ) {
        super();
        this.transactionTemplate = transactionTemplate;
    }

    public ResponseDTO getUerInfoService(RegisterDTO registerDTO) {

        return new ResponseDTO(true, "", null);
    }
}
