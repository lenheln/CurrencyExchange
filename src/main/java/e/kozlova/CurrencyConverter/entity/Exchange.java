package e.kozlova.CurrencyConverter.entity;

import e.kozlova.CurrencyConverter.utils.Currency;
import lombok.*;

import javax.persistence.*;

/**
 * Сущность Обмен валюты
 */
@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXCHANGE")
public class Exchange {

    //Идентификатор конвертации
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Валюта из которой проводится конвертация
    @Column(name = "input_currency")
    private Currency inputCurrency;

    //Валюта, в которую производится конвертация
    @Column(name = "output_currency")
    private Currency outputCurrency;

    //Количество денежных единиц
    @Column(name = "amount")
    private Long amount;

    //Пользователь, производящий конвертацию
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
