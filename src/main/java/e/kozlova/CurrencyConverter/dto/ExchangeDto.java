package e.kozlova.CurrencyConverter.dto;
import e.kozlova.CurrencyConverter.utils.Currency;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Dto для сущности Exchange
 */

@Getter
public class ExchangeDto {

    //Валюта из которой проводится конвертация
    @Enumerated(EnumType.STRING)
    private Currency inputCurrency;

    //Валюта, в которую производится конвертация
    @Enumerated(EnumType.STRING)
    private Currency outputCurrency;

    //Количество денежных единиц
    @NotNull
    @Positive
    private Long amount;
}
