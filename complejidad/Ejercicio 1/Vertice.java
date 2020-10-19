import java.util.ArrayList;

//Clase que modela un vertice en una grafica
public class Vertice implements Comparable<Vertice>{
    
    public ArrayList<Vertice>vecinos; //vecinos del vertice en una grafica 
    String nombre; //Nombre del vertice que sirve como identificador

    //Constructor de la clase vertice
	public Vertice(ArrayList<Vertice>vecinos, String nombre){
        this.vecinos = vecinos;
        this.nombre = nombre;
	}

    //Metodo que regresa el nombre del vertice
	public String getNombre(){
        return nombre;
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

    //Metodo que busca un vertice en los vecinos del vertice
	public boolean buscaNombre(String name){
        boolean valido = false;
        for(Vertice v : this.vecinos){
            if((v.getNombre().equals(name))){
                valido = true;
                break;
            }
        }
        return valido;
    }

    //Metodo que nos sirve para ordenar una lista de vertices
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
