/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Comic {
    String nombre;
    int cantidad;
    String género;
    String IVN;
    Coleccion colection;
    String autor;

    public Comic() {
    }

    public Comic(String nombre, int cantidad, String género, String IVN, Coleccion colection, String autor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.género = género;
        this.IVN = IVN;
        this.colection = colection;
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getGénero() {
        return género;
    }

    public void setGénero(String género) {
        this.género = género;
    }

    public String getIVN() {
        return IVN;
    }

    public void setIVN(String IVN) {
        this.IVN = IVN;
    }

    public Coleccion getColection() {
        return colection;
    }

    public void setColection(Coleccion colection) {
        this.colection = colection;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
}
