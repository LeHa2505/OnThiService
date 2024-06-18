package cfm.onthi.repositories;

import cfm.onthi.constant.ActiveStatus;
import cfm.onthi.constant.Gender;
import cfm.onthi.constant.Role;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.UserInfoDTO;
import cfm.onthi.entities.tables.OtUser;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface UserRepository extends BaseRepository<UserInfoDTO> {
    Boolean existsByEmail(String email);
    UserInfoDTO getByEmail(@NotNull InputCondition inputCondition);
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UserRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<UserInfoDTO>, UserRepository {
    private final SchoolRepository schoolRepository;
    OtUser user = OtUser.OT_USER.as("OtUser");
    Gender gender;
    Role role;
    ActiveStatus activeStatus;

    public UserRepositoryImpl(
            @Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
            @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
            @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider,
            SchoolRepository schoolRepository
    ) {
        super(dslContext, entityManager);
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Boolean existsByEmail(String email) {
        if (user != null) {
            return dslContext.selectCount()
                    .from(user)
                    .where(user.EMAIL.eq(email))
                    .fetchOne()
                    .value1() > 0;
        } else {
            return false;
        }
    }

    @Override
    public List<UserInfoDTO> getAll() {
        List<UserInfoDTO> userInfoDTOList = dslContext.select()
                .from(user)
                .fetch()
                .stream()
                .map(record -> {
                    UserInfoDTO userInfoDTO = new UserInfoDTO();

                    userInfoDTO.idUser = record.get(user.ID_USER);
                    userInfoDTO.idSchool = record.get(user.ID_SCHOOL);
                    userInfoDTO.typeUser = record.get(user.TYPE_USER);
                    userInfoDTO.username = record.get(user.USERNAME);
                    userInfoDTO.email = record.get(user.EMAIL);
                    userInfoDTO.phone = record.get(user.PHONE);
                    userInfoDTO.grade = record.get(user.GRADE);
                    userInfoDTO.gender = record.get(user.GENDER);
                    userInfoDTO.bod = record.get(user.BOD);
                    userInfoDTO.address = record.get(user.ADDRESS);
                    userInfoDTO.description = record.get(user.DESCRIPTION);
                    userInfoDTO.facebook = record.get(user.FACEBOOK);
                    userInfoDTO.instagram = record.get(user.INSTAGRAM);
                    userInfoDTO.avatar = record.get(user.AVATAR);
                    userInfoDTO.active = record.get(user.ACTIVE);

                    // Assuming schoolRepository.getByID() is a method that fetches school information by ID
                    if (userInfoDTO.idSchool != null) {
                        userInfoDTO.schoolInfo = schoolRepository.getByID(userInfoDTO.idSchool);
                    }
                    return userInfoDTO;
                })
                .collect(Collectors.toList());

        return userInfoDTOList;
    }

    @Override
    public List<UserInfoDTO> getListByInputCondition(@NotNull InputCondition inputCondition)  {
        Condition condition = trueCondition();

        if (inputCondition.EMAIL != null && !inputCondition.EMAIL.isBlank() && !inputCondition.EMAIL.isEmpty()) {
            condition = condition.and(user.EMAIL.eq(inputCondition.EMAIL));
        }

        if (inputCondition.USERNAME != null && !inputCondition.USERNAME.isBlank() && !inputCondition.USERNAME.isEmpty()) {
            condition = condition.and(user.USERNAME.likeIgnoreCase("%" + inputCondition.USERNAME + "%"));
        }

        if (inputCondition.ID_SCHOOL != null) {
            condition = condition.and(user.ID_SCHOOL.eq(inputCondition.ID_SCHOOL));
        }

        List<UserInfoDTO> userInfoDTOList = dslContext.select()
                .from(user).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(user), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    UserInfoDTO userInfoDTO = new UserInfoDTO();

                    userInfoDTO.idUser = entry.getKey().getIdUser();
                    userInfoDTO.idSchool = entry.getKey().getIdSchool();
                    userInfoDTO.username = entry.getKey().getUsername();
                    userInfoDTO.email = entry.getKey().getEmail();
                    userInfoDTO.phone = entry.getKey().getPhone();
                    userInfoDTO.grade = entry.getKey().getGrade();
                    userInfoDTO.gender = entry.getKey().getGender();
                    userInfoDTO.bod =  entry.getKey().getBod();
                    userInfoDTO.address = entry.getKey().getAddress();
                    userInfoDTO.description = entry.getKey().getDescription();
                    userInfoDTO.facebook = entry.getKey().getFacebook();
                    userInfoDTO.instagram = entry.getKey().getInstagram();
                    userInfoDTO.avatar = entry.getKey().getAvatar();
                    userInfoDTO.active = entry.getKey().getActive();

                    if (userInfoDTO.idSchool != null) {
                        userInfoDTO.schoolInfo = schoolRepository.getByID(userInfoDTO.idSchool);
                    }

                    return userInfoDTO;
                }).collect(Collectors.toList());

        return userInfoDTOList;
    }

    @Override
    public UserInfoDTO getByID(@NotNull Long id) {
        Condition condition = trueCondition();

        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_USER = id;

        if (inputCondition.ID_USER != null) {
            condition = condition.and(user.ID_USER.eq(inputCondition.ID_USER));
        }

        List<UserInfoDTO> userInfoDTOList = dslContext.select()
                .from(user).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(user), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    UserInfoDTO userInfoDTO = new UserInfoDTO();

                    userInfoDTO.idUser = entry.getKey().getIdUser();
                    userInfoDTO.idSchool = entry.getKey().getIdSchool();
                    userInfoDTO.username = entry.getKey().getUsername();
                    userInfoDTO.email = entry.getKey().getEmail();
                    userInfoDTO.phone = entry.getKey().getPhone();
                    userInfoDTO.grade = entry.getKey().getGrade();
                    userInfoDTO.gender = entry.getKey().getGender();
                    userInfoDTO.bod =  entry.getKey().getBod();
                    userInfoDTO.address = entry.getKey().getAddress();
                    userInfoDTO.description = entry.getKey().getDescription();
                    userInfoDTO.facebook = entry.getKey().getFacebook();
                    userInfoDTO.instagram = entry.getKey().getInstagram();
                    userInfoDTO.avatar = entry.getKey().getAvatar();
                    userInfoDTO.active = entry.getKey().getActive();

                    if (userInfoDTO.idSchool != null) {
                        userInfoDTO.schoolInfo = schoolRepository.getByID(userInfoDTO.idSchool);
                    }

                    return userInfoDTO;
                }).collect(Collectors.toList());

        return userInfoDTOList != null && !userInfoDTOList.isEmpty() ? userInfoDTOList.get(0) : null;
    }

    @Override
    public UserInfoDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();
        condition = condition.and(user.EMAIL.in(inputCondition.EMAIL));
        List<UserInfoDTO> result = dslContext.select()
                .from(user)
                .where(condition)
                .fetchInto(UserInfoDTO.class);
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public UserInfoDTO getByEmail(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();
        condition = condition.and(user.EMAIL.in(inputCondition.EMAIL));
        List<UserInfoDTO> result = dslContext.select()
                .from(user)
                .where(condition)
                .fetchInto(UserInfoDTO.class);

        for (UserInfoDTO userInfoDTO : result) {
            userInfoDTO.setSchoolInfo(schoolRepository.getByID(userInfoDTO.idSchool));
            userInfoDTO.setPassword(null);
        }

        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    @Transactional
    @Override
    public Boolean saveList(@NotNull List<UserInfoDTO> input) {
        try {
            for (UserInfoDTO item : input) {
                Boolean result = save(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean save(@NotNull UserInfoDTO item) {
        try {
            dslContext.insertInto(user)
                    .set(user.TYPE_USER, item.getTypeUser())
                    .set(user.USERNAME, item.getUsername())
                    .set(user.PASSWORD, item.getPassword())
                    .set(user.EMAIL, item.getEmail())
                    .set(user.PHONE, item.getPhone())
                    .set(user.GRADE, item.getGrade())
                    .set(user.BOD, item.getBod())
                    .set(user.ADDRESS, item.getAddress())
                    .set(user.DESCRIPTION, item.getDescription())
                    .set(user.FACEBOOK, item.getFacebook())
                    .set(user.INSTAGRAM, item.getInstagram())
                    .set(user.AVATAR, item.getAvatar())
                    .set(user.ACTIVE, activeStatus.ACTIVE.getCode())
                    .set(user.CREATED_DATE, LocalDateTime.now())
                    .set(user.LAST_MODIFIED_BY, item.getUsername())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserInfoDTO> saveListAndReturn(@NotNull List<UserInfoDTO> input) {
        return List.of();
    }

    @Override
    public UserInfoDTO saveAndReturn(@NotNull UserInfoDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<UserInfoDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull UserInfoDTO item) {
        return null;
    }

    @Override
    public List<UserInfoDTO> mergeListAndReturn(@NotNull List<UserInfoDTO> input) {
        return List.of();
    }

    @Override
    public UserInfoDTO mergeAndReturn(@NotNull UserInfoDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull UserInfoDTO item) {
        try {
            int result = dslContext.update(user)
                    .set(user.TYPE_USER, item.getTypeUser())
                    .set(user.USERNAME, item.getUsername())
                    .set(user.EMAIL, item.getEmail())
                    .set(user.PHONE, item.getPhone())
                    .set(user.GRADE, item.getGrade())
                    .set(user.BOD, item.getBod())
                    .set(user.ADDRESS, item.getAddress())
                    .set(user.DESCRIPTION, item.getDescription())
                    .set(user.FACEBOOK, item.getFacebook())
                    .set(user.INSTAGRAM, item.getInstagram())
                    .set(user.AVATAR, item.getAvatar())
                    .set(user.ACTIVE, item.getActive())
                    .set(user.LAST_MODIFIED_BY, item.getUsername())
                    .set(user.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(user.ID_USER.eq(item.getIdUser()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateList(@NotNull List<UserInfoDTO> input) {
        try {
            for (UserInfoDTO item : input) {
                Boolean result = update(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(@NotNull UserInfoDTO item) {
        try {
            int result = dslContext.deleteFrom(user)
                    .where(user.ID_USER.eq(item.getIdUser()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<UserInfoDTO> input) {
        try {
            for (UserInfoDTO item : input) {
                Boolean result = delete(item);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteByID(@NotNull Long id) {
        try {
            int result = dslContext.deleteFrom(user)
                    .where(user.ID_USER.eq(id))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteListByID(@NotNull List<Long> input) {
        try {
            for (Long id : input) {
                Boolean result = deleteByID(id);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
