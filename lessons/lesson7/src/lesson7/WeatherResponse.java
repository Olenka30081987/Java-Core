package lesson7;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    @JsonProperty("LocalObservationDateTime")
    private String date;

    @JsonProperty("WeatherText")
    private String weatherText;

    @JsonProperty("Temperature")
    private Temperature temperature;

    @JsonProperty("LocalObservationDateTime")
    public String getDate() {
        return date;
    }

    @JsonProperty("LocalObservationDateTime")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("WeatherText")
    public String getWeatherText(){
        return weatherText;
    }

    @JsonProperty("WeatherText")
    public void setWeatherText(String weatherText){
        this.weatherText = weatherText;
    }

    @JsonProperty("Temperature")
    public Temperature getTemperature() {
        return temperature;
    }

    @JsonProperty("Temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

}
