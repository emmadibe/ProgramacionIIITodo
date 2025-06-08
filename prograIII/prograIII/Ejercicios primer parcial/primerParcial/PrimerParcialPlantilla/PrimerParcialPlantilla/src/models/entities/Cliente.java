package models.entities;

import models.enums.TipoCliente;

public class Cliente
{
    private int id;
    private String nombre;
    private String localidad;
    private TipoCliente tipoCliente;

    public Cliente(String nombre, String localidad, TipoCliente tipoCliente)
    {
        this.setNombre(nombre);
        this.setLocalidad(localidad);
        this.setTipoCliente(tipoCliente);
    }
    public Cliente(int id, String nombre, String localidad, TipoCliente tipoCliente)
    {
        this.setId(id);
        this.setNombre(nombre);
        this.setLocalidad(localidad);
        this.setTipoCliente(tipoCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", tipoCliente=" + tipoCliente +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
