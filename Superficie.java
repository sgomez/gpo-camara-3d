/**
Superficie.java
Clase que guarda informacion sobre superficies y las maneja
Guarda los puntos que la componen y su vector normal
@author Sergio Gomez Bachiller
@version 1.00 "Superficie"
*/

import java.util.Vector;

class Superficie {

// Variables

/**
Almacena los vectores que componen la superficie
@see java.util.Vector
*/

  public Vector poligono=new Vector(0,1);

/**
Almacena el vector normal a la superficie
@see Vertice
*/

  public Vertice vertice = new Vertice(0,0,0);

/**
Añade un vertice a la lista de vertices que componen la superficie
@param vertice Indica el numero de vertice
*/

  public void anyadeVertice (int vertice) {
    poligono.addElement(new Integer (vertice));
  }

/**
Indica el vector normal de la superficie
@param nx Valor x del vector normal
@param ny Valor y del vector normal
@param nz Valor z del vector normal
*/

  public void anyadeVNormal (double nx, double ny, double nz) {
    vertice = new Vertice(nx,ny,nz);
  }

/**
Indica el vector normal de la superficie
@param v Vector normal
*/

  public void anyadeVNormal (Vertice v) {
    vertice = new Vertice(v);
  }

/**
Imprime por consola informacion de la superficie
*/

  public void imprime() {
    System.out.println("Se ha creado una superficie con los vectores:"+poligono.toString()+" y su vector normal es: ");
    vertice.imprime();
  }

/**
Devuelve el valor del vector normal
*/

  public Vertice normal() {
    return this.vertice;
  }

}
