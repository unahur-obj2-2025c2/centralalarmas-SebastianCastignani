package ar.edu.unahur.obj2.observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unahur.obj2.observer.exception.DominoException;

public class Central implements Observable {

    private HashMap<Alerta,Integer> registro = new HashMap<>();
    private Set<IObserver> observadores = new HashSet<>();


    public void emitirAlerta(String tipo, Integer nivel) {
        if ( nivel < 1 || nivel > 10){
            throw new DominoException("nivel de alerta invalido");
        }
        Alerta alerta = new Alerta(tipo, nivel);
        registro.put(alerta , observadores.size());
        notificar(alerta);
    }


    @Override
    public void agregarObservador(IObserver observador) {
        observadores.add(observador);
    }


    @Override
    public void quitarObservador(IObserver observador) {
        observadores.remove(observador);
    }


    @Override
    public void notificar(Alerta alerta) {
       observadores.forEach(observador -> observador.actualizar(alerta));
    }

    public Integer cantidadNotificacionesEnviadas() {
        return registro.values().stream().mapToInt(Integer::intValue).sum(); 
    }

    

}
