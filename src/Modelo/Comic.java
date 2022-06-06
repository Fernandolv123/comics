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
	
	String isbn;
    String nombre;
    int cantidad;
    String genero;
    Coleccion colection;
    String autor;
    float precio;
    byte[] img;
    String descripcion;

    public Comic() {
    }

    public Comic(String nombre, int cantidad, String genero, String isbn, Coleccion colection, String autor,float precio, byte[] img, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.genero = genero;
        this.isbn = isbn;
        this.colection = colection;
        this.autor = autor;
        this.precio = precio;
        this.img = img;
        this.descripcion = descripcion;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNombrecol() {
    	return colection.getNombre();
    }
    
    @Override
    public String toString() {
        return "Comic{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", genero=" + genero + ", ISBN=" + isbn + ", colection=" + colection + ", autor=" + autor + ", precio=" + precio + ", img=" + img + '}';
    }
    
    public boolean isFormat() {
    	if(nombre.equals("")  || genero.equals("") || autor.equals("") || img == null ) {
    		return false;
    	}
    	
    	return true;
    	
    	
    }
    
}
