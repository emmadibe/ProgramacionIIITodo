package Models;

public class Producto implements Comparable<Producto>
{
    private String nombre;
    private double precio;
    private String categoria;
    private int stock;
    // Constructor, Getters y Setters
    public Producto(String nombre, double precio, String categoria, int
            stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
    }
//Generar Getters, Setters y metodo toString.


    @Override
    public String toString()
    {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int compareTo(Producto o)
    {
        int valor;
        if (this.getPrecio() > o.getPrecio()){
            valor = 1;
        }else if(this.getPrecio() < o.getPrecio()){
            valor = -1;
        }else{
            valor = 0;
        }
        return valor;
    }
}
