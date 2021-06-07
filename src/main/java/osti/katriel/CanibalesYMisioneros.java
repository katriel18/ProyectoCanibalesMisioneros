package osti.katriel;

import java.util.ArrayList;
import java.util.Arrays;

public class CanibalesYMisioneros implements Estado {

    private final int TAM = 3;
    private final int[] META = { 0, 0, 0 };
    private int[] estadoActual;

    public CanibalesYMisioneros(int[] nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public int[] getEstadoActual() {
        return estadoActual;
    }

    private boolean esValido(int[] nuevoEstado) {
        if (nuevoEstado[0] > 3 || nuevoEstado[1] <0 || nuevoEstado[1] > 3 || nuevoEstado[1] < 0) {
            return false;
        }
        if (nuevoEstado[0] == 0 || TAM-nuevoEstado[0] == 0) {
            return true;
        }
        if (nuevoEstado[0] >= nuevoEstado[1] && TAM - nuevoEstado[0] >= TAM - nuevoEstado[1]) {
            return true;
        }
        return false;
    }

    @Override
    public boolean esMeta() {
        if (Arrays.equals(estadoActual, META)) {
            return true;
        }
        return false;
    }

    private void intercambiarYGuardar(int m, int c, ArrayList<Estado> suc) {
        
        int[] copiaMod = copiarEstadoActual(estadoActual);

        copiaMod[0] += copiaMod[2] == 1 ? -m : +m;
        copiaMod[1] += copiaMod[2] == 1 ? -c : +c;
        copiaMod[2] = copiaMod[2] == 1 ? 0 : 1;

        if (esValido(copiaMod))
            suc.add((new CanibalesYMisioneros(copiaMod)));

    }

    @Override
    public ArrayList<Estado> generarSucesores() {

        ArrayList<Estado> sucesores = new ArrayList<>();

        // Operadores:
        //1 misionero cruza en el bote
        intercambiarYGuardar(1, 0, sucesores);
        //2 misioneros cruzan en el bote
        intercambiarYGuardar(2, 0, sucesores);
        //1 canibal cruza en el bote
        intercambiarYGuardar(0, 1, sucesores);
        //2 canibales cruzan en el bote
        intercambiarYGuardar(0, 2, sucesores);
        //1 misionero y un canibal cruzan en el bote
        intercambiarYGuardar(1, 1, sucesores);

        return sucesores;
        
    }

    @Override
    public void mostrarEstado() {
        System.out.println("(" + estadoActual[0] + ", " + estadoActual[1] + ", " + estadoActual[2] + ")");
    }

    private int[] copiarEstadoActual(int[] estado) {
        int[] resultado = new int[TAM];
        for (int i = 0; i < TAM; i++) {
            resultado[i] = estado[i];
        }
        return resultado;
    }

    @Override
    public boolean igual(Estado e) {
        if (Arrays.equals(estadoActual, ((CanibalesYMisioneros) e).getEstadoActual())) {
            return true;
        }
        return false;
    }

}
