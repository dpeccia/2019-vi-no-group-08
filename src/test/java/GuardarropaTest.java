package domain;
import domain.guardarropa.Gratuito;
import domain.guardarropa.Premium;
import domain.usuario.Calendario;
import domain.usuario.Evento;
import domain.usuario.Periodo;
import domain.usuario.Sensibilidad;
import domain.clima.AccuWeather;
import domain.prenda.Color;
import domain.prenda.Material;
import domain.prenda.TipoDePrenda;
import domain.prenda.Trama;
import exceptions.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

public class GuardarropaTest {
    private Guardarropa guardarropa;
    private Prenda musculosa;
    private Prenda blusa;
    private Prenda campera;
    private Prenda buzo;
    private Prenda crocs;
    private Prenda pantalon;
    private Prenda pantalonPolar;
    private Prenda botasDeNieve;
    private Prenda zapatos;
    private Prenda shortDeJean;
    private Prenda pollera;
    private Prenda ojotas;
    private Prenda pañuelo;
    private Prenda bandana;
    private Prenda gorro;
    private Prenda anteojos;
    private Prenda prendaVacia;
    private Prenda otraPrendaVacia;
    private Color color;
    private Usuario marta;
    private Usuario flor;
    private Usuario pepita;
    private Guardarropa guardarropaLimitado;
    private Guardarropa guardarropaCompartido;
    //prendas de mi guardarropa limitado
    private Prenda remeraFutbol;
    private Prenda camperaDeportiva;
    private Prenda botines;
    private Prenda zapatillas;
    private Prenda shortDeFutbol;
    private Prenda mediasDeFutbol;
    private Prenda canillera;
    private Prenda sinAccesorioCuello;
    private Prenda sinAccesorioManos;
    private Prenda sinAccesorioCuello2;
    private Prenda sinAccesorioManos2;
    private Calendario calendarioMarta;
    private Calendario calendarioFlor;
    private Calendario calendarioPepita;
    private AccuWeather accuWeather;
    private Evento eventoX;
    private LocalDate dia;
    private Sensibilidad sensibilidad;
    private String jsonClimaAbrigoBasico = GeneraJson.getInstance().dameJSONClimaAbrigoBasico();
    private String jsonClimaAbrigoMediano = GeneraJson.getInstance().dameJSONClimaAbrigoMediano();
    private String jsonClimaAbrigoAlto = GeneraJson.getInstance().dameJSONClimaAbrigoAlto();

    @Before
    public void iniciarTest() {

        dia = LocalDate.of(2019,5,26);//Instant.ofEpochMilli(1559188800).atZone(ZoneId.systemDefault()).toLocalDate();
        this.marta = new Usuario("", calendarioMarta, "marta123","","");
        this.flor = new Usuario("",calendarioFlor, "flor123","","");
        this.pepita = new Usuario("", calendarioPepita, "pepita124","","");
        Set<Usuario> usuariosConFlor = new HashSet<>();
        usuariosConFlor.add(flor);
        Set<Usuario> usuariosConMarta = new HashSet<>();
        usuariosConMarta.add(marta);
        this.guardarropa = new Guardarropa(usuariosConFlor,new Premium());
        this.guardarropaLimitado = new Guardarropa(usuariosConMarta,new Gratuito(5));
        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(flor);
        usuarios.add(pepita);
        this.guardarropaCompartido = new Guardarropa(usuarios,new Premium());

        this.color = new Color(1, 2, 3);
        this.pantalonPolar = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false,"pantalon de polar");
        this.botasDeNieve = new Prenda(TipoDePrenda.BOTAS_NIEVE, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false,"botas de nieve");
        this.musculosa = new Prenda(TipoDePrenda.MUSCULOSA, Material.ALGODON, color, null, Trama.LISA, guardarropa,  false,"musculosa");
        this.blusa = new Prenda(TipoDePrenda.BLUSA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false,"blusa");
        this.campera = new Prenda(TipoDePrenda.CAMPERA, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false, "campera");
        this.buzo = new Prenda(TipoDePrenda.BUZO, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false,"buzo");
        this.zapatillas = new Prenda(TipoDePrenda.ZAPATILLAS, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, false, "zapatillas");
        this.crocs = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa, true,"crocs");
        this.ojotas = new Prenda(TipoDePrenda.CROCS, Material.GOMA, color, null, Trama.CUADROS, guardarropa, true,"ojotas");
        this.pantalon = new Prenda(TipoDePrenda.PANTALON, Material.ALGODON, color, null, Trama.CUADROS, guardarropa, true,"pantalon jogging");
        this.zapatos = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropa, true,"zapato cuero");
        this.shortDeJean = new Prenda(TipoDePrenda.SHORT, Material.JEAN, color, null, Trama.LISA, guardarropa, false, "short de jean");
        this.pollera = new Prenda(TipoDePrenda.POLLERA, Material.ALGODON, color, null, Trama.LISA, guardarropa, false,"polera algodón");
        this.pañuelo = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa, false,"pañuelo");
        this.bandana = new Prenda(TipoDePrenda.PANUELO, Material.ALGODON, color, null, Trama.LISA, guardarropa, false, "bandana");
        this.gorro = new Prenda(TipoDePrenda.GORRO, Material.LANA, color, null, Trama.LISA, guardarropa, false,"gorro");
        this.anteojos = new Prenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, color, null, Trama.LISA, guardarropa, false, "anteojos sol");
        this.prendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0, 0, 0), null, Trama.NINGUNO, guardarropa, false, "prenda vacia");
        this.otraPrendaVacia = new Prenda(TipoDePrenda.NINGUNO_SUPERIOR, Material.NINGUNO, new Color(0, 0, 0), null, Trama.NINGUNO, guardarropa, false,"otra prenda vacia");
        this.sinAccesorioManos = new Prenda(TipoDePrenda.ACCESORIO_VACIO_MANOS, Material.NINGUNO, color, null, Trama.LISA, guardarropa, false,"");
        this.sinAccesorioCuello = new Prenda(TipoDePrenda.ACCESORIO_VACIO_CUELLO, Material.NINGUNO, color, null, Trama.LISA, guardarropa, false,"");
        this.sinAccesorioManos2 = new Prenda(TipoDePrenda.ACCESORIO_VACIO_MANOS, Material.NINGUNO, color, null, Trama.LISA, guardarropa, false,"");
        this.sinAccesorioCuello2 = new Prenda(TipoDePrenda.ACCESORIO_VACIO_CUELLO, Material.NINGUNO, color, null, Trama.LISA, guardarropa, false,"");
        //De guardarropa limitado
        this.remeraFutbol = new Prenda(TipoDePrenda.REMERA, Material.ALGODON, color, null, Trama.ESTAMPADO, guardarropaLimitado, false,"remera de futbol");
        this.camperaDeportiva = new Prenda(TipoDePrenda.CAMPERA, Material.ALGODON, color, null, Trama.LISA, guardarropaLimitado, false,"campera deportiva");
        this.botines = new Prenda(TipoDePrenda.ZAPATO, Material.CUERO, color, null, Trama.LISA, guardarropaLimitado, false,"zapato cuero");
        this.shortDeFutbol = new Prenda(TipoDePrenda.SHORT, Material.POLYESTER, color, null, Trama.RAYADA, guardarropaLimitado, false,"short de futbol");
        this.mediasDeFutbol = new Prenda(TipoDePrenda.MEDIAS, Material.POLYESTER, color, null, Trama.CUADROS, guardarropaLimitado, false,"medias de futbol");
        this.canillera = new Prenda(TipoDePrenda.CANILLERA, Material.PLASTICO, color, null, Trama.LISA, guardarropaLimitado, false,"canillera");
        // mockeo clima
        this.accuWeather = Mockito.spy(new AccuWeather());
        doReturn(jsonClimaAbrigoBasico).when(this.accuWeather).getJsonClima();

        doReturn(dia).when(accuWeather).puntoDeReferencia();
        this.eventoX = new Evento("Prueba", "UTN", LocalDateTime.of(2019,5,26,0,0), Periodo.NINGUNO,0);

        eventoX.setearMeteorologo(accuWeather);
        this.sensibilidad = new Sensibilidad();

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void sugerirAtuendosConAbrigoBasico() {
        doReturn(jsonClimaAbrigoBasico).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        Prenda prendaInferior = shortDeJean;
        Prenda prendaInferior2 = pollera;
        Prenda calzado = crocs;
        Prenda accesorio = anteojos;

        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, prendaInferior, calzado, accesorio, this.sinAccesorioCuello, this.sinAccesorioManos);
        Atuendo segundoAtuendo = new Atuendo(prendasSuperiores, prendaInferior2, calzado, accesorio, this.sinAccesorioCuello, this.sinAccesorioManos);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio().equals(sugerencia.obtenerAccesorio()) &&
                            sugerenciaEsperada.obtenerCalzado().equals(sugerencia.obtenerCalzado()) &&
                            sugerenciaEsperada.obtenerPrendaInferior().equals(sugerencia.obtenerPrendaInferior()) &&
                            sugerenciaEsperada.obtenerAccesorioManos().equals(sugerencia.obtenerAccesorioManos()) &&
                            sugerenciaEsperada.obtenerAccesorioCuello().equals(sugerencia.obtenerAccesorioCuello())
            );
            Assert.assertTrue(coincide);
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().size() == 1);
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void sugerirAtuendosConAbrigoMediano() {
        doReturn(jsonClimaAbrigoMediano).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa); // valido
        this.guardarropa.guardarPrenda(this.buzo); // valido
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatillas); // valido
        this.guardarropa.guardarPrenda(this.pantalon); // valido
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.pañuelo); // valido
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.prendaVacia);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, pantalon, zapatillas, pañuelo, this.sinAccesorioCuello, this.sinAccesorioManos);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio().equals(sugerencia.obtenerAccesorio()) &&
                            sugerenciaEsperada.obtenerCalzado().equals(sugerencia.obtenerCalzado()) &&
                            sugerenciaEsperada.obtenerPrendaInferior().equals(sugerencia.obtenerPrendaInferior()) &&
                            sugerenciaEsperada.obtenerAccesorioManos().equals(sugerencia.obtenerAccesorioManos()) &&
                            sugerenciaEsperada.obtenerAccesorioCuello().equals(sugerencia.obtenerAccesorioCuello())
            );
            //prendasSuperiores.retainAll(sugerencia.obtenerPrendasSuperiores());
            //Assert.assertTrue(prendasSuperiores.size() == 3);
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().stream().allMatch(prenda -> prendasSuperiores.contains(prenda)));
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void sugerirAtuendosConAbrigoAlto() {
        doReturn(jsonClimaAbrigoAlto).when(accuWeather).getJsonClima();
        this.guardarropa.guardarPrenda(this.musculosa); // valido
        this.guardarropa.guardarPrenda(this.buzo); // valido
        this.guardarropa.guardarPrenda(this.campera); // valido
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.botasDeNieve); // valido
        this.guardarropa.guardarPrenda(this.pantalonPolar); // valido
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.gorro); // valido
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);

        List<Atuendo> sugerencias = this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        prendasSuperiores.add(this.buzo);
        prendasSuperiores.add(this.campera);
        Set<Prenda> prendasSuperiores2 = new HashSet<>();
        prendasSuperiores2.add(this.musculosa);
        prendasSuperiores2.add(this.buzo);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, pantalonPolar, botasDeNieve, gorro, this.sinAccesorioCuello, this.sinAccesorioManos);
        Atuendo segundoAtuendo = new Atuendo(prendasSuperiores2, pantalonPolar, botasDeNieve, gorro, this.sinAccesorioCuello, this.sinAccesorioManos);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo, segundoAtuendo);
        sugerencias.forEach(sugerencia -> {
            boolean coincide = sugerenciasEsperadas.stream().anyMatch(sugerenciaEsperada ->
                    sugerenciaEsperada.obtenerAccesorio() == sugerencia.obtenerAccesorio() &&
                            sugerenciaEsperada.obtenerCalzado() == sugerencia.obtenerCalzado() &&
                            sugerenciaEsperada.obtenerPrendaInferior() == sugerencia.obtenerPrendaInferior()&&
                            sugerenciaEsperada.obtenerAccesorioManos().equals(sugerencia.obtenerAccesorioManos()) &&
                            sugerenciaEsperada.obtenerAccesorioCuello().equals(sugerencia.obtenerAccesorioCuello())
            );
            Assert.assertTrue(coincide);
            Assert.assertTrue(sugerencia.obtenerPrendasSuperiores().stream().allMatch(prenda -> prendasSuperiores.contains(prenda))
            || sugerencia.obtenerPrendasSuperiores().stream().allMatch(prenda -> prendasSuperiores2.contains(prenda)));
        });
        Assert.assertEquals(sugerenciasEsperadas.size(), sugerencias.size());
    }

    @Test
    public void guardarPartesSuperiores() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.blusa);
        Assert.assertTrue(this.guardarropa.obtenerPrendasSuperiores().contains(this.musculosa));
        Assert.assertTrue(this.guardarropa.obtenerPrendasSuperiores().contains(this.blusa));
        Assert.assertEquals(2, this.guardarropa.obtenerPrendasSuperiores().size());
    }

    @Test
    public void guardarPartesInferiores() {
        this.guardarropa.guardarPrenda(this.shortDeJean);
        this.guardarropa.guardarPrenda(this.pollera);
        Assert.assertTrue(this.guardarropa.obtenerPrendasInferiores().contains(this.shortDeJean));
        Assert.assertTrue(this.guardarropa.obtenerPrendasInferiores().contains(this.pollera));
        Assert.assertEquals(2, this.guardarropa.obtenerPrendasInferiores().size());
    }

    @Test
    public void guardarCalzados() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.zapatos);
        Assert.assertTrue(this.guardarropa.obtenerCalzados().contains(this.crocs));
        Assert.assertTrue(this.guardarropa.obtenerCalzados().contains(this.zapatos));
        Assert.assertEquals(2, this.guardarropa.obtenerCalzados().size());
    }


    @Test
    public void guardarAccesorios() {
        this.guardarropa.guardarPrenda(this.pañuelo);
        this.guardarropa.guardarPrenda(this.anteojos);
        Assert.assertTrue(this.guardarropa.obtenerAccesorios().contains(this.pañuelo));
        Assert.assertTrue(this.guardarropa.obtenerAccesorios().contains(this.anteojos));
        Assert.assertEquals(2, this.guardarropa.obtenerAccesorios().size());
    }

    @Test
    public void noSePuedeSugerirSinParteSuperior() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas superiores. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinAccesorioDeManos() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios manos. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinAccesorioDeCuello() {
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios cuello. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinCalzado() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan zapatos. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinAccesorio() {
        this.guardarropa.guardarPrenda(this.pollera);
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan accesorios. ");
        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void noSePuedeSugerirSinParteInferior() {
        this.guardarropa.guardarPrenda(this.musculosa);
        this.guardarropa.guardarPrenda(this.crocs);
        this.guardarropa.guardarPrenda(this.anteojos);
        this.guardarropa.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropa.guardarPrenda(this.sinAccesorioManos);
        exception.expect(FaltaPrendaException.class);
        exception.expectMessage("Faltan prendas inferiores. ");

        this.guardarropa.generarSugerencia(this.eventoX, this.sensibilidad);
    }

    @Test
    public void superarLimiteDePrendas() {
        exception.expect(SuperaLimiteDePrendasException.class);
        exception.expectMessage("Se supera el límite de " + guardarropaLimitado.getlimiteDePrendas() + " prendas definido para el tipo de guardarropa");
        this.guardarropaLimitado.guardarPrenda(this.remeraFutbol);
        this.guardarropaLimitado.guardarPrenda(this.camperaDeportiva);
        this.guardarropaLimitado.guardarPrenda(this.botines);
        this.guardarropaLimitado.guardarPrenda(this.shortDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.mediasDeFutbol);
        this.guardarropaLimitado.guardarPrenda(this.canillera);
        this.guardarropaLimitado.guardarPrenda(this.anteojos);
    }

    @Test
    public void noPuedoUsarPrendaQueFueAceptada() {
        doReturn(jsonClimaAbrigoBasico).when(accuWeather).getJsonClima();
        this.guardarropaCompartido.guardarPrenda(this.remeraFutbol);
        this.guardarropaCompartido.guardarPrenda(this.musculosa);
        this.guardarropaCompartido.guardarPrenda(this.campera);
        this.guardarropaCompartido.guardarPrenda(this.buzo);
        this.guardarropaCompartido.guardarPrenda(this.crocs);
        this.guardarropaCompartido.guardarPrenda(this.ojotas);
        this.guardarropaCompartido.guardarPrenda(this.shortDeJean);
        this.guardarropaCompartido.guardarPrenda(this.pollera);
        this.guardarropaCompartido.guardarPrenda(this.bandana);
        this.guardarropaCompartido.guardarPrenda(this.anteojos);
        this.guardarropaCompartido.guardarPrenda(this.sinAccesorioCuello);
        this.guardarropaCompartido.guardarPrenda(this.sinAccesorioManos);
        this.guardarropaCompartido.guardarPrenda(this.sinAccesorioCuello2);
        this.guardarropaCompartido.guardarPrenda(this.sinAccesorioManos2);

        Set<Prenda> prendasSuperiores = new HashSet<>();
        prendasSuperiores.add(this.musculosa);
        Atuendo primerAtuendo = new Atuendo(prendasSuperiores, shortDeJean, crocs, anteojos, this.sinAccesorioCuello, this.sinAccesorioManos);
        flor.aceptarAtuendo(primerAtuendo);

        // cuando pepita pide las sugerencias, no puede tener la ropa del primer atuendo

        List<Atuendo> sugerencias = this.guardarropaCompartido.generarSugerencia(this.eventoX, this.sensibilidad);
        Set<Prenda> prendasSuperiores2 = new HashSet<>();
        prendasSuperiores2.add(this.remeraFutbol);
        prendasSuperiores2.add(this.buzo);
        prendasSuperiores2.add(this.campera);
        Atuendo primerAtuendo2 = new Atuendo(prendasSuperiores2, pollera, ojotas, bandana, this.sinAccesorioCuello, this.sinAccesorioManos);
        List<Atuendo> sugerenciasEsperadas = Arrays.asList(primerAtuendo2);
        boolean sugierePrendaAceptada = sugerencias.stream().anyMatch(atuendo -> atuendo.obtenerPrendasSuperiores().contains(musculosa));

        Assert.assertFalse(sugierePrendaAceptada);

    }

}
