package ar.edu.unahur.obj2.observer;

public class Alerta {

    private final String tipo; // tipo de evento (incendio, inundacion, sismo, etc.)
    private final Integer nivel; // nivel de gravedad del evento

    public Alerta(String tipo, Integer nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }


    public Boolean esCritica(){
        return Boolean.valueOf(nivel >= 8);
    }


    public String getTipo() {
        return tipo;
    }


    public Integer getNivel() {
        return nivel;
    }

    

}
