package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.riesgo.Criterio;
import ar.edu.unahur.obj2.observer.riesgo.RiesgoCritico;

public class Entidad implements IObserver {

    private final String nombre;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private Criterio criterioRiesgo = new RiesgoCritico();


    public Entidad(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void actualizar(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }


    public Double riesgo(){
        return alertasRecibidas.isEmpty() ? 0.0 : criterioRiesgo.obtenerRiesgo(alertasRecibidas);
    }


    public void setCriterioRiesgo(Criterio criterioRiesgo) {
        this.criterioRiesgo = criterioRiesgo;
    }


    public String getNombre() {
        return nombre;
    }


    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }


    public Criterio getCriterioRiesgo() {
        return criterioRiesgo;
    }


    

}
