package cfm.onthi.repositories;

import cfm.onthi.dtos.SchoolDTO;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtSchool;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface SchoolRepository extends BaseRepository<SchoolDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class SchoolRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<SchoolDTO>, SchoolRepository {
    private final ProvinceRepository provinceRepository;

    OtSchool otSchool = OtSchool.OT_SCHOOL.as("otSchool");

    public SchoolRepositoryImpl(DSLContext dslContext,
                                EntityManager entityManager,
                                ProvinceRepository provinceRepository) {
        super(dslContext, entityManager);
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<SchoolDTO> getAll() {
        return List.of();
    }

    @Override
    public List<SchoolDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        return List.of();
    }

    @Override
    public SchoolDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();
        condition = condition.and(otSchool.ID_SCHOOL.eq(id));

        List<SchoolDTO> schoolDTOS = dslContext.select()
                .from(otSchool).where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(otSchool)))
                .entrySet().stream()
                .map(entry -> {
                    SchoolDTO schoolDTO = new SchoolDTO();

                    schoolDTO.idSchool = entry.getKey().getIdSchool();
                    schoolDTO.schoolName = entry.getKey().getSchoolName();
                    schoolDTO.typeSchool = entry.getKey().getTypeSchool();
                    schoolDTO.idProvince = entry.getKey().getIdProvince();

                    //Province info
                    schoolDTO.provinceInfo = provinceRepository.getByID(schoolDTO.idProvince);

                    return schoolDTO;
                }).collect(Collectors.toList());
        return schoolDTOS != null && !schoolDTOS.isEmpty() ? schoolDTOS.get(0) : null;
    }

    @Override
    public SchoolDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_SCHOOL != null) {
            condition = condition.and(otSchool.ID_SCHOOL.eq(inputCondition.ID_SCHOOL));
        }

        List<SchoolDTO> schoolDTOS = dslContext.select()
                .from(otSchool).where(condition)
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(otSchool)))
                .entrySet().stream()
                .map(entry -> {
                    SchoolDTO schoolDTO = new SchoolDTO();

                    schoolDTO.idSchool = entry.getKey().getIdSchool();
                    schoolDTO.schoolName = entry.getKey().getSchoolName();
                    schoolDTO.typeSchool = entry.getKey().getTypeSchool();

                    //Province info
                    schoolDTO.provinceInfo = provinceRepository.getByID(schoolDTO.idProvince);

                    return schoolDTO;
                }).collect(Collectors.toList());;
        return schoolDTOS != null && !schoolDTOS.isEmpty() ? schoolDTOS.get(0) : null;
    }

    @Override
    public Boolean saveList(@NotNull List<SchoolDTO> input) {
        return null;
    }

    @Override
    public Boolean save(@NotNull SchoolDTO item) {
        return null;
    }

    @Override
    public List<SchoolDTO> saveListAndReturn(@NotNull List<SchoolDTO> input) {
        return List.of();
    }

    @Override
    public SchoolDTO saveAndReturn(@NotNull SchoolDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<SchoolDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull SchoolDTO item) {
        return null;
    }

    @Override
    public List<SchoolDTO> mergeListAndReturn(@NotNull List<SchoolDTO> input) {
        return List.of();
    }

    @Override
    public SchoolDTO mergeAndReturn(@NotNull SchoolDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull SchoolDTO item) {
        try {
            int result = dslContext.update(otSchool)
                    .set(otSchool.SCHOOL_NAME, item.schoolName)
                    .set(otSchool.TYPE_SCHOOL, item.typeSchool)
                    .set(otSchool.ID_PROVINCE, item.idProvince)
                    .where(otSchool.ID_SCHOOL.eq(item.getIdSchool()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(@NotNull List<SchoolDTO> input) {
        return null;
    }

    @Override
    public Boolean delete(@NotNull SchoolDTO item) {
        return null;
    }

    @Override
    public Boolean deleteList(@NotNull List<SchoolDTO> input) {
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
