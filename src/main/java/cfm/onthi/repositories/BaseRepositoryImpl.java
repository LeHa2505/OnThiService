package cfm.onthi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Result;
import org.jooq.TableRecord;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public abstract class BaseRepositoryImpl {
    public static Query nativeQueryString = null;
    protected final DSLContext dslContext;
    @PersistenceContext
    protected final EntityManager entityManager;
    public ModelMapper modelMapper = new ModelMapper();
    public ModelMapper modelMapperLoose = new ModelMapper();

    public BaseRepositoryImpl(DSLContext dslContext, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.dslContext = dslContext;

        modelMapperLoose.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public <T, R extends TableRecord<R>> List<T> processResult(Result<R> result, Class<T> targetClass) {
        List<T> resultList = result.map(record -> modelMapper.map(record.into(targetClass), targetClass));

        resultList.removeAll(Collections.singleton(null));

        return resultList;
    }

    public <T, R extends TableRecord<R>> List<T> processResult(List<R> result, Class<T> targetClass) {
        List<T> resultList = result.stream()
                .map(record -> modelMapper.map(record.into(targetClass), targetClass))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        resultList.removeAll(Collections.singleton(null));

        return resultList;
    }

    public Boolean nativeQueryNotReturnRecord(Query query) {
        jakarta.persistence.Query result = entityManager.createNativeQuery(query.getSQL());

        List<Object> values = query.getBindValues();

        IntStream.range(0, values.size()).forEach(i -> result.setParameter(i + 1, values.get(i)));

        Integer codeCommit = result.executeUpdate();

        return codeCommit != null && codeCommit > 0 ? true : false;
    }

}
