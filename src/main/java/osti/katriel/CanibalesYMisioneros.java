package osti.katriel;

import java.util.ArrayList;
import java.util.Arrays;

public class CanibalesYMisioneros implements Estado {

    private final int CANIBALES = 3;
    private final int MISIONEROS = 3;
    private final int[] META = { 0, 0, 0 };
    private int[] boteActual;

    public CanibalesYMisioneros(int[] bote) {
        this.boteActual = bote;
    }

    private int[] getBoteActual() {
        return boteActual;
    }

    private int[] getBoteComplemento() {
        int[] boteComplemento = new int[3];
        boteComplemento[0] = MISIONEROS - boteActual[0];
        boteComplemento[1] = CANIBALES - boteActual[1];
        boteComplemento[2] = boteActual[2] == 0 ? 1 : 0;
        return boteComplemento;
    }

    @Override
    public boolean esMeta() {
        if (Arrays.equals(boteActual, META)) {
            return true;
        }
        return false;
    }

    private void transportarYGuardar(int m, int c, ArrayList<Estado> suc) {
        int[] boteCopia = copiarBote(boteActual);
        boteCopia[0] += m;
        boteCopia[1] += c;
        boteCopia[2] = boteCopia[2] == 0 ? 1 : 0;
        suc.add(new CanibalesYMisioneros(boteCopia));
    }

    @Override
    public ArrayList<Estado> generarSucesores() {
        ArrayList<Estado> sucesores = new ArrayList<>();

        int[] complemento = getBoteComplemento();

        // Operadores:
        // Oeste
        if (boteActual[2] == 1) {
            // 1) 1 misionero cruza en el bote
            if (boteActual[0] >= 1 && complemento[0] + 1 >= complemento[1]
                    && (boteActual[0] - 1 >= boteActual[1] || boteActual[0] == 1)) {
                transportarYGuardar(-1, 0, sucesores);
            }
            // 2) 2 misioneros cruzan en el bote
            if (boteActual[0] >= 2 && (boteActual[0] - 2 >= boteActual[1] || boteActual[0] == 2)
                    && complemento[0] + 2 >= complemento[1]) {
                transportarYGuardar(-2, 0, sucesores);
            }
            // 3) 1 canibal cruza en el bote
            if (boteActual[1] >= 1 && complemento[1] + 1 <= complemento[0] || complemento[0] == 0) {
                transportarYGuardar(0, -1, sucesores);
            }
            // 4) 2 canibales cruzan en el bote
            if (boteActual[1] >= 2 && (complemento[1] + 2 <= complemento[0] || complemento[0] == 0)) {
                transportarYGuardar(0, -2, sucesores);
            }
            // 5) 1 misionero y un canibal cruzan en el bote
            if ((boteActual[0] >= 1 && boteActual[1] >= 1) && (complemento[0] >= complemento[1])) {
                transportarYGuardar(-1, -1, sucesores);
            }
        }
        // Este
        if (boteActual[2] == 0) {
            // 1) 1 misionero cruza en el bote
            if (complemento[0] >= 1 && boteActual[0] + 1 >= boteActual[1]
                    && (complemento[0] - 1 >= complemento[1] || complemento[0] == 1)) {
                transportarYGuardar(1, 0, sucesores);
            }
            // 2) 2 misioneros cruzan en el bote
            if (complemento[0] >= 2 && (complemento[0] - 2 >= complemento[1] || complemento[0] == 2)
                    && boteActual[0] + 2 <= boteActual[1]) {
                transportarYGuardar(2, 0, sucesores);
            }
            // 3) 1 canibal cruza en el bote
            if (complemento[1] >= 1 && boteActual[1] + 1 <= boteActual[0] || boteActual[0] == 0) {
                transportarYGuardar(0, 1, sucesores);
            }
            // 4) 2 canibales cruzan en el bote
            if (complemento[1] >= 2 && (boteActual[1] + 2 <= boteActual[0] || boteActual[0] == 0)) {
                transportarYGuardar(0, 2, sucesores);
            }
            // 5) 1 misionero y un canibal cruzan en el bote
            if ((complemento[0] >= 1 && complemento[1] >= 1) && (boteActual[0] >= boteActual[1])) {
                transportarYGuardar(1, 1, sucesores);
            }
        }
        return sucesores;
    }

    @Override
    public void mostrarEstado() {
        System.out.println("(" + boteActual[0] + ", " + boteActual[1] + ", " + boteActual[2] + ")");
    }

    public int[] copiarBote(int[] estado) {
        int[] resultado = new int[3];
        for (int i = 0; i < 3; i++) {
            resultado[i] = estado[i];
        }
        return resultado;
    }

    @Override
    public boolean igual(Estado e) {
        if (Arrays.equals(boteActual, ((CanibalesYMisioneros) e).getBoteActual())) {
            return true;
        }
        return false;
    }

}
