package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T>
{
    //Metodos comunes en todos los Repositorios
    public T findById(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
}
