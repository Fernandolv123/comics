/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Comic implements Serializable {

    String nombre;
    int cantidad;
    String genero;
    String IVN;
    Coleccion colection;
    String autor;
    float precio;
    byte[] img;

    public Comic() {
    }

    public Comic(String nombre, int cantidad, String genero, String IVN, Coleccion colection, String autor,float precio, byte[] img) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.genero = genero;
        this.IVN = IVN;
        this.colection = colection;
        this.autor = autor;
        this.precio = precio;
        this.img = img;
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

    public String getGnero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public float getPrecio() {
        return precio;
    }
    
    public void setImg(byte[] img){
        this.img = img;
    }
    
    public byte[] getImg(){
        return img;
    }

    @Override
    public String toString() {
        return "Comic{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", genero=" + genero + ", IVN=" + IVN + ", colection=" + colection + ", autor=" + autor + ", precio=" + precio + ", img=" + img + '}';
    }

    
    
}