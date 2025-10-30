package repository;

import java.util.List;
public interface CrudRepository<T,Id> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(Id id);
    T searchById(Id id);
    List<T>getAll();
}
