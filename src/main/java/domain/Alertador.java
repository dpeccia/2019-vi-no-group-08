package domain;

import domain.clima.Alerta;
import java.util.List;
import java.util.stream.Collectors;

public class Alertador {

    private static Alertador instanceOfAlertador;

    public static Alertador getInstance() {
        if(instanceOfAlertador==null) {
            instanceOfAlertador = new Alertador();
        }
        return instanceOfAlertador;
    }

    public void alertarQueVaALLoverOGranizar(List<Alerta> alertas) {
        List<Usuario> usuariosConEventoHoy = RepositorioDeUsuarios.getInstance().getUsuarios().stream()
            .filter(usuario -> usuario.obtenerAtuendosSugeridosProximoEvento().getEvento().esHoy())
            .collect(Collectors.toList());
        usuariosConEventoHoy.forEach(usuario -> usuario.recibirAlertas(alertas));
    }

    public void avisarQueEstanListasLasSugerencias() {
        List<Usuario> usuariosParaAvisar = RepositorioDeUsuarios.getInstance().getUsuarios().stream()
                .filter(usuario -> usuario.quiereSerNotificado() && usuario.sugerenciasListasParaProximoEvento())
                .collect(Collectors.toList());
        for(Usuario usuario : usuariosParaAvisar) {
            usuario.notificar("Sugerencias Listas para evento: " + usuario.getCalendario().obtenerProximoEvento().getNombre());
        }
    }

}
