package lesson7;

import lesson7.enums.Functionality;
import lesson7.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();
    private DatabaseRepositorySQLiteImpl databaseRepositorySQLiteImpl = new DatabaseRepositorySQLiteImpl();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
    }

    public void onUserInput(String input) throws IOException{
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
        }
    }

    public void getCurrentWeather() throws IOException{
        ArrayList <WeatherData> weatherData = weatherProvider.getWeather(Periods.NOW);
        try {
            for (WeatherData item : weatherData) {
                databaseRepositorySQLiteImpl.saveWeatherData(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getWeatherIn5Days() throws IOException{
        ArrayList <WeatherData> weatherData = weatherProvider.getWeather(Periods.FIVE_DAYS);
        try {
            for (WeatherData item : weatherData) {
                databaseRepositorySQLiteImpl.saveWeatherData(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromDb(String date) throws SQLException {
        ArrayList <WeatherData> weatherData = databaseRepositorySQLiteImpl.getAllSavedData();
          Collections.reverse(weatherData);
        for (WeatherData item : weatherData) {
            if(item.getLocalDate().equals(date)) {
                System.out.println(item);
                return;
            }
        }
        throw new SQLException("There is no data in the database");
    }
}
