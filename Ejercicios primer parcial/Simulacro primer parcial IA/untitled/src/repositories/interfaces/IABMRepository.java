package repositories.interfaces;

import java.sql.SQLException;

public interface IABMRepository <T> extends IRepository<T>
{
    public void add(T t) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(T t) throws SQLException;
}
