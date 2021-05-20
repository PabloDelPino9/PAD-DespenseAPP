package es.ucm.fdi.despenseapp.Productos;

public class ProductoComprado extends Producto {
    private int cantidad;

    public ProductoComprado(int imgFoto, String titulo, double precio, int cantidad, int id) {
        super(imgFoto, titulo, precio, id);
        this.cantidad = cantidad;
    }

    public ProductoComprado(Producto p) {
        super(p.getImgProducto(), p.getNombreProducto(), p.getPrecioAprox(), p.getIdProd());
        this.cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
