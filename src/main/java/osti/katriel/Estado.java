package osti.katriel;
import java.util.ArrayList;

public interface Estado {
    
    public boolean esMeta();
    public ArrayList<Estado> generarSucesores();
    public void mostrarEstado();
    public boolean igual(Estado e);
    
}
