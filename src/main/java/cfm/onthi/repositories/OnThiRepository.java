package cfm.onthi.repositories;

import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

public interface OnThiRepository {

}

@Repository
class OnThiRepositoryImpl extends BaseRepositoryImpl implements OnThiRepository {


    public OnThiRepositoryImpl(
            @Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
            @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
            @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider
    ) {
        super(dslContext, entityManager);
    }

}

