package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoPromedio implements Criterio {

    @Override
    public Double obtenerRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.stream().mapToDouble(alerta -> alerta.getNivel()).average().orElse(0.0);
    }

}
