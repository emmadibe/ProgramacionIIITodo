package services;

import models.entities.Bebida;
import models.enums.TipoBebida;
import repositories.impl.BebidaRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BebidaService
{
    private BebidaRepository bebidaRepository;

    public BebidaService()
    {
        this.setBebidaRepository(new BebidaRepository());
    }

    public void add(Bebida bebida)
    {
        bebidaRepository.add(bebida);
    }

    public void update(Bebida bebida)
    {
        try {
            bebidaRepository.update(bebida);
        } catch (SQLException e) {
            System.err.println("Error al actualizar");
        }
    }
    public void delete(int id)
    {
        try {
            bebidaRepository.delete(id);
            System.out.println("Producto eliminado con éxito");
        } catch (SQLException e) {
            System.err.println("Error. No se pudo borrar el producto de la base de datos.");
        }
    }

    public Optional<Bebida> findById(int id)
    {
        try {
            Bebida bebida = bebidaRepository.findById(id);
            System.out.println("Encontrado");
            return Optional.ofNullable(bebida);
        } catch (SQLException e) {
            System.err.println("El cliente no existe.");
            return Optional.empty();
        }

    }

    public List<Bebida> getAll()
    {
        try {
            return bebidaRepository.getAll();
        } catch (SQLException e) {
            System.out.println(e);
            return List.of();
        }
    }
    public List<Bebida> bebidaFiltrada(TipoBebida tb)
    {
        try {
            List<Bebida> bebidaList = bebidaRepository.getAll().stream()
                    .filter(bebida -> bebida.getTipoBebida().equals(tb))
                    .collect(Collectors.toList());
            return bebidaList;
        }catch (SQLException e){
            System.err.println("Error");
            return List.of();
        }
    }

    public List<Bebida> bebidaFiltradaPorStock(int stockMaximo)
    {
        try {
            List<Bebida> bebidaList = bebidaRepository.getAll().stream()
                    .filter(bebida -> bebida.getStock() <= stockMaximo)
                    .collect(Collectors.toList());
            return bebidaList;
        }catch (SQLException e){
            System.err.println("Error");
            return List.of();
        }
    }

    public BebidaRepository getBebidaRepository()
    {
        return bebidaRepository;
    }

    public void setBebidaRepository(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }
}
