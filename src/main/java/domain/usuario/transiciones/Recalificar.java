package domain.usuario.transiciones;

import domain.Atuendo;
import domain.estadoAtuendo.Aceptado;
import domain.Usuario;

public class Recalificar implements Decision {
    private Atuendo atuendo;
    private int calificacionAnterior;

    public Recalificar(Atuendo atuendoRecalificado) {

        this.atuendo = atuendoRecalificado;
    }
    public void deshacer(Usuario usuario) {
        if (this.atuendo.obtenerCalificacionAnterior()==0) {
            this.atuendo.cambiarEstado(new Aceptado(this.atuendo));

        }
        else {
            this.atuendo.calificar(this.atuendo.obtenerCalificacionAnterior());
        }
    }
}