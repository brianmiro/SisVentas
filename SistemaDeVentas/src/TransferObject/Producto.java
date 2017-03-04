//Definimos los atributos y metodos del objeto Producto
package TransferObject;

/**
 *
 * @author Brian Mirò Moròn
 * Programa de Ventas
 * AllFFitness
 * 
 */
public class Producto {
    private int idproducto;
    private String nombre;
    private double precio;
    private int stock;
    private String descripcion;
    private Categoria categoria;

    public int getIdproducto() {
        return idproducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  
    
}
