package repositories.impl;

import config.DBConnection;
import models.entitie.Cliente;
import repositories.interfaces.IABMRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteRepository implements IABMRepository<Cliente>
{
    private Connection conn;
    public ClienteRepository()
    {
        this.setConn(DBConnection.getInstance().getConnection());
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Cliente cliente) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Cliente cliente) throws SQLException {

    }

    @Override
    public Cliente findByID(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        return List.of();
    }
}
