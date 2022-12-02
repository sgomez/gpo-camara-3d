/**
Lado.class: Clase que guarda los dos vertices que forma un lado
@version 1.00 "Lado"
@author Sergio Gomez Bachiller <i52gobas@uco.es>
*/

class Lado {

// Variables

/**
Almacena el punto de inicio
*/

  private int inicio;

/**
Almacena el punto final
*/

  private int fin;

// Constructores

/**
Constructor al que se le deben indicar el punto de inicio y de fin del
lado
@param ninicio Indica el numero de vertice que inicia el lado
@param nfin Indica el numero de vertice que finaliza el lado
*/

  public Lado (int ninicio,int nfin){
    this.inicio=ninicio;
    this.fin=nfin;
  }

// Metodos

/**
Imprime los vertices del lado
@see Vertice
*/

  public void imprime () {
    System.out.println("Lado "+inicio+" : "+fin);
  }

/**
Indica si dos lados son iguales o no
@param viejo Lado a comparar
*/

  public boolean igual ( Lado viejo ){
    if ((this.inicio==viejo.inicio && this.fin==viejo.fin) ||
(this.inicio==viejo.fin && this.fin==viejo.inicio))
      return true;
    else
      return false;
  }
}
