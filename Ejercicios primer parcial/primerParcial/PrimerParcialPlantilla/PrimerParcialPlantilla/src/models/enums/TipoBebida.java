package models.enums;

public enum TipoBebida
{
    GASEOSA("Gaseosa", 1),
    AGUA("Agua", 2),
    JUGO("Jugo", 3),
    CERVEZA("Cerveza", 4);

    private String nombre;
    private int orden;

    private TipoBebida(String nombre, int orden)
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
