// SpecTcl generated class Camara, version 1.1

import java.awt.*;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class Camara extends java.applet.Applet {		

// a slot to hold an arbitrary object pointer that can
// be filled in by the app. and referenced in actions
public Object arg;

public Monitor pantalla;
public Label label_3;
public Choice perspectiva;
public Label label_1;
public Checkbox si;
public CheckboxGroup ocultar = new CheckboxGroup();
public Checkbox no;
public Label label_2;
public TextField fichero;

int viejax, viejay;
String viejo;

//methods to support form introspection
public static String names[] = {
	"pantalla","label_3","perspectiva","label_1","ocultar","si","no","label_2","fichero",
};
public String[] getNames() {
	return names;
}

//There should be an easier way to do this
public Object[] getWidgets() {
	Object[] list = new Object[9];
	list[0] = pantalla;
	list[1] = label_3;
	list[2] = perspectiva;
	list[3] = label_1;
	list[4] = ocultar;
	list[5] = si;
	list[6] = no;
	list[7] = label_2;
	list[8] = fichero;
	return list;
}

public void init() {

    	InputStream is = null;
    	String nombrefichero = new String("");

    	try {
     	 nombrefichero = new String (getParameter("fichero"));
     	 viejo = new String(nombrefichero);    
     	 is = new URL(getDocumentBase(), nombrefichero).openStream();
    	} catch (Exception e){}

	// main panel
	GridBagLayout grid = new GridBagLayout();
	int rowHeights[] = {0,301,30,30,30};
	int columnWidths[] = {0,30,30,30};
	double rowWeights[] = {0.0,0.0,0.0,0.0,0.0};
	double columnWeights[] = {0.0,0.0,0.0,0.0};
	grid.rowHeights = rowHeights;
	grid.columnWidths = columnWidths;
	grid.rowWeights = rowWeights;
	grid.columnWeights = columnWeights;

	pantalla = new Monitor();
	this.add(pantalla);
	pantalla.tablas(is);

	label_3 = new Label();
	label_3.setText("Tipo de perspectiva:");
	this.add(label_3);

	perspectiva = new Choice();
	perspectiva.addItem("Paralela");
	perspectiva.addItem("Caballera");
//	perspectiva.addItem("Isométrica");
	this.add(perspectiva);

	label_1 = new Label();
	label_1.setText("¿Ocultar caras posteriores?");
	this.add(label_1);

	si = new Checkbox();
	si.setLabel("Si");
	si.setCheckboxGroup(ocultar);
	this.add(si);

	no = new Checkbox();
	no.setLabel("No");
	no.setCheckboxGroup(ocultar);
	no.setState(true);
	this.add(no);

	label_2 = new Label();
	label_2.setText("Nombre del fichero:");
	this.add(label_2);

	fichero = new TextField(20);
	fichero.setText(nombrefichero);
	this.add(fichero);

	// Geometry management
	GridBagConstraints con = new GridBagConstraints();
	reset(con);
	con.gridx = 1;
	con.gridy = 1;
	con.gridwidth = 3;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.BOTH;
	grid.setConstraints(pantalla, con);

	reset(con);
	con.gridx = 1;
	con.gridy = 2;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(label_3, con);

	reset(con);
	con.gridx = 2;
	con.gridy = 2;
	con.gridwidth = 2;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(perspectiva, con);

	reset(con);
	con.gridx = 1;
	con.gridy = 3;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(label_1, con);

	reset(con);
	con.gridx = 2;
	con.gridy = 3;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(si, con);

	reset(con);
	con.gridx = 3;
	con.gridy = 3;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(no, con);

	reset(con);
	con.gridx = 1;
	con.gridy = 4;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(label_2, con);

	reset(con);
	con.gridx = 2;
	con.gridy = 4;
	con.gridwidth = 2;
	con.anchor = GridBagConstraints.CENTER;
	con.fill = GridBagConstraints.NONE;
	grid.setConstraints(fichero, con);


	// Resize behavior management and parent heirarchy
	setLayout(grid);

	// Give the application a chance to do its initialization
	
}

  public boolean handleEvent (Event e) {
    if (e.id == Event.WINDOW_DESTROY) 
      System.exit(0);
    else if (e.id == Event.MOUSE_DRAG) {
      pantalla.rotay(e.x-viejax);
      pantalla.rotax(e.y-viejay);
      viejax=e.x;
      viejay=e.y;
    } else if (e.id == Event.KEY_PRESS && e.key=='\n') {
      if (!(fichero.getText()).equals(viejo)) {
        try {
          InputStream is = new URL(getDocumentBase(),fichero.getText()).openStream();
          pantalla.tablas(is);
          is.close();
          viejo=fichero.getText();
	  pantalla.repaint();
        } catch (Exception eee) {
          fichero.setText("***error***");
          viejo="***error***";
	  eee.printStackTrace();
        }
      }
   }
   return super.handleEvent(e);
  }

  public boolean action(Event e, Object arg) {
    if (e.target instanceof Choice) {
      String opcion = new String (((Choice) (e.target)).getSelectedItem());
      if (opcion.equals("Paralela")) {
	pantalla.cambiar(1);
	si.enable();
	no.enable();
	label_1.enable();
      } else if (opcion.equals("Caballera")) {
	pantalla.cambiar(2);
	si.disable();
	no.disable();
	label_1.disable();
      } else if (opcion.equals("Isométrica")) {
	si.disable();
	no.disable();	
	label_1.disable();
	pantalla.cambiar(3);
      } 
    } else if (e.target instanceof Checkbox) {
      String opcion = new String (((Checkbox) (e.target)).getLabel());
      if (opcion.equals("Si")) {
	pantalla.ocultar=true;
	pantalla.repaint();
      }
      else if (opcion.equals("No")) {
	pantalla.ocultar=false;
	pantalla.repaint();
      }
      return true;
    } 
    return false;
  }

  private static String[][] paramInfo = {
 	{"FICHERO", "URL", "Nombre del fichero con los datos"},
  };

 public String[][] getParameterInfo() {
  return paramInfo;
 }

 public String getAppletInfo() {
  return new String("Camara v1.00\nPor Sergio Gómez Bachiller <i52gobas@uco.es>");
}

public static void main(String[] args) {
    Frame f = new Frame("Camara Test");
    Camara win = new Camara();
    win.init();
    f.add("Center", win);
    f.pack();
    f.show();
}

private void reset(GridBagConstraints con) {
    con.gridx = GridBagConstraints.RELATIVE;
    con.gridy = GridBagConstraints.RELATIVE;
    con.gridwidth = 1;
    con.gridheight = 1;
 
    con.weightx = 0;
    con.weighty = 0;
    con.anchor = GridBagConstraints.CENTER;
    con.fill = GridBagConstraints.NONE;
 
    con.insets = new Insets(0, 0, 0, 0);
    con.ipadx = 0;
    con.ipady = 0;
}

}
