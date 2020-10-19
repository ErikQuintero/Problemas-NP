import java.util.*;

//Clase que nos ayudara a verificar cadenas validas
public class Verificadorcadenas {

    //Metodo que verifica si una cadena es un numero
    public boolean valCad(String s){
        char[] arreglo = s.toCharArray();
        boolean valido = true;
        for(char c : arreglo){
            if(!(Character.isDigit(c))){
                valido = false;
                break;
            }
        }
        return valido;
    }

    //Metodo que verifica que no haya caracteres que no sean digitos ni comas
	public boolean verChar(String s){
        char[] arreglo = s.toCharArray();
        boolean funciona = true;
        for(char c: arreglo){
            if(!(Character.isDigit(c))){
                if(c != ','){
                    funciona = false;
                    break;
                }
            }
        }
        return funciona;
	}

    //Metodo que verifica la estructura de una cadena, para evitar cosas como dos comas seguidas o espacios
	public boolean verEstr(String s){
        char[] arreglo = s.toCharArray();
        boolean funciona = true;
        for(int i = 0; i<arreglo.length; i++){
            if(i != arreglo.length-1){
                if((arreglo[i] == ',') && (arreglo[i+1] == ',')){
                    funciona = false;
                    break;
                }
            }
        }
        return funciona;
	}

    //Metodo que verifica que no existan lazos en la grafica
	public boolean verLazo(String s, int x){
        CaminoNP col = new CaminoNP();
        ArrayList<String> auxiliar = col.iteraString(s);
        String x2 = x + "";
        boolean valido = true;
        for(String s2: auxiliar){
            if(s2.equals(x2)){
                valido = false;
                break;
            }
        }
        return valido;  
	} 

    //Metodo que verifica que los vertices existan en G, para evitar anadir vertices que no esten en G como vertices vecinos
	public boolean verVer(String s, ArrayList<Vertice> g){
        CaminoNP col = new CaminoNP();
        ArrayList<String> auxiliar = col.iteraString(s);
        boolean valido = true;
        for(String s2: auxiliar){
            if(!(existeVer(s2,g))){
                valido = false;
                break;
            }
        }
        return valido;
	}

    //Metodo auxiliar para verVer
	public boolean existeVer(String s, ArrayList<Vertice> g){
        boolean ex = false;
        for (Vertice v: g){
            if((v.getNombre()).equals(s)){
                ex = true;
                break;
            }
        }
        return ex;
	}	
}
