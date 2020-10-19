import java.util.ArrayList;
import java.util.*;

//clase que sirve para ejecutar nuestro algoritmo NP de 3-coloracion
public class CaminoNP {

    //Metodo que genera una lista de vertices para una grafica
	public static ArrayList<Vertice> generaVertices(){
        ArrayList<Vertice> vertices = new ArrayList<Vertice>();
        int maxNum = (int)(Math.floor(((Math.random()*10)+10)));
        for(int i = 0; i < maxNum;i++){
            String aux = (i+1) + "";
            ArrayList<Vertice> lista = new ArrayList<Vertice>();
            Vertice v = new Vertice(lista,aux);
            vertices.add(v);
        }
        return vertices;
	}

    //Metodo que nos regresa un valor booleano aleatorio, nos servira en el metodo agregaAd
    public static boolean randomBool(){
        int random = (int)(Math.floor(Math.random()*10+1));
        if (random <= 3){
            return true;
        }else{
            return false;
        }
    }

    //Metodo que genera adyacencias aleatorias en una graafica
	public static ArrayList<Vertice> agregaAd(){
        ArrayList<Vertice> vertices = generaVertices();
        for (Vertice x: vertices){
            for (Vertice y: vertices){
                boolean aux = randomBool();
                if(aux && (!x.getNombre().equals(y.getNombre()))){
                    x.vecinos.add(y);
                }
            }
        }
        return vertices;
	}

	//Metodo que itera la cadena que pasa el usuario y devuelve una lista de cadenas
	public static ArrayList<String> iteraString(String s){
        s = s + ",99";
        String aux = "";
        ArrayList<String> lista = new ArrayList<String>();
        for (char c: s.toCharArray()){ 
            if(!(c == ',')){
                aux = aux + c;    
            }else{
                lista.add(aux);
                aux = "";
            } 
        }
        return lista;
	}

    //Metodo que genera una lista de vertices tomando en cuenta la cadena que escribio el usuario
	public static Vertice generaListaV(String s , String nombre){
	    ArrayList<String> listaAux = iteraString(s);
	    ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	    for(String s1 : listaAux){
	        ArrayList<Vertice> listaAux2 = new ArrayList<Vertice>();
	        Vertice v = new Vertice(listaAux2,s1);
            vertices.add(v);
	    }
	    return new Vertice(vertices, nombre);        
	}
	

    //Metodo main del programa
	public static void main(String [] args){
        System.out.print("Presione 1 si quiere que se genere una grafica aleatoria \nPresione 2 si quiere introducir su propia grafica\n");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.next();
        if(opcion.equals("1")){ 
            //En esta perte del codigo se genera una grafica aleatoria y despues se ejecuta el algoritmo de coloracion
            ArrayList<Vertice> l2 = agregaAd();
            Grafica ejemplar = new Grafica (l2);
            ejemplar.dirigidaNo();
            System.out.print("\nTu grafica tiene "+ (ejemplar.vertices).size() + " vertices\n");
            System.out.print("\nIngresa el vertice inicio(solo se aceptan numeros)\n");
            Scanner sc2 = new Scanner(System.in);
            String inicio = sc.next();
            Verificadorcadenas verificador = new Verificadorcadenas();
            System.out.print("\nIngresa el vertice final(solo se aceptan numeros)\n");
            Scanner sc3 = new Scanner(System.in);
            String destino = sc.next();
            boolean valC = verificador.valCad(inicio) && verificador.valCad(destino);
            if(valC){
                boolean ve0 = ((Integer.parseInt(inicio)) >0) && ((Integer.parseInt(destino)) >0 && !(inicio.equals(destino))); 
                if((Integer.parseInt(inicio)) <= (ejemplar.vertices).size() && ((Integer.parseInt(destino)) <= (ejemplar.vertices).size()) && ve0){
                    System.out.print("\nLa grafica es: \n"); 
                    System.out.print(ejemplar.toString());
                    System.out.print("\nEl candidato a camino es: \n");
                    ArrayList<String> caminoS = ejemplar.arreglaCamino(inicio,destino,(ejemplar.vertices).size());
                    ArrayList<Arista> camino = Arista.generaA2 (ejemplar, caminoS);
                    System.out.print("\n"+camino+"\n");
                    boolean si = ejemplar.faseVerificadora(camino); 
                    if(si){
                        System.out.print("\nEl candidato si es una solucion del problema\n");
                    }else{
                        System.out.print("\nEl candidato no es una solucion del problema\n");
                    }
                }else{
                    System.out.print("\nAlguno de los vertices que ingresaste es invalido \n");
                }
            }else{
                System.out.print("\nAlguno de los vertices que ingresaste es invalido \n");
            }
        }else if(opcion.equals("2")){
            //En esta seccion del codigo se genera la grafica proporcionada por el usuario y se ejecuta el algoritmo de alcanzabilidad
            //Ademas tambien hace verificaciones y todas esas cosas, por eso hay mucho codigo
            //Esta parte del codigo solo es para verificar y construir las graficas proporcionadas por el usuario
            System.out.print("Cuantos vertices quieres en la grafica?(Maximo 20)");
            Scanner sc2 = new Scanner(System.in);
            int nuMax = sc2.nextInt();
            if(nuMax < 10 || nuMax >20){
                System.out.print("Ingresaste un numero invalido");   
            }else{
                //En esta perte del codigo se procesa lo que escribe el usuario para tranformarlo en una grafica
                ArrayList<Vertice> verticesG = new ArrayList<Vertice>();
                ArrayList<String> memoria = new ArrayList<String>();
                for (int i = 0; i<nuMax;i++){
                    System.out.print("\nEscribe los vecinos del vertice: "+ (i+1) +
                    " separados por comas y sin espacios(Recuerda que solo se aceptan numeros)\n ");
                    Scanner sc3 = new Scanner(System.in);
                    String vertices = sc3.next();
                    Verificadorcadenas verificador = new Verificadorcadenas();
                    boolean verChar = verificador.verChar(vertices);
                    boolean verEstr = verificador.verEstr(vertices);
                    boolean verLazo = verificador.verLazo (vertices,i+1);
                    if(verLazo && verEstr && verChar && !(vertices.equals(""))){
                        String nombre = (i+1) + "";
                        Vertice vertice = generaListaV(vertices, nombre);
                        memoria.add(vertices);
                        verticesG.add(vertice);
                    }else{
                        System.out.print("\nLa cadena ingresada es invalida, vuelve a intentar");
                        System.exit(0);
                    }
                }
                //En esta parte del codigo se ejecuta el algoritmo usando la grafica proporcionada por el usuario
                Verificadorcadenas verificador2 = new Verificadorcadenas();
                for(String s2 : memoria){
                    if(!(verificador2.verVer(s2,verticesG))){
                            System.out.print("\nIngresaste algun vertice invalido, vuelve a intentar");
                            System.exit(0); 
                    }
                }
                Grafica ejemplar = new Grafica(verticesG);
                ejemplar.dirigidaNo();
                System.out.print("\nIngresa el vertice inicio(solo se aceptan numeros)\n");
                Scanner sc3 = new Scanner(System.in);
                String inicio = sc.next();
                Verificadorcadenas verificador = new Verificadorcadenas();
                System.out.print("\nIngresa el vertice final(solo se aceptan numeros)\n");
                Scanner sc4 = new Scanner(System.in);
                String destino = sc.next();
                boolean valC = verificador.valCad(inicio) && verificador.valCad(destino);
                if(valC){
                    boolean ve0 = ((Integer.parseInt(inicio)) >0) && ((Integer.parseInt(destino)) >0 && !(inicio.equals(destino)));
                    if((Integer.parseInt(inicio)) <= (ejemplar.vertices).size() && ((Integer.parseInt(destino)) <= (ejemplar.vertices).size()) && ve0){
                        System.out.print("\nLa grafica es: \n");    
                        System.out.print(ejemplar.toString());
                        System.out.print("\nEl candidato a camino es: \n");
                        ArrayList<String> caminoS = ejemplar.arreglaCamino(inicio,destino,(ejemplar.vertices).size());
                        ArrayList<Arista> camino = Arista.generaA2 (ejemplar, caminoS);
                        System.out.print("\n"+camino+"\n");
                        boolean si = ejemplar.faseVerificadora(camino);
                        if(si){
                            System.out.print("\nEl candidato si es una solucion del problema\n");
                        }else{
                            System.out.print("\nEl candidato no es una solucion del problema\n");
                        }
                    }
                }
            }
        }else{
            System.out.print("No seleccionaste una opcion valida, vuelve a ejecutar");
        } 
	}  
}
