package ar.edu.unahur.obj2.observer;

public interface Observable {
    void agregarObservador(IObserver observador);

    void quitarObservador(IObserver observador);

    void notificar(Alerta alerta);

}
