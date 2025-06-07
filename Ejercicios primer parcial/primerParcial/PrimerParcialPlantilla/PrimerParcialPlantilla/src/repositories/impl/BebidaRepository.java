package repositories.impl;

import config.DBConnection;
import models.entities.Bebida;
import models.enums.TipoBebida;
import repositories.interfaces.IABMRepository;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.security.KeyRep;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BebidaRepository implements IABMRepository<Bebida>
{
    private Connection conn = null;
    public BebidaRepository()
    {
        this.setConn(DBConnection.getInstance().getConnection());
    }
    @Override
    public void add(Bebida bebida)
    {
        String sql = "INSERT INTO nombre, stock, tipo, precioUnitario FROM bebidas VALUES(?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, bebida.getNombre());
            stm.setInt(2, bebida.getStock());
            stm.setString(3, bebida.getNombre());
            stm.setDouble(4, bebida.getPrecioUnitario());
            stm.execute();
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if(conn != null) conn.close();
                if(stm != null) stm.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void update(Bebida bebida) throws SQLException
    {
        String sql = "UPDATE bebidas SET nombre = ?, stock = ?, precioUnitario = ?, tipo = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, bebida.getNombre());
            ps.setInt(2, bebida.getStock());
            ps.setDouble(3, bebida.getPrecioUnitario());
            ps.setString(4, bebida.getTipoBebida().getNombre());
            ps.setInt(5, bebida.getId());
            ps.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "DELET * FROM bebidas WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    @Override
    public Bebida findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM bebidas WHERE id = ?";
        Bebida bebida = null;
        ResultSet rs = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getNString("nombre");
                int stock = rs.getInt("stock");
                int precioUnitario = rs.getInt("precioUnitario");
                String tipoSTR = rs.getNString("tipo");
                TipoBebida tb = TipoBebida.valueOf(tipoSTR); //Para pasarlo al enum.
                bebida = new Bebida(id, nombre, precioUnitario, stock, tb);
            }
        }
        return bebida;
    }

    @Override
    public List<Bebida> getAll() throws SQLException
    {
        List<Bebida> bebidaList = new ArrayList<>();
        String sql = "SELECT * FROM bebidas";
        Bebida bebida = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String nombre = rs.getNString("nombre");
                int id = rs.getInt("id");
                int stock = rs.getInt("stock");
                int precioUnitario = rs.getInt("precioUnitario");
                String tipoSTR = rs.getNString("tipo");
                TipoBebida tb = TipoBebida.valueOf(tipoSTR);
                bebida = new Bebida(id, nombre, precioUnitario, stock, tb);
                bebidaList.add(bebida);
            }
        }

        return bebidaList;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
