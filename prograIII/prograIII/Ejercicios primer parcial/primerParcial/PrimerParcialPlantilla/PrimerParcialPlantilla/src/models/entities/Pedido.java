package models.entities;

import models.enums.EstadoPedido;

public class Pedido
{
    private int id;
    private int idCliente;
    private int idBebida;
    private int cantidad;
    private EstadoPedido estadoPedido;

    public Pedido(int idCliente, int idBebida, int cantidad, EstadoPedido estadoPedido)
    {
        this.setCantidad(cantidad);
        this.setIdBebida(idBebida);
        this.setIdCliente(idCliente);
        this.setEstadoPedido(estadoPedido);
    }
    public Pedido(int id, int idCliente, int idBebida, int cantidad, EstadoPedido estadoPedido)
    {
        this.setId(id);
        this.setCantidad(cantidad);
        this.setIdBebida(idBebida);
        this.setIdCliente(idCliente);
        this.setEstadoPedido(estadoPedido);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idBebida=" + idBebida +
                ", cantidad=" + cantidad +
                ", estadoPedido=" + estadoPedido +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
