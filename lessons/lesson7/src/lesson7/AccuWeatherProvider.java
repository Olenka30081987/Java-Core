package lesson7;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.enums.Periods;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
import java.sql.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.ArrayList;
import java.util.List;



public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public ArrayList <WeatherData> getWeather(Periods periods) throws IOException {
        City city = detectCityKey();
        String cityKey = city.getCityKey();
        ArrayList <WeatherData> weatherDataItems = new ArrayList <WeatherData>();
        if (periods.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            String body = response.body().string();

            StringReader reader = new StringReader(body.substring(1, body.length() - 1));
            WeatherResponse weatherResponse = objectMapper.readValue(reader, WeatherResponse.class);
            String date = weatherResponse.getDate().substring(0, 10);
            System.out.println("В городе " + city.getCityName() +
                    " на дату " + date + " сегодня "
                    + weatherResponse.getWeatherText() + " температура "
                    + weatherResponse.getTemperature().getMetric().getValue()
                    + weatherResponse.getTemperature().getMetric().getUnit());
            WeatherData weatherData = new WeatherData(city.getCityName(), date, weatherResponse.getWeatherText(), weatherResponse.getTemperature().getMetric().getValue());
            weatherDataItems.add(weatherData);
        } else if (periods.equals(Periods.FIVE_DAYS)) {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String body = response.body().string();

            StringReader reader = new StringReader(body);

            Example example = objectMapper.readValue(reader, Example.class);
            for ( DailyForecast item : example.getDailyForecasts()) {
                String date = item.getDate().substring(0,10);
                String precipitationTypeDay = item.getDay().getPrecipitationType();
                String precipitationTypeNight = item.getNight().getPrecipitationType();
                System.out.println("В городе " + city.getCityName()
                        + " на дату " + date
                        +  " ожидается днем " + (precipitationTypeDay == null ? "без осадков" : precipitationTypeDay)
                        +  " ожидается ночью " + (precipitationTypeNight == null ? "без осадков" : precipitationTypeNight)
                        + " температура от " + item.getTemperature().getMinimum().getValue()
                        + item.getTemperature().getMinimum().getUnit()
                        + " до " + item.getTemperature().getMaximum().getValue()
                        + item.getTemperature().getMaximum().getUnit());

                WeatherData weatherData = new WeatherData(city.getCityName(), date, precipitationTypeDay == null ? "без осадков" : precipitationTypeDay, item.getTemperature().getMinimum().getValue());
                weatherDataItems.add(weatherData);
            }
        }
        return weatherDataItems;
    }

    public City detectCityKey() throws IOException {
        City city = new City();
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
            city.setCityName(cityName);
        } else throw new IOException("Server returns 0 cities");
        city.setCityKey(objectMapper.readTree(jsonResponse).get(0).at("/Key").asText());

        return city;
    }

}
