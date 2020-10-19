import java.util.ArrayList;
import java.util.*;

//Clase que representa una grafica
public class Grafica {

    public ArrayList<Vertice>vertices; //Lista de vertices en una grafica
    
    //Constructor de una grafica
	public Grafica(ArrayList<Vertice>vertices){
        this.vertices = vertices;
	}

    //Metodo que genera un camino aleatorio
    public ArrayList<String> buscaCamino(String inicio, String destino, int numVertices){
        int longitudC = (int)(Math.floor(Math.random()*20));
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add(inicio);
        for(int i = 0; i < longitudC;i++){
            int nuevoV = (int)(Math.floor(Math.random()*(numVertices)+1));
            String nuevoV2 = ""+ nuevoV;
            nombres.add(nuevoV2);
        }
        nombres.add(destino);
        return nombres;
    }

    //Metodo que verifique que no hay lazos en el camino
    public boolean verificaCaminoLz (ArrayList<String> l){
        int cont = 1;
        boolean valido = true;
        for(String s : l){
            if(cont != l.size()){
                if(s.equals((l.get(cont)))){
                    valido = false;
                    break;
                }
                cont++;
            }
        }
        return valido;
    }

    //Metodo que crean un camino hasta que segenere uno sin lazos
    public ArrayList<String> arreglaCamino(String inicio, String destino, int numVertices){
        ArrayList<String> l = buscaCamino(inicio,destino,numVertices);
        while(!(verificaCaminoLz(l))){
            l = buscaCamino(inicio,destino,numVertices);
        }
        return l;
    } 

    //Metodo que emula la fase verificadora del programa, (verifica si existen las dayasencias del camino en la grafica)
    public boolean faseVerificadora(ArrayList<Arista> l){
        boolean valido = true;
        for(Arista a : l){
            if(!((a.v).buscaNombre((a.s)))){
                valido = false;
                break;
            }
        }
        return valido;
    }

    //Metodo que imprime un camino
    public String imprimeCamino(ArrayList<Vertice> lista){
        String s = "[";
        for(Vertice x :lista){
            s = s + x.getNombre() + ",";
        }
        s = s + "]";
        return s;
    }

    //Metodo que regresa un vertice buscandolo por su nombre
    public Vertice regresNombreG(String name){
        ArrayList<Vertice> vecinosAux = new ArrayList<Vertice>();
        Vertice aux = new Vertice(vecinosAux , "0");
        for(Vertice v : vertices){
            if((v.getNombre().equals(name))){
                aux = v;
                break;
            }
        }
        return aux;
    }
    
	//Refresa una lista con los nombre de los vecinos
    public ArrayList<String> nomVecinos(Vertice v){
        ArrayList<String> vecinos2 = new ArrayList<String>();
        for(Vertice x : v.vecinos){
            vecinos2.add(x.getNombre());
        }
        return vecinos2;
    }

    //Metodo auxiliar 1 para eliminar repetidos
    public int[] eliminaRep1(ArrayList<Vertice> l){
        int x = vertices.size();
        int[] apariciones = new int[x];
        for(Vertice v : l){
            int aux = Integer.parseInt(v.getNombre());
            int aux2 = aux -1;
            apariciones[aux2] += 1;
        }
        return apariciones;
    }

    //Metodo auxiliar 2 para eliminar repetidos
    public ArrayList<Vertice> eliminaRep2(ArrayList<Vertice>  l){
        int[] reps = eliminaRep1(l);
        ArrayList<Vertice> auxiliar = new ArrayList<Vertice>();
        for(Vertice v : l){
            int aux = Integer.parseInt(v.getNombre());
            int aux2 = aux -1;
            if(reps[aux2] >1){
                reps[aux2] -= 1;
            }else if(reps[aux2] == 1){
                auxiliar.add(v);
                reps[aux2] -= 1;
            }   
        }
        return auxiliar;
    }

    //Metodo para eliminar repetidos
    public void eliminaRep3(){
        for(Vertice v : vertices){
            ArrayList<Vertice> auxiliar = eliminaRep2(v.vecinos);
            v.vecinos = auxiliar;
        }
    } 

    //Metodo que ordena todos los vecinos de un vertice de la grafica
    public void ordenar(){
        for (Vertice v : vertices){
            Collections.sort(v.vecinos);
        }
    }

    //Corrige la grafica para que siempre sea no dirigida
    public void dirigidaNo(){
        for(Vertice x : vertices){
            ArrayList<String> auxiliar = nomVecinos(x);
            for(String s : auxiliar){
                for(Vertice y : vertices){
                    if((y.getNombre().equals(s))){
                        y.vecinos.add(x);
                    }
                }
            }    
        }
        eliminaRep3();
        ordenar();
    }
    

    //Metodo que imprime una representacion en cadena de una grafica
	public String toString(){
        String s = "";
        for(Vertice x: vertices){
            s = s + x.toString() + "\n";
        }
        return s;
	}

}
