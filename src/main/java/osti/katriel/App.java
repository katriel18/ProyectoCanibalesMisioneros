package osti.katriel;
public class App {
    public static void main(String[] args) {

        int[] a = { 3, 0, 0 };

        CanibalesYMisioneros newEstado = new CanibalesYMisioneros(a);
        
        for(Estado sucesor:newEstado.generarSucesores()){
            sucesor.mostrarEstado();
        }

    }
}
