package services;

import models.entitie.Producto;
import models.enums.Categoria;
import repositories.impl.ProductoRepository;

import java.lang.classfile.Opcode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

public class ProductoService
{
    ProductoRepository productoRepository;
    public ProductoService()
    {
        productoRepository = new ProductoRepository();
    }
    public void add(Producto producto)
    {
        try {
            productoRepository.add(producto);
            System.out.println("Producto agrgeado con éxito a la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al agregar un producto a la base de datos. " + e);
        }
    }

    public List<Producto> getAll()
    {
        try {
            List<Producto> productoList = productoRepository.getAll();
            return productoList;
        } catch (SQLException e) {
            System.err.println("Error al intentar traer los datos de la bdd " + e);
            return List.of();
        }
    }

    public void delete(int id)
    {
        try {
            productoRepository.delete(id);
            System.out.println("Producto eliminado con éxito de la BDD");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto de la base de datos " + e);
        }
    }

    public Optional<Producto> findByID(int id)
    {
        try {
            Producto producto = productoRepository.findByID(id);
            return Optional.ofNullable(producto);
        } catch (SQLException e) {
            System.err.println("No existe un producto con ese id " + e);
            return Optional.empty();
        }
    }

    public List<Producto> ejercicio2(Categoria categoria, int minPrice, int maxPrice)
    {
        try {
            List<Producto> productoList = productoRepository.getAll().stream()
                    .filter(p -> p.getCategoria().getNombre().equals(categoria.getNombre()) && p.getPrecioUnitario() >= minPrice && p.getPrecioUnitario() <= maxPrice)
                    .collect(Collectors.toList());
            return productoList;
        }catch (SQLException e){
            System.err.println("Error en la dataBase");
            return List.of();
        }
    }

    public void update(Producto producto)
    {
        try {
            productoRepository.update(producto);
            System.out.println("Producto actualizado con éxito");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto!");
        }
    }
}
