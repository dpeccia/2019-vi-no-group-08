package clima;

import domain.clima.AccuWeather;
import domain.clima.Clima;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class AccuWeatherJsonTest {
    private Clima clima;
    private AccuWeather accuWeather;
    private String jsonClima = "{\n" +
            "    \"Headline\": {\n" +
            "        \"EffectiveDate\": \"2019-05-30T01:00:00-03:00\",\n" +
            "        \"EffectiveEpochDate\": 1559188800,\n" +
            "        \"Severity\": 5,\n" +
            "        \"Text\": \"Previsión de chubascos el miércoles a última hora de la noche\",\n" +
            "        \"Category\": \"rain\",\n" +
            "        \"EndDate\": \"2019-05-30T07:00:00-03:00\",\n" +
            "        \"EndEpochDate\": 1559210400,\n" +
            "        \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/extended-weather-forecast/7894?unit=c&lang=es-ar\",\n" +
            "        \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?unit=c&lang=es-ar\"\n" +
            "    },\n" +
            "    \"DailyForecasts\": [\n" +
            "        {\n" +
            "            \"Date\": \"2019-05-26T07:00:00-03:00\",\n" +
            "            \"EpochDate\": 1558864800,\n" +
            "            \"Sun\": {\n" +
            "                \"Rise\": \"2019-05-26T07:48:00-03:00\",\n" +
            "                \"EpochRise\": 1558867680,\n" +
            "                \"Set\": \"2019-05-26T17:53:00-03:00\",\n" +
            "                \"EpochSet\": 1558903980\n" +
            "            },\n" +
            "            \"Moon\": {\n" +
            "                \"Rise\": \"2019-05-26T00:05:00-03:00\",\n" +
            "                \"EpochRise\": 1558839900,\n" +
            "                \"Set\": \"2019-05-26T13:48:00-03:00\",\n" +
            "                \"EpochSet\": 1558889280,\n" +
            "                \"Phase\": \"Last\",\n" +
            "                \"Age\": 22\n" +
            "            },\n" +
            "            \"Temperature\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 14.4,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 17.8,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"RealFeelTemperature\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 15.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 18.3,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"RealFeelTemperatureShade\": {\n" +
            "                \"Minimum\": {\n" +
            "                    \"Value\": 15.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Maximum\": {\n" +
            "                    \"Value\": 18.3,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"HoursOfSun\": 3.9,\n" +
            "            \"DegreeDaySummary\": {\n" +
            "                \"Heating\": {\n" +
            "                    \"Value\": 2.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                },\n" +
            "                \"Cooling\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"C\",\n" +
            "                    \"UnitType\": 17\n" +
            "                }\n" +
            "            },\n" +
            "            \"AirAndPollen\": [\n" +
            "                {\n" +
            "                    \"Name\": \"AirQuality\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bueno\",\n" +
            "                    \"CategoryValue\": 1,\n" +
            "                    \"Type\": \"Contaminación por partículas\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Grass\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Mold\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Tree\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"Ragweed\",\n" +
            "                    \"Value\": 0,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Name\": \"UVIndex\",\n" +
            "                    \"Value\": 2,\n" +
            "                    \"Category\": \"Bajo\",\n" +
            "                    \"CategoryValue\": 1\n" +
            "                }\n" +
            "            ],\n" +
            "            \"Day\": {\n" +
            "                \"Icon\": 6,\n" +
            "                \"IconPhrase\": \"Mayormente nublado\",\n" +
            "                \"ShortPhrase\": \"Tornándose nublado\",\n" +
            "                \"LongPhrase\": \"Tornándose nublado\",\n" +
            "                \"PrecipitationProbability\": 25,\n" +
            "                \"ThunderstormProbability\": 0,\n" +
            "                \"RainProbability\": 25,\n" +
            "                \"SnowProbability\": 0,\n" +
            "                \"IceProbability\": 0,\n" +
            "                \"Wind\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 11.3,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 327,\n" +
            "                        \"Localized\": \"NNO\",\n" +
            "                        \"English\": \"NNW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"WindGust\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 22.5,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 327,\n" +
            "                        \"Localized\": \"NNO\",\n" +
            "                        \"English\": \"NNW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"TotalLiquid\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Rain\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Snow\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"cm\",\n" +
            "                    \"UnitType\": 4\n" +
            "                },\n" +
            "                \"Ice\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"HoursOfPrecipitation\": 0.0,\n" +
            "                \"HoursOfRain\": 0.0,\n" +
            "                \"HoursOfSnow\": 0.0,\n" +
            "                \"HoursOfIce\": 0.0,\n" +
            "                \"CloudCover\": 86\n" +
            "            },\n" +
            "            \"Night\": {\n" +
            "                \"Icon\": 36,\n" +
            "                \"IconPhrase\": \"Nubes intermitentes\",\n" +
            "                \"ShortPhrase\": \"Nubosidad variable\",\n" +
            "                \"LongPhrase\": \"Nubosidad variable\",\n" +
            "                \"PrecipitationProbability\": 25,\n" +
            "                \"ThunderstormProbability\": 0,\n" +
            "                \"RainProbability\": 25,\n" +
            "                \"SnowProbability\": 0,\n" +
            "                \"IceProbability\": 0,\n" +
            "                \"Wind\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 3.2,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 307,\n" +
            "                        \"Localized\": \"NO\",\n" +
            "                        \"English\": \"NW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"WindGust\": {\n" +
            "                    \"Speed\": {\n" +
            "                        \"Value\": 12.9,\n" +
            "                        \"Unit\": \"km/h\",\n" +
            "                        \"UnitType\": 7\n" +
            "                    },\n" +
            "                    \"Direction\": {\n" +
            "                        \"Degrees\": 307,\n" +
            "                        \"Localized\": \"NO\",\n" +
            "                        \"English\": \"NW\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"TotalLiquid\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Rain\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"Snow\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"cm\",\n" +
            "                    \"UnitType\": 4\n" +
            "                },\n" +
            "                \"Ice\": {\n" +
            "                    \"Value\": 0.0,\n" +
            "                    \"Unit\": \"mm\",\n" +
            "                    \"UnitType\": 3\n" +
            "                },\n" +
            "                \"HoursOfPrecipitation\": 0.0,\n" +
            "                \"HoursOfRain\": 0.0,\n" +
            "                \"HoursOfSnow\": 0.0,\n" +
            "                \"HoursOfIce\": 0.0,\n" +
            "                \"CloudCover\": 69\n" +
            "            },\n" +
            "            \"Sources\": [\n" +
            "                \"AccuWeather\"\n" +
            "            ],\n" +
            "            \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&unit=c&lang=es-ar\",\n" +
            "            \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/daily-weather-forecast/7894?day=1&unit=c&lang=es-ar\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Before
    public void iniciarTest() {
        accuWeather = Mockito.spy(new AccuWeather());
        doReturn(jsonClima).when(accuWeather).getJsonClima();
        clima = new Clima(1558864800,17.8,14.4,25,25);
    }

    @Test
    public void obtenerFechaDelJson() {
        assertEquals(clima.getFecha(), accuWeather.obtenerClima().getFecha());
    }

    @Test
    public void obtenerMaximaDelJson() {
        assertEquals(clima.getTemperaturaMaxima(), accuWeather.obtenerClima().getTemperaturaMaxima(),0);
    }

    @Test
    public void obtenerMinimaDelJson() {
        assertEquals(clima.getTemperaturaMinima(), accuWeather.obtenerClima().getTemperaturaMinima(),0);
    }

    @Test
    public void obtenerProbaPrecipitacionDiaDelJson() {
        assertEquals(clima.getPrecipitacionDia(), accuWeather.obtenerClima().getPrecipitacionDia(),0);
    }

    @Test
    public void obtenerProbaPrecipitacionNocheDelJson() {
        assertEquals(clima.getPrecipitacionNoche(), accuWeather.obtenerClima().getPrecipitacionNoche(),0);
    }
}