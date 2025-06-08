package repositories.impl;

import config.DBConnection;
import models.entitie.Pedido;
import repositories.interfaces.IABMRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PedidoRepository implements IABMRepository<Pedido>
{
    private Connection conn;

    public PedidoRepository()
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
    public void add(Pedido pedido) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Pedido pedido) throws SQLException {

    }

    @Override
    public Pedido findByID(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Pedido> getAll() throws SQLException {
        return List.of();
    }
}
