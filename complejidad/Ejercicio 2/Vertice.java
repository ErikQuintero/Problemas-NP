import java.util.ArrayList;

//Clase que modela un vertice en una grafica
public class Vertice implements Comparable<Vertice>{
    int color; //Color del vertice
    public ArrayList<Vertice>vecinos; //vecinos del vertice en una grafica 
    String nombre; //Nombre del vertice que sirve como identificador

    //Constructor de la clase vertice
	public Vertice(ArrayList<Vertice>vecinos, String nombre){
        this.vecinos = vecinos;
        this.nombre = nombre;
        this.color = 0;
	}

    //Metodo que regresa el color de un vertice
	public int getColor(){
        return color;
	}

    //Metodo que regresa el nombre del vertice
	public String getNombre(){
        return nombre;
	}

    //Metodo que asigna un color a un vertice 
	public void setColor(int nuevoColor){
        this.color = nuevoColor;
	}

    //Metodo que verifica que si algun vecino del vertice tiene su mismo color
	public boolean verificaColor(){
        boolean coloracionValida = true;
        for(Vertice x:vecinos ){
            if (this.color == x.getColor()){
                coloracionValida = false;
                break;
            }   
        }
        return coloracionValida;
	}

    //Metodo que imprime un vertice sin su color
	public String toString(){
        String s = "v";
        s = s + this.nombre + " vecinos: [";
        int tamano = 0;
        for(Vertice x:vecinos){
            if(tamano != ((vecinos.size())-1)){
                s = s + "v"+ x.getNombre()+",";
            }else{
                s = s + "v"+ x.getNombre();
            }
            tamano++;
        }
        s = s + "]";
        return s; 
	}

    //Metodo que imprime una representacion en cadena de un vertice, pero incluyendo su color
	public String toString2(){
        String s = "";
        s = s + "(v"+ this.nombre + ","+ this.colorV(this.getColor()) +")"+ " vecinos: [";
        int tamano = 0;
        for(Vertice x:vecinos){
            if(tamano != ((vecinos.size())-1)){
                s = s + "(v"+ x.getNombre() + "," + x.colorV(x.getColor()) + ")"+",";
            }else{
                s = s + "(v"+ x.getNombre() + "," + x.colorV(x.getColor()) + ")";
            }
            tamano++;
        }
        s = s + "]";
        return s; 
	}

	//Metodo que regresa un color como cadena
	public String colorV(int x){
        if(x == 1){
            return "ROJO";
        }else if(x == 2){
            return "VERDE";
        }else if(x == 3){
            return "AZUL";
        }else{
            return "BLANCO";
        }       
	}

    //Metodo que sirve para ordenar una lista de vertices
	@Override
    public int compareTo(Vertice v) {
        if ((Integer.parseInt(nombre)) < (Integer.parseInt(v.nombre))) {
            return -1;
        }
        if ((Integer.parseInt(nombre)) < (Integer.parseInt(v.nombre))) {
            return 1;
        }
            return 0;
    }
}
