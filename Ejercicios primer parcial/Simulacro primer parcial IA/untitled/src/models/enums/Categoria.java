package models.enums;

public enum Categoria
{
    LACTEOS("Lacteos", 1),
    CARNES("Carnes", 2),
    BEBIDAS("Bebidas", 3),
    PANIFICADOS("Panificados", 4);

    private String nombre;
    private int orden;

    private Categoria(String nombre, int orden)
    {
        this.setNombre(nombre);
        this.setOrden(orden);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
