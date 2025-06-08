package models.enums;

public enum TipoCliente
{
    KIOSCO("Kiosco", 1),
    ALMACEN("Almacen", 2),
    SUPERMERCADO("Supermercado", 3);

    private String nombre;
    private int orden;

    private TipoCliente(String nombre, int orden)
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
