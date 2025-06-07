package repositories.interfaces;

import java.sql.SQLException;

public interface IABMRepository<T> extends IRepository<T>
{
    //Repositorios que implementan Alta, Baja y Modificacion
    public void add(T t) throws SQLException;
    public void update(T t) throws SQLException;
    public void delete(int id) throws SQLException;
}
