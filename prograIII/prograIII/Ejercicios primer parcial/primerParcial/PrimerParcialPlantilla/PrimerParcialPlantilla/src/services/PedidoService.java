package services;

import models.entities.Bebida;
import models.entities.Cliente;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import models.exceptions.PedidoInvalidoException;
import repositories.impl.BebidaRepository;
import repositories.impl.PedidoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoService
{
    private PedidoRepository pedidoRepository;

    public PedidoService()
    {
        this.setPedidoRepository(new PedidoRepository());
    }

    public List<Pedido> getAllByCliente(int clienteID)
    {
        ClienteService clienteService = new ClienteService();
        List<Pedido> pedidoList = new ArrayList<>();
        Optional<Cliente> cliente = clienteService.findByID(clienteID);
        if(cliente.isPresent()){ //Si existe el cliente...
            System.out.println(cliente); //Imprimo los datos del cliente.
            try {
                pedidoList = pedidoRepository.getAll().stream()
                        .filter(p -> p.getIdCliente() == clienteID)
                        .collect(Collectors.toList());
            }catch (SQLException e){
                System.out.println(e);
            }
        }else{
            System.out.println("No existe un cliente con ese ID, pal.");
        }
        return pedidoList;
    }

    public Optional<Pedido> findByID(int id)
    {
        try {
            Pedido pedido = pedidoRepository.findById(id);
            System.out.println("Pedido encontrado con éxito");
            return Optional.ofNullable(pedido);
        } catch (SQLException e) {
            System.err.println("No se encontró el cliente " + e);
            return Optional.empty();
        }

    }

    public void cambiarEstadoPedido(int id, EstadoPedido nuevoEstado)
    {
        Optional<Pedido> pedido = this.findByID(id);
        if(pedido.isEmpty()){
            System.out.println("no existe un pedido con ese id");
        }else{
            Pedido pedido1 = pedido.get();
            if(pedido1.getEstadoPedido().getNombre().equals("Pendiente") && nuevoEstado.getNombre().equals("Preparada")
            || pedido1.getEstadoPedido().getNombre().equals("Preparado") && nuevoEstado.getNombre().equals("Entregado"))
            {
                try {
                    pedido1.setEstadoPedido(nuevoEstado);
                    pedidoRepository.update(pedido1);
                } catch (SQLException e) {
                    System.out.println("Error al actualizar " + e);
                }
            }else{
                System.out.println("Pasaje de Estado inválido!");
            }
        }
    }

    public void add(Pedido pedido)
    {
        ClienteService clienteService = new ClienteService();
        BebidaService bebidaService = new BebidaService();
        int cantidad = pedido.getCantidad();
        Optional<Cliente> cliente = clienteService.findByID(pedido.getIdCliente());
        Optional< Bebida> bebida = bebidaService.findById(pedido.getIdBebida());
        if(cantidad < 0 || bebida.isEmpty() || cliente.isEmpty() || cantidad > bebida.get().getStock()){
            throw new PedidoInvalidoException("Pedido inválido");
        }else{
            try {
                pedidoRepository.add(pedido);
                System.out.println("Agregado el pedido con exito");
            }catch (SQLException e){
                System.err.println("Error en la base de datos "+ e);
            }
        }

    }

    public Map<EstadoPedido, Long> getCantidadByEstado()
    {
        try {
            Map<EstadoPedido, Long> pedidosMap = pedidoRepository.getAll().stream()
                    .collect(Collectors.groupingBy(p -> p.getEstadoPedido(),
                            Collectors.counting()));
            return pedidosMap;
        }catch (SQLException e){
            System.err.println("Error al buscar datos " + e);
            return Map.of(); // En vez de List.of(), Map.of().
        }

    }

    public PedidoRepository getPedidoRepository() {
        return pedidoRepository;
    }

    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
}
