package cfm.onthi.repositories;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.DocumentDTO;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.entities.tables.OtDocument;
import cfm.onthi.utils.DefineProperties;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.trueCondition;

public interface DocumentRepository extends BaseRepository<DocumentDTO>{
}

@Lazy
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class DocumentRepositoryImpl extends BaseRepositoryImpl implements BaseRepository<DocumentDTO>, DocumentRepository {
    OtDocument document = OtDocument.OT_DOCUMENT.as("OtDocument");

    public DocumentRepositoryImpl(@Qualifier(DefineProperties.DSLContextOnThi) DSLContext dslContext,
                                  @Qualifier(DefineProperties.entityManagerOnThi) EntityManager entityManager,
                                  @Qualifier(DefineProperties.connectionProviderOnThi) DataSourceConnectionProvider connectionProvider) {
        super(dslContext, entityManager);
    }

    @Override
    public List<DocumentDTO> getAll() {
        return List.of();
    }

    @Override
    public List<DocumentDTO> getListByInputCondition(@NotNull InputCondition inputCondition) {
        Condition condition = trueCondition();

        if (inputCondition.ID_LESSON != null) {
            condition = condition.and(document.ID_LESSON.eq(inputCondition.ID_LESSON));
        }

        List<DocumentDTO> courseDTOList = dslContext.select()
                .from(document).where(condition).fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.into(document), LinkedHashMap::new, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    DocumentDTO documentDTO = new DocumentDTO();

                    documentDTO.idDocument = entry.getKey().getIdDocument();
                    documentDTO.idLesson = entry.getKey().getIdLesson();
                    documentDTO.documentName = entry.getKey().getDocumentName();;
                    documentDTO.documentLink = entry.getKey().getDocumentLink();
                    documentDTO.typeDocument = entry.getKey().getTypeDocument();
                    documentDTO.downloadStatus = entry.getKey().getDownloadStatus();
                    documentDTO.size = entry.getKey().getSize();

                    return documentDTO;
                }).collect(Collectors.toList());

        return courseDTOList;
    }

    @Override
    public DocumentDTO getByID(@NotNull Long id) {
        try {
            return dslContext.selectFrom(document)
                    .where(document.ID_LESSON.eq(id))
                    .fetchOneInto(DocumentDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DocumentDTO getByInputCondition(@NotNull InputCondition inputCondition) {
        return null;
    }

    @Override
    @Transactional
    public Boolean saveList(@NotNull List<DocumentDTO> input) {
        try {
            for (DocumentDTO item : input) {
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
    public Boolean save(@NotNull DocumentDTO item) {
        try {
            dslContext.insertInto(document)
                    .set(document.ID_LESSON, item.idLesson)
                    .set(document.DOCUMENT_LINK, item.documentLink)
                    .set(document.DOCUMENT_NAME, item.documentName)
                    .set(document.TYPE_DOCUMENT, item.typeDocument)
                    .set(document.SIZE, item.size)
                    .set(document.DOWNLOAD_STATUS, item.downloadStatus)
                    .set(document.CREATED_DATE, LocalDateTime.now())
                    .set(document.LAST_MODIFIED_BY, item.idLesson.toString())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DocumentDTO> saveListAndReturn(@NotNull List<DocumentDTO> input) {
        return List.of();
    }

    @Override
    public DocumentDTO saveAndReturn(@NotNull DocumentDTO item) {
        return null;
    }

    @Override
    public Boolean mergeList(@NotNull List<DocumentDTO> input) {
        return null;
    }

    @Override
    public Boolean merge(@NotNull DocumentDTO item) {
        return null;
    }

    @Override
    public List<DocumentDTO> mergeListAndReturn(@NotNull List<DocumentDTO> input) {
        return List.of();
    }

    @Override
    public DocumentDTO mergeAndReturn(@NotNull DocumentDTO item) {
        return null;
    }

    @Override
    public Boolean update(@NotNull DocumentDTO item) {
        try {
            int result = dslContext.update(document)
                    .set(document.ID_LESSON, item.idLesson)
                    .set(document.DOCUMENT_LINK, item.documentLink)
                    .set(document.DOCUMENT_NAME, item.documentName)
                    .set(document.SIZE, item.size)
                    .set(document.TYPE_DOCUMENT, item.typeDocument)
                    .set(document.DOWNLOAD_STATUS, item.downloadStatus)
                    .set(document.LAST_MODIFIED_DATE, LocalDateTime.now())
                    .where(document.ID_DOCUMENT.eq(item.idDocument))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateList(@NotNull List<DocumentDTO> input) {
        try {
            for (DocumentDTO item : input) {
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
    public Boolean delete(@NotNull DocumentDTO item) {
        try {
            int result = dslContext.deleteFrom(document)
                    .where(document.ID_DOCUMENT.eq(item.getIdDocument()))
                    .execute();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteList(@NotNull List<DocumentDTO> input) {
        try {
            for (DocumentDTO item : input) {
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
            int result = dslContext.deleteFrom(document)
                    .where(document.ID_DOCUMENT.eq(id))
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
