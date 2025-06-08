package models.entities;

import models.enums.TipoBebida;

public class Bebida
{
    private  int id;
    private TipoBebida tipoBebida;
    private String nombre;
    private int stock;
    private double precioUnitario;

    public Bebida(String nombre, double precioUnitario, int stock, TipoBebida tipoBebida)
    {
        this.setNombre(nombre);
        this.setStock(stock);
        this.setTipoBebida(tipoBebida);
        this.setPrecioUnitario(precioUnitario);
    }
    public Bebida(int id, String nombre, double precioUnitario, int stock, TipoBebida tipoBebida)
    {
        this.setId(id);
        this.setNombre(nombre);
        this.setStock(stock);
        this.setTipoBebida(tipoBebida);
        this.setPrecioUnitario(precioUnitario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
