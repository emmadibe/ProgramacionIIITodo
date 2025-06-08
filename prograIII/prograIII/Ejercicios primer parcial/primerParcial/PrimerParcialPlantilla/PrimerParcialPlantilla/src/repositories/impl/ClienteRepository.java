package repositories.impl;

import config.DBConnection;
import models.entities.Cliente;
import models.enums.TipoCliente;
import repositories.interfaces.IABMRepository;

import javax.management.relation.RelationSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IABMRepository<Cliente>
{
    private Connection conn;
    public ClienteRepository()
    {
        this.setConn(DBConnection.getInstance().getConnection());
    }

    @Override
    public void add(Cliente cliente) throws SQLException
    {

    }

    @Override
    public void update(Cliente cliente) throws SQLException
    {

    }

    @Override
    public void delete(int id) throws SQLException
    {

    }

    @Override
    public Cliente findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cliente cliente = null;
            if(rs.next()){
                cliente.setId(id);
                cliente.setTipoCliente(TipoCliente.valueOf(rs.getNString("tipo")));
                cliente.setLocalidad(rs.getNString("localidad"));
                cliente.setNombre(rs.getNString("nombre"));
            }
            return cliente;
        }
    }

    @Override
    public List<Cliente> getAll() throws SQLException
    {
        String sql = "SELECT * FROM clientes";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Cliente> clienteList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            Cliente cliente = null;
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getNString("nombre");
                String localidad = rs.getNString("localidad");
                String tipoClienteSTR = rs.getNString("tipo");
                TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteSTR);

                cliente = new Cliente(id, nombre, localidad, tipoCliente);
                clienteList.add(cliente);
            }
            return clienteList;
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
