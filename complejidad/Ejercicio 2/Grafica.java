import java.util.ArrayList;
import java.util.*;

//Clase que representa una grafica
public class Grafica {
    public ArrayList<Vertice>vertices; //Lista de vertices en una grafica
    
    //Constructor de una grafica
	public Grafica(ArrayList<Vertice>vertices){
        this.vertices = vertices;
	}

	//
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
    
    //Metodo adivinador del problema, el cual intenta encontrar una 3-coloracion valida para la grafica
	public Grafica generaColores(){
        for(Vertice x:vertices){
            int nuevoColor = (int)(Math.floor(Math.random()*3+1));
            x.setColor(nuevoColor);
        }
        return this;
	}

    //Metodo que imprime una representacion en cadena de una grafica
	public String toString(){
        String s = "";
        for(Vertice x: vertices){
            s = s + x.toString() + "\n";
        }
        return s;
	}

    //Metodo que imprime una representacion en cadena de una grafica, pero añadiendo los colores de los vertices
	public String toStringColor(){
        String s = "";
        for(Vertice x: vertices){
            s = s + x.toString2() + "\n";
        }
        return s;
	}

    //Metodo que emulada la fase de verificacion del algoritmo NP
	public boolean faseVerificacion(){
        boolean valido = true;
        for(Vertice x:vertices){
            valido = x.verificaColor();
            if(!valido){
                break;
            }
        }
        return valido;
	}

    //Metodo que busca un color usando un nombre
	public int buscaColor(String nombre){
        int color = 0;
        for(Vertice v: vertices){
            if (v.getNombre().equals(nombre)){
                color = v.getColor();
                break;
            }
        }
        return color;
	}

    //Metodo que corrige el color de los vecinos de los vertices
	public void corrigeColor(){
        for(Vertice v: vertices){
            for(Vertice x: v.vecinos){
                int colorReal = buscaColor(x.getNombre());
                x.setColor(colorReal);            
            }
        }
	}
}
