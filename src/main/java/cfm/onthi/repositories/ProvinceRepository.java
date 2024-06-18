package cfm.onthi.repositories;

import cfm.onthi.dtos.ProvinceDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtProvince;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface ProvinceRepository extends BaseRepository<ProvinceDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ProvinceRepositoryImpl extends BaseRepositoryImpl implements ProvinceRepository {
    OtProvince otProvince = OtProvince.OT_PROVINCE.as("otProvince");

    public ProvinceRepositoryImpl(DSLContext dslContext, EntityManager entityManager) {
        super(dslContext, entityManager);
    }

    @Override
    public List<ProvinceDTO> getAll() {
        return List.of();
    }

    @Override
    public List<ProvinceDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();
        condition = condition.and(otProvince.ID_PROVINCE.like("%" + inputCondition.ID_PROVINCE + "%")); // Sử dụng LIKE với một mẫu đơn giản

        List<ProvinceDTO> result = dslContext.select()
                .from(otProvince)
                .where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(otProvince)))
                .entrySet().stream()
                .map(entry -> {
                    ProvinceDTO provinceDTO = new ProvinceDTO();

                    provinceDTO.idProvince = entry.getKey().getIdProvince();
                    provinceDTO.provinceName = entry.getKey().getProvinceName();

                    return provinceDTO;
                }).collect(Collectors.toList());

        return result;
    }

    @Override
    public ProvinceDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();
        condition = condition.and(otProvince.ID_PROVINCE.like("%" + id + "%")); // Sử dụng LIKE với một mẫu đơn giản

        List<ProvinceDTO> result = dslContext.select()
                .from(otProvince)
                .where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(otProvince)))
                .entrySet().stream()
                .map(entry -> {
                    ProvinceDTO provinceDTO = new ProvinceDTO();

                    provinceDTO.idProvince = entry.getKey().getIdProvince();
                    provinceDTO.provinceName = entry.getKey().getProvinceName();

                    return provinceDTO;
                }).collect(Collectors.toList());

        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public ProvinceDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_PROVINCE != null) {
            condition = condition.and(otProvince.ID_PROVINCE.eq(inputCondition.ID_PROVINCE));
        }

        if (inputCondition.PROVINCE_NAME != null && !inputCondition.PROVINCE_NAME.isBlank() && !inputCondition.PROVINCE_NAME.isEmpty()) {
            condition = condition.and(otProvince.PROVINCE_NAME.like("%" + inputCondition.PROVINCE_NAME + "%")); // Sử dụng LIKE với một mẫu đơn giản
        }

        List<ProvinceDTO> result = dslContext.select()
                .from(otProvince)
                .where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(otProvince)))
                .entrySet().stream()
                .map(entry -> {
                    ProvinceDTO provinceDTO = new ProvinceDTO();

                    provinceDTO.idProvince = entry.getKey().getIdProvince();
                    provinceDTO.provinceName = entry.getKey().getProvinceName();

                    return provinceDTO;
                }).collect(Collectors.toList());

        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<ProvinceDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public List<ProvinceDTO> saveListAndReturn(@NotNull List<ProvinceDTO> input) {
        return List.of();
    }

    @Override
    public ProvinceDTO saveAndReturn(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<ProvinceDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public List<ProvinceDTO> mergeListAndReturn(@NotNull List<ProvinceDTO> input) {
        return List.of();
    }

    @Override
    public ProvinceDTO mergeAndReturn(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public Boolean updateList(@NotNull List<ProvinceDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull ProvinceDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<ProvinceDTO> input) {
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