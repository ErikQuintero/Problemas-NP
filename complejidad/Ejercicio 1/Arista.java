import java.util.ArrayList;
import java.util.*;

//Clase que emula una arista de un camino
public class Arista{

    public Vertice v;
    public String s;

    //Contructor de la clase arista
    public Arista(Vertice v, String s){
        this.v = v;
        this.s = s;
    }
        
    //Metodo que genera una lista de aristas 
    public static ArrayList<Arista> generaA(Grafica g, ArrayList<String> lista){
        ArrayList <String> caminoAux = lista;
        ArrayList<Arista> caminoA = new ArrayList<Arista>();
        for(String s : caminoAux){
            Vertice a = g.regresNombreG(s);
            Arista aux = new Arista(a,"0");
            caminoA.add(aux);
        }
        return caminoA;
    }

    //Metodo que les añade una cadena a las aristas
    public static ArrayList<Arista> generaA2(Grafica g, ArrayList<String> lista){
        ArrayList<Arista> caminoA = generaA(g , lista);
        ArrayList <String> caminoAux = lista;
        int cont = 1;
        for(Arista a: caminoA){
            if(cont != caminoA.size()){
                a.s = caminoAux.get(cont);
                cont++;
            }
        }
        int fin = (caminoA.size()) - 1;
        caminoA.remove(fin);
        return caminoA;
    }

    //Metodo que  regresa una arista como cadena
    public String toString(){
        String s2 = "(v" + v.getNombre() + ",v" + s + ") ";
        return s2;
    }

}


