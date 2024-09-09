package org.example;

import Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Create and persist a new Cliente with associated Domicilio
            Domicilio domicilio = new Domicilio("San Martin", 1222);
            Cliente cliente = new Cliente("Pablo", "Muñoz", 12312312);
            cliente.setDomicilio(domicilio);
            domicilio.setCliente(cliente);

            // Create and persist a new Factura with associated Cliente
            Factura factura1 = new Factura();
            factura1.setNumero(12);
            factura1.setFecha("09/09/2024");
            factura1.setCliente(cliente);

            // Create and persist Categorias and Articulos
            Categoria perecederos = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(200, "Yogurt Ser sabor Durazno", 20);
            Articulo art2 = new Articulo(300, "Deteregente Magistral", 40);

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            // Create and persist DetalleFactura
            DetalleFactura det1 = new DetalleFactura();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);
            det1.setFactura(factura1);
            factura1.getDetalles().add(det1);

            DetalleFactura det2 = new DetalleFactura();
            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(80);
            det2.setFactura(factura1);
            factura1.getDetalles().add(det2);

            factura1.setTotal(120);

            entityManager.persist(factura1);
            entityManager.flush();
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("No se pudo crear la tabla");
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}