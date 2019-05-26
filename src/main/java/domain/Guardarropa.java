package domain;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import exceptions.*;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Guardarropa {

    private Set<Prenda> prendasSuperiores = new HashSet<>();
    private Set<Prenda> prendasInferiores = new HashSet<>();
    private Set<Prenda> calzados = new HashSet<>();
    private Set<Prenda> accesorios = new HashSet<>();
    private Usuario usuario;
    private int limiteDePrendas = usuario.limiteDePrendas(); // el guardarropas queda seteado con el limite que tenga el usuario dueño del mismo
    private int cantidadDePrendas;
    public Set<Prenda> obtenerPrendasSuperiores() {
        return prendasSuperiores;
    }

    //Agregamos el usuario en el constructor porque necesitamos saber
    // el tipo de usuario que tiene asociado para saber el límite de prendas que se pueden agregar

    public Guardarropa(Usuario usuario) {
        this.usuario = requireNonNull(usuario, "Debe ingresar un usuario");
    }

    public Set<Prenda> obtenerPrendasInferiores() {
        return prendasInferiores;
    }

    public Set<Prenda> obtenerCalzados() {
        return calzados;
    }

    public Set<Prenda> obtenerAccesorios() {
        return accesorios;
    }

    public Usuario obtenerUsuario() {
        return usuario;
    }

    public void definirUsuario(Usuario usuario)  {
        if(!isNull(this.usuario)) {
            throw new GuardarropaOcupadoException("Ya tengo dueño/a, no me podes asignar a otra/o");
        }
        this.usuario = requireNonNull(usuario, "El usuario no puede ser vacio");
    }

    public void guardarPrenda(Prenda prenda) {

        if(this.usuario.tieneLimiteDePrendas()) {
            if (this.cantidadDePrendas>=this.usuario.limiteDePrendas()) {
                throw new SuperaLimiteDePrendasException ("Se supera el límite de "+this.usuario.limiteDePrendas() + " prendas definido para el tipo de usuario del guardarropa");
            }
        }
        switch (prenda.obtenerCategoria()) {
            case CALZADO:
                calzados.add(prenda);
                break;
            case PARTE_SUPERIOR:
                prendasSuperiores.add(prenda);
                break;
            case PARTE_INFERIOR:
                prendasInferiores.add(prenda);
                break;
            case ACCESORIO:
                accesorios.add(prenda);
                break;
        }
        this.cantidadDePrendas++;

    }


    private void validarPrendas()  {
        if(prendasInferiores.size() <= 0) {
            throw new FaltanPrendasInferioresException("Faltan prendas inferiores");
        }
        if(prendasSuperiores.size() <= 0) {
            throw new FaltanPrendasSuperioresException("Faltan prendas superiores");
        }
        if(calzados.size() <= 0) {
            throw new FaltanCalzadosException("Faltan calzados");
        }
        if(accesorios.size() <= 0) {
            throw new FaltanAccesoriosException("Faltan accesorios");
        }
    }

    public List<Atuendo> generarSugerencia() {
        this.validarPrendas();
        return Sets.cartesianProduct(prendasSuperiores, prendasInferiores, calzados, accesorios)
                .stream()
                .map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(toList());
    }

}
