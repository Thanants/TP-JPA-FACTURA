package Entidades;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private int subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;

    public DetalleFactura(Factura factura, Articulo articulo, int subtotal, int cantidad) {
        this.factura = factura;
        this.articulo = articulo;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }



    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public DetalleFactura(int subtotal, int cantidad, Articulo articulo) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public DetalleFactura() {
    }

    public DetalleFactura(int subtotal, int cantidad) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
