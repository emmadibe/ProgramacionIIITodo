package repositories.impl;

import config.DBConnection;
import models.entitie.DetallePedido;
import repositories.interfaces.IABMRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DetallePedidoRepository implements IABMRepository<DetallePedido>
{
    private Connection conn;

    public DetallePedidoRepository()
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
    public void add(DetallePedido detallePedido) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(DetallePedido detallePedido) throws SQLException {

    }

    @Override
    public DetallePedido findByID(int id) throws SQLException {
        return null;
    }

    @Override
    public List<DetallePedido> getAll() throws SQLException {
        return List.of();
    }
}
