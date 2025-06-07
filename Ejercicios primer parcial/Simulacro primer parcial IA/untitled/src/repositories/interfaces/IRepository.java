package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository <T>
{
    public T findByID(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
}
