package cfm.onthi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


public interface OnThiService {

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

}
