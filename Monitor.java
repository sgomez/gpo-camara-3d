import java.awt.*;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

class Monitor extends Canvas {
  public Vector tablaLados;
  public Vector tablaVertices;
  public Vector tablaSuperficies;
  public Vertice centro = new Vertice (0,0,0);
  int perspectiva=1;
  boolean ocultar=false;

  Image imag;
  Graphics grafic;

  public void cambiar(int tipo){
    perspectiva=tipo;
    this.repaint();
  }

  public void rotaz (float giro) {
    for (int i=0; i < tablaVertices.size(); i++) {
      Vertice unvertice = (Vertice) tablaVertices.elementAt(i);
      unvertice.rotaz(giro);
      tablaVertices.setElementAt(unvertice,i);
    }
    for (int i=0; i < tablaSuperficies.size(); i++) {
      Superficie unasuperficie = (Superficie) tablaSuperficies.elementAt(i);
      unasuperficie.vertice.rotaz(giro);
      tablaSuperficies.setElementAt(unasuperficie,i);
    }
    this.repaint();
  } 

  public void rotax (float giro) {
    for (int i=0; i < tablaVertices.size(); i++) {
      Vertice unvertice = (Vertice) tablaVertices.elementAt(i);
      unvertice.rotax(giro);
      tablaVertices.setElementAt(unvertice,i);
    }
    for (int i=0; i < tablaSuperficies.size(); i++) {
      Superficie unasuperficie = (Superficie) tablaSuperficies.elementAt(i);
      unasuperficie.vertice.rotax(giro);
      tablaSuperficies.setElementAt(unasuperficie,i);
    }
   this.repaint();
  } 

  public void rotay (float giro) {
    for (int i=0; i < tablaVertices.size(); i++) {
      Vertice unvertice = (Vertice) tablaVertices.elementAt(i);
      unvertice.rotay(giro);
      tablaVertices.setElementAt(unvertice,i);
    }
    for (int i=0; i < tablaSuperficies.size(); i++) {
      Superficie unasuperficie = (Superficie) tablaSuperficies.elementAt(i);
      unasuperficie.vertice.rotay(giro);
      tablaSuperficies.setElementAt(unasuperficie,i);
    }
    this.repaint();
  } 

  public void paint(Graphics g) {
   if (imag!=null){
    g.drawImage(imag,0,0,this);
   } else 
   update(g);
  }

  public void update(Graphics g) {
	Dimension d=size();
   	if(grafic==null) {
          imag=createImage(d.width,d.height);
	  grafic=imag.getGraphics();
	}
	grafic.clearRect(0,0,d.width,d.height);
	paintAnimation(grafic);
	g.drawImage(imag,0,0,null);
  }

  public void paintAnimation (Graphics g){
    double factor;
    double factor2;
    double angulo;
    int inicio, fin;
    Vertice verticeInicio=new Vertice(0,0,0), verticeFinal= new Vertice(0,0,0);
    for (int i=0; i < tablaSuperficies.size(); i++) {
      Superficie unasuperficie = (Superficie) tablaSuperficies.elementAt(i);
      double pe=unasuperficie.vertice.productoEscalar(new Vertice(0,0,-1));
      if ((pe<0 && perspectiva==1 && ocultar==true) || (perspectiva==1 && ocultar==false) || (perspectiva!=1)) {
	for (int j=0; j < unasuperficie.poligono.size(); j++){	 
	  inicio=((Integer) unasuperficie.poligono.elementAt(j)).intValue();
	  fin=((Integer) unasuperficie.poligono.elementAt((j+1)==unasuperficie.poligono.size()?0:j+1)).intValue();
	  verticeInicio = (Vertice) tablaVertices.elementAt(inicio-1);
	  verticeFinal = (Vertice) tablaVertices.elementAt(fin-1);

	  verticeInicio.escala(100);
	  verticeInicio.traslada(150);
	  verticeFinal.escala(100);
	  verticeFinal.traslada(150);

	  switch (perspectiva) {
	  case 1:
	    g.drawLine((int)verticeInicio.x,(int)verticeInicio.y,(int)verticeFinal.x,(int)verticeFinal.y);
	    break;
	  case 2:
	    angulo=45*Math.PI/180;
	    factor=Math.cos(angulo)*Math.cos(angulo)*Math.sin(angulo);
	    factor2=Math.cos(angulo);
	    g.drawLine((int) (verticeInicio.x+verticeInicio.z*factor-35),(int) (verticeInicio.y+verticeInicio.z*factor2-70),(int)(verticeFinal.x+verticeFinal.z*factor-35),(int)(verticeFinal.y+verticeFinal.z*factor2-70));
	    break;
	  case 3:
	    angulo=63.4*Math.PI/180;
	    factor=Math.cos(angulo)*Math.cos(angulo)*Math.sin(angulo);
	    factor2=Math.cos(angulo);
	    g.drawLine((int) (verticeInicio.x+verticeInicio.z*factor-18),(int) (verticeInicio.y+verticeInicio.z*factor2-44),(int)(verticeFinal.x+verticeFinal.z*factor-18),(int)(verticeFinal.y+verticeFinal.z*factor2-44));
	    break;
	  }

	  verticeInicio.traslada(-150);
	  verticeInicio.escala(0.01);
	  verticeFinal.traslada(-150);
	  verticeFinal.escala(0.01);
	}
      }
    }
  }
  
  void anyadeVertice(double x, double y, double z) {
    Vertice nuevovertice = new Vertice (x,y,z);
    for (int i=0; i < tablaVertices.size() ; i++) {
      Vertice viejovertice = (Vertice) tablaVertices.elementAt(i);
      if (nuevovertice.igual(viejovertice)) return;
    }
    tablaVertices.addElement(nuevovertice);
  }

  void anyadeLado(int inicio, int fin){
    if (inicio>fin) {
      int buffer = inicio;
      inicio = fin;
      fin = buffer;
    }
    Lado nuevolado = new Lado (inicio, fin);
    for (int i=0; i < tablaLados.size() ; i++) {
      Lado viejolado = (Lado) tablaLados.elementAt(i);
      if (nuevolado.igual(viejolado)) return;
    }
    tablaLados.addElement(nuevolado);
  }

  void anyadeSuperficie(int numvertice, int numsuperficie) {
    Superficie unasuperficie;
    if (tablaSuperficies.size()<=numsuperficie) {
      unasuperficie= new Superficie();
      unasuperficie.anyadeVertice(numvertice);
      tablaSuperficies.addElement(unasuperficie);
    } else {
      unasuperficie = (Superficie) tablaSuperficies.elementAt(numsuperficie);
      unasuperficie.anyadeVertice(numvertice);
      tablaSuperficies.setElementAt(unasuperficie,numsuperficie);
    }
  }
    
  void anyadeNormal(double x, double y, double z, int numsuperficie){
    Superficie unasuperficie=(Superficie) tablaSuperficies.elementAt(numsuperficie);
    unasuperficie.anyadeVNormal(x,y,z);
    tablaSuperficies.setElementAt(unasuperficie,numsuperficie);
  }
  void anyadeNormal(Vertice v, int numsuperficie){
    Superficie unasuperficie=(Superficie) tablaSuperficies.elementAt(numsuperficie);
    unasuperficie.anyadeVNormal(v);
    tablaSuperficies.setElementAt(unasuperficie,numsuperficie);
  }

  void tablas (InputStream is){
    double x=0,y=0,z=0,max=-1000;
    int actual, primera, vieja;
    int contsup=0,contvnorm=0;
    tablaLados=new Vector(0,1);
    tablaVertices=new Vector(0,1);
    tablaSuperficies=new Vector(0,1);
    Vertice minimo=new Vertice(1000,1000,1000);
    Vertice maximo=new Vertice(-1000,-1000,-1000);
    try {
      StreamTokenizer st = new StreamTokenizer(is);
      st.eolIsSignificant(true);
      st.commentChar('#');
      while (st.nextToken()!=StreamTokenizer.TT_EOF) {
	if (st.ttype == StreamTokenizer.TT_WORD) {
	  if ("v".equals(st.sval)) {
	    if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
	      x=st.nval;
	      if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
		y=st.nval;
		if (st.nextToken() == StreamTokenizer.TT_NUMBER) 
		  z=st.nval;
	      }
	    }
	    anyadeVertice(x,y,z);
	    minimo = new Vertice (minimo.x>x?x:minimo.x,minimo.y>y?y:minimo.y,minimo.z>z?z:minimo.z);
	    maximo = new Vertice (maximo.x<x?x:maximo.x,maximo.y<y?y:maximo.y,maximo.z<z?z:maximo.z);
	  } else if ("a".equals(st.sval)) {
	    st.nextToken();
	    primera=(int) st.nval;
	    vieja=primera;
	    st.nextToken();
	    do{
	      actual=(int) st.nval;
	      anyadeLado(vieja,actual);
	      anyadeSuperficie(vieja,contsup);
	      vieja=actual;
	    }while (st.nextToken()!=StreamTokenizer.TT_EOL);
	    anyadeLado(actual,primera);
	    anyadeSuperficie(actual,contsup);
	    contsup++;
	  } /*else if ("n".equals(st.sval)) {
	    if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
	      x=st.nval;
	      if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
		y=st.nval;
		if (st.nextToken() == StreamTokenizer.TT_NUMBER) 
		  z=st.nval;
	      }
	    }
	    anyadeNormal(x,y,z,contvnorm);
	    contvnorm++;
	  }*/
	}
      }
    } catch (Exception e){}

    for(int i=0; i<tablaSuperficies.size(); i++) {
	Superficie unasuperficie = (Superficie) tablaSuperficies.elementAt(i);
	if (unasuperficie.poligono.size()<2)
		anyadeNormal(new Vertice(0,0,0),i);
	else {

		Vertice vertice0 = new Vertice((Vertice) tablaVertices.elementAt(((Integer) unasuperficie.poligono.elementAt(0)).intValue()-1));
		Vertice vertice1 = new Vertice((Vertice) tablaVertices.elementAt(((Integer) unasuperficie.poligono.elementAt(1)).intValue()-1));
		Vertice vertice2 = new Vertice((Vertice) tablaVertices.elementAt(((Integer) unasuperficie.poligono.elementAt(2)).intValue()-1));

		Vertice lado2 = new Vertice(vertice1.crearVector(vertice0));
		Vertice lado1 = new Vertice(vertice2.crearVector(vertice1));
		anyadeNormal(new Vertice(lado1.productoVectorial(lado2)),i);
	}
    }

    maximo = new Vertice (maximo.x-minimo.x,maximo.y-minimo.y,maximo.z-minimo.z);
    max=maximo.x>maximo.y?(maximo.x>maximo.z?maximo.x:maximo.z):(maximo.y>maximo.z?maximo.y:maximo.z);
    centro = new Vertice (maximo.x/2/max, maximo.y/2/max, maximo.z/2/max);
    for(int i=0; i<tablaVertices.size(); i++){
      Vertice unvertice = (Vertice) tablaVertices.elementAt(i);
      unvertice.x=((unvertice.x-minimo.x)/max);
      unvertice.y=((unvertice.y-minimo.y)/max);
      unvertice.z=((unvertice.z-minimo.z)/max);
      unvertice.traslada(centro.invierte());  
      tablaVertices.setElementAt(unvertice,i);
    }
  }
}
