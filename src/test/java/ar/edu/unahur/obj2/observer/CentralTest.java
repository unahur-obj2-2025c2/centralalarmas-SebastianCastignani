package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.exception.DominoException;
import ar.edu.unahur.obj2.observer.riesgo.RiesgoPromedio;

public class CentralTest {

    private Central central;
    private Entidad e1;
    private Entidad e2;

    @BeforeEach
    void setUp(){
        central = new Central();
        e1 = new Entidad("Hospital");
        e2 = new Entidad("Bomberos");
        central.agregarObservador(e1);
        central.agregarObservador(e2);
    }


    @Test
    @DisplayName("dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    void test1(){
     Alerta calor = new Alerta("calor" , 5 );
     Alerta lluvia = new Alerta("lluvia" , 8 );
     central.emitirAlerta("calor", 6);
     central.emitirAlerta("lluvia", 8);
     assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
     assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
     assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
     assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());
     assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
     assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
     assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
     assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());
    }


    @Test
    @DisplayName("emitirAlerta_nivelInvalido_lanzaDominoException")
    void testNivelInvalidoLanzaException() {
        // nivel 0 es inválido
        assertThrows(ar.edu.unahur.obj2.observer.exception.DominoException.class,
            () -> central.emitirAlerta("viento", 0));

        // nivel 11 es inválido
        assertThrows(ar.edu.unahur.obj2.observer.exception.DominoException.class,
            () -> central.emitirAlerta("viento", 11));
    }


    @Test
    @DisplayName("dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    void test2(){
        e1.setCriterioRiesgo(new RiesgoPromedio());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e1.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("lluvia", e1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e1.getAlertasRecibidas().getLast().getNivel());
        assertEquals("calor", e2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, e2.getAlertasRecibidas().getFirst().getNivel());
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, e2.getAlertasRecibidas().getLast().getNivel());
        assertEquals(7, e1.riesgo());
        assertEquals(10, e2.riesgo());
    }

    @Test
    @DisplayName("dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo")
    void test3(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        central.quitarObservador(e1);
        central.emitirAlerta("granizo", 7);
        assertEquals(2, e1.getAlertasRecibidas().size());
        assertEquals(10, e1.riesgo());
        assertEquals(3, e2.getAlertasRecibidas().size());
        assertEquals(7, e2.riesgo());
        assertEquals(5, central.cantidadNotificacionesEnviadas());
    }


    @Test
    @DisplayName ("Excepción")
    void test4() {
        Exception ex = assertThrows(DominoException.class, () -> {
            central.emitirAlerta("frio", 0);
        });
        assertEquals("nivel de alerta invalido", ex.getMessage());
    }


  
}
