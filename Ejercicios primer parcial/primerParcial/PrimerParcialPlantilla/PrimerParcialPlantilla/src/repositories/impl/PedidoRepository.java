package repositories.impl;

import config.DBConnection;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import repositories.interfaces.IABMRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements IABMRepository<Pedido>
{
    Connection conn;
    public PedidoRepository()
    {
        conn = DBConnection.getInstance().getConnection();
    }
    @Override
    public void add(Pedido pedido) throws SQLException
    {
        String sql = "INSERT INTO pedidos (clienteID, bebidaID, cantidad, estado VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, pedido.getIdCliente());
            ps.setInt(2, pedido.getIdBebida());
            ps.setInt(3, pedido.getCantidad());
            ps.setString(4, pedido.getEstadoPedido().getNombre());

            ps.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException
    {

    }

    @Override
    public Pedido findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Pedido pedido = null;
            if(rs.next()){
                pedido.setId(rs.getInt("id"));
                pedido.setCantidad(rs.getInt("cantidad"));
                pedido.setIdCliente(rs.getInt("clienteID"));
                pedido.setIdBebida(rs.getInt("bebidaID"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getNString("estado")));
            }
            return pedido;
        }
    }

    @Override
    public void update(Pedido pedido) throws SQLException
    {
        String sql = "UPDATE pedidos SET estado = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pedido.getEstadoPedido().getNombre());
            ps.setInt(2, pedido.getId());
            ps.execute();
        }
    }

    @Override
    public List<Pedido> getAll() throws SQLException
    {
        String sql = "SELECT * FROM pedidos";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            Pedido pedido = null;
            List<Pedido> pedidoList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int bebidaID = rs.getInt("bebidaID");
                int clienteID = rs.getInt("clienteID");
                int cantidad = rs.getInt("cantidad");
                String estadoSTR = rs.getString("estado");
                EstadoPedido estadoPedido = EstadoPedido.valueOf(estadoSTR);
                pedido = new Pedido(id, clienteID, bebidaID, cantidad, estadoPedido);
                pedidoList.add(pedido);
            }
            return pedidoList;
        }
    }
}
