package cfm.onthi.repositories;

import cfm.onthi.dtos.base.InputCondition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BaseRepository<T> {

    List<T> getAll();

    List<T> getListByInputCondition(@NotNull InputCondition inputCondition);

    T getByID(@NotNull Long id);

    T getByInputCondition(@NotNull InputCondition inputCondition);

    Boolean saveList(@NotNull List<T> input);

    Boolean save(@NotNull T item);

    List<T> saveListAndReturn(@NotNull List<T> input);

    T saveAndReturn(@NotNull T item);

    Boolean mergeList(@NotNull List<T> input);

    Boolean merge(@NotNull T item);

    List<T> mergeListAndReturn(@NotNull List<T> input);

    T mergeAndReturn(@NotNull T item);

    Boolean update(@NotNull T item);

    Boolean updateList(@NotNull List<T> input);

    Boolean delete(@NotNull T item);

    Boolean deleteList(@NotNull List<T> input);

    Boolean deleteByID(@NotNull Long id);

    Boolean deleteListByID(@NotNull List<Long> input);
}
