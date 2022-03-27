package lesson7;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public interface DatabaseRepository {

    boolean saveWeatherData(WeatherData weatherData) throws SQLException;

    ArrayList<WeatherData> getAllSavedData() throws SQLException;
}
