package e.kozlova.CurrencyConverter.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties
@Getter
@Setter
public class Response {
    private Object USD;
    private Object EUR;
    private Object RUB;
    private Object GBP;
    private Object BYN;
    private Object CHF;
    private Object CNY;
    private Object JPY;

    @SuppressWarnings("unchecked")
    @JsonProperty("rates")
    private void unpackNested(Map<String,Object> rates) {
        this.RUB = rates.get("RUB");
        this.EUR = rates.get("EUR");
        this.USD = rates.get("USD");
        this.GBP = rates.get("GBP");
        this.BYN = rates.get("BYN");
        this.CHF = rates.get("CHF");
        this.CNY = rates.get("CNY");
        this.JPY = rates.get("JPY");
    }

}
