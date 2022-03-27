package lesson7;

import lesson7.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface WeatherProvider {

    ArrayList<WeatherData>  getWeather(Periods periods) throws IOException;

}
