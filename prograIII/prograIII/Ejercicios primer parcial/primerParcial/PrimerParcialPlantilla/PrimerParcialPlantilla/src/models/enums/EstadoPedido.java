package models.enums;

public enum EstadoPedido
{
    PENDIENTE("Pendiente", 1),
    PREPARADO("Preparado", 2),
    ENTREGADO("Entregado", 3);

    private String nombre;
    private int orden;

    private EstadoPedido (String nombre, int orden)
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
