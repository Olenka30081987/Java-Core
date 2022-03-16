package lesson7;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric {
    @JsonProperty("Value")
    private String value;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("Unit")
    public String getUnit(){
        return unit;
    }

    @JsonProperty("Unit")
    public void setUnit(String unit){
        this.unit = unit;
    }


}
