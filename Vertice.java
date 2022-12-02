/**
Vertice.java
Clase que crea Vertices y los maneja
@version 1.00 "Vertice.java"
@author Sergio Gomez Bachiller <i52gobas@uco.es>
*/

class Vertice {

// Variables

/**
Valor x del vertice
*/

  public double x;

/**
Valor y del vertice
*/

  public double y;

/**
Valor z del vertice
*/

  public double z;

// Constructores

/**
Constructor que acepta los valores de los tres puntos
@param nx Valor de x
@param ny Valor de y
@param nz Valor de z
*/

  public Vertice (double nx, double ny, double nz) {
    this.x=nx;
    this.y=ny;
    this.z=nz;
  }

/**
Constructor que acepta otro vertice para construir este
@param v Vertice que sera copiado para crear uno nuevo
*/

  public Vertice (Vertice v) {
    this.x=v.x;
    this.y=v.y;
    this.z=v.z;
  }

// Metodos

/**
Imprime por la consola el valor del vertice
*/

  public void imprime () {
    System.out.println("Vertice ("+x+","+y+","+z+")");
  }

/**
Indica si dos vertices son iguales
@param viejo El vertice a comparar
*/

  public boolean igual ( Vertice viejo ) {
    if (this.x==viejo.x && this.y==viejo.y && this.z==viejo.z)
      return true;
    else
      return false;
  }

/**
Modifica la escala del punto 
@param s Valor por el que escalar el punto
*/

  public void escala (double s) {
    x*=s;
    y*=s;
    z*=s;
  }

/**
Modifica la escala del punto
@param s Vertice que contiene los valores por los que escalar el punto en cada eje
*/

  public void escala (Vertice s) {
    x*=s.x;
    y*=s.y;
    z*=s.z;
  }

/**
Traslada el punto
@param t Valor que indica la distancia de traslado
*/  

  public void traslada (double t) {
    x+=t;
    y+=t;
    z+=t;
  }

/**
Traslada el punto
@param t Vector que indica cuanto se va a trasladar el punto en cada eje
*/  

 public void traslada (Vertice t) {
   x+=t.x;
   y+=t.y;
   z+=t.z;
 }

/**
Invierte los valores del vertice
*/

public Vertice invierte () {
  return new Vertice (-x,-y,-z);
}

/**
Rota el punto en el eje de las y
@param angulo Valor del angulo de giro
*/

  public void rotay (double angulo){
    angulo*=(Math.PI/180);
    double coseno = Math.cos(angulo);
    double seno = Math.sin (angulo);
    double viejax=x*coseno+z*seno;
    double viejaz=z*coseno-x*seno;
    x=viejax;
    z=viejaz;
  }

/**
Rota el punto en el eje de las x
@param angulo Valor del angulo de giro
*/

  public void rotax (double angulo){
    angulo*=(Math.PI/180);
    double coseno = Math.cos(angulo);
    double seno = Math.sin (angulo);
    double viejay=y*coseno+z*seno;
    double viejaz=z*coseno-y*seno;
    y=viejay;
    z=viejaz;
  }

/**
Rota el punto en el eje de las z
@param angulo Valor del angulo de giro
*/

  public void rotaz (double angulo){
    angulo*=(Math.PI/180);
    double coseno = Math.cos(angulo);
    double seno = Math.sin (angulo);
    double viejay=y*coseno+x*seno;
    double viejax=x*coseno-y*seno;
    x=viejax;
    y=viejay;
  }

/** 
Crea un vector
@param unvertice Vertice que se usara para crear el vector
*/

  public Vertice crearVector(Vertice unvertice){
    return(new Vertice(this.x-unvertice.x, this.y-unvertice.y, this.z-unvertice.z));
  }

/**
Realiza el producto escalar de dos vectores
@param unvertice Vertice por el que se hara el producto escalar
*/

  public double productoEscalar (Vertice unvertice) {
    return (this.x*unvertice.x+this.y*unvertice.y+this.z*unvertice.z);
  }

/**
Realiza el producto vectorial de dos vectores
@param unvertice Vertice por el que se hara el producto vectorial
*/

  public Vertice productoVectorial (Vertice unvertice) {
    return (new Vertice(this.y*unvertice.z-unvertice.y*this.z,-(this.x*unvertice.z-unvertice.x*this.z),this.x*unvertice.y-unvertice.x*this.y));
  }
}

