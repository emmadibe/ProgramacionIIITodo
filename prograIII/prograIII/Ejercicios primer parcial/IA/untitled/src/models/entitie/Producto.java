package models.entitie;

import models.enums.Categoria;

import java.time.LocalDate;

public class Producto implements Comparable<Producto>
{
    private int id;
    private String nombre;
    private Categoria categoria;
    private int precioUnitario;
    private int stock;
    private LocalDate fechaVencimiento;

    public Producto(int id, String nombre, Categoria categoria, int precioUnitario, int stock, LocalDate fechaVencimiento)
    {
        this.setId(id);
        this.setNombre(nombre);
        this.setStock(stock);
        this.setPrecioUnitario(precioUnitario);
        this.setFechaVencimiento(fechaVencimiento);
        this.setCategoria(categoria);
    }
    public Producto(String nombre, Categoria categoria, int precioUnitario, int stock, LocalDate fechaVencimiento)
    {
        this.setNombre(nombre);
        this.setStock(stock);
        this.setPrecioUnitario(precioUnitario);
        this.setFechaVencimiento(fechaVencimiento);
        this.setCategoria(categoria);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", precioUnitario=" + precioUnitario +
                ", stock=" + stock +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public int compareTo(Producto o)
    {
        int value = 0;
        if(this.getPrecioUnitario() > o.getPrecioUnitario()){
            value = 1;
        }else if(this.getPrecioUnitario() < o.getPrecioUnitario()){
            value = -1;
        }
        return value;
    }
}
