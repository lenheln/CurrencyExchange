package e.kozlova.CurrencyConverter.service;

import e.kozlova.CurrencyConverter.dto.ExchangeDto;
import e.kozlova.CurrencyConverter.entity.Exchange;
import e.kozlova.CurrencyConverter.entity.User;
import e.kozlova.CurrencyConverter.repository.ExchangeRepository;
import e.kozlova.CurrencyConverter.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Сервисный слой для работы с сущностью Обмен Валюты
 */

@Service
@Transactional
public class ExchangeService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private UserService userService;

    /**
     * Конвертирует сумму из одной валюты в другую
     *
     * @param userId пользователь для которого осуществляется конвертация
     * @param exchangeDto dto с параметрами конвертации
     * @return резульльтат конвертации
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public Double doExchange(Long userId, ExchangeDto exchangeDto) throws NoSuchFieldException, IllegalAccessException {
        saveExchange(userId, exchangeDto);

        String url = String.format("https://api.exchangerate.host/latest?base=%s", exchangeDto.getInputCurrency());
        Response response = restTemplate.getForObject(url, Response.class);
        Field field = response.getClass().getDeclaredField(exchangeDto.getOutputCurrency().toString());
        field.setAccessible(true);
        Double rate = (Double) field.get(response);

        return (Double) rate * exchangeDto.getAmount();
    }

    /**
     * Сохраняет операцию конвертации в базу данных
     *
     * @param userId идентификатор пользователя
     * @param exchangeDto dto с параметрами конвертации
     */
    public void saveExchange(Long userId, ExchangeDto exchangeDto){
        Exchange exchange = convertExchangeDtoToEntity(exchangeDto);
        User user = userService.findUserById(userId);
        exchange.setUser(user);
        exchangeRepository.save(exchange);
    }

    /**
     * Конвертирует dto в сущность Обмен валют
     * @param exchangeDto dto
     * @return сущность Обмен валют
     */
    public Exchange convertExchangeDtoToEntity(ExchangeDto exchangeDto){
        return Exchange.builder()
                .inputCurrency(exchangeDto.getInputCurrency())
                .outputCurrency(exchangeDto.getOutputCurrency())
                .amount(exchangeDto.getAmount())
                .build();
    }
}
