package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.Alerta;

public class RiesgoAcumulativo implements  Criterio {

    @Override
    public Double obtenerRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.stream().filter(a -> a.esCritica()).mapToDouble(a -> a.getNivel()).sum();
    }

}
