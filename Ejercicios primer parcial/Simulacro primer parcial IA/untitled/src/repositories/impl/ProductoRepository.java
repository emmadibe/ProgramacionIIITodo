package repositories.impl;

import config.DBConnection;
import models.entitie.Producto;
import models.enums.Categoria;
import repositories.interfaces.IABMRepository;

import java.lang.classfile.constantpool.PoolEntry;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductoRepository implements IABMRepository<Producto>
{
    private Connection conn;

    public ProductoRepository()
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
    public void add(Producto producto) throws SQLException
    {
        String sql = "INSERT INTO productos (nombre, categoria, precioUnitario, stock, fechaVencimiento) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getCategoria().getNombre());
            ps.setInt(3, producto.getPrecioUnitario());
            ps.setInt(4, producto.getStock());
            ps.setDate(5, Date.valueOf(producto.getFechaVencimiento()));
            ps.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "DELETE * FROM productos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.execute();
        }
    }

    @Override
    public void update(Producto producto) throws SQLException
    {
        String sql = "UPDATE productos SET nombre = ?, precioUnitario = ?, stock = ?, categoria = ?, fechaVencimiento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getPrecioUnitario());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCategoria().getNombre());
            ps.setDate(5, Date.valueOf(producto.getFechaVencimiento()));

            ps.execute();
        }
    }

    @Override
    public Producto findByID(int id) throws SQLException
    {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            Producto producto = null;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nombre = rs.getNString("nombre");
                Categoria categoria = Categoria.valueOf(rs.getNString("categoria").toUpperCase());
                int precioUnitario = rs.getInt("precioUnitario");
                int stock = rs.getInt("stock");
                LocalDate fechaVencimiento = LocalDate.parse(String.valueOf(rs.getDate("fechaVencimiento")));
                producto = new Producto(id, nombre, categoria, precioUnitario, stock, fechaVencimiento);
            }
            return producto;
        }
    }

    @Override
    public List<Producto> getAll() throws SQLException
    {
        String sql = "SELECT * FROM productos";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            Producto producto = null;
            List<Producto> productoList = new ArrayList<>();
            while (rs.next()){
                String nombre = rs.getNString("nombre");
                int id = rs.getInt("id");
                String categoriaSTR = rs.getNString("categoria").toUpperCase();
                Categoria categoria = Categoria.valueOf(categoriaSTR);
                int precioUnitario = rs.getInt("precioUnitario");
                int stock = rs.getInt("stock");
                LocalDate fechaVencimiento = LocalDate.parse(String.valueOf(rs.getDate("fechaVencimiento")));

                producto = new Producto(id, nombre, categoria, precioUnitario, stock, fechaVencimiento);
                productoList.add(producto);
            }
            return productoList;
        }
    }
}
