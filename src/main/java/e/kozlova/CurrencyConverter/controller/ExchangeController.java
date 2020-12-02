package e.kozlova.CurrencyConverter.controller;
import e.kozlova.CurrencyConverter.dto.ExchangeDto;
import e.kozlova.CurrencyConverter.service.ExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Контроллер обмена валют")
@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
@Slf4j
public class ExchangeController {

    private final ExchangeService exchangeService;

    /**
     * Конвертирует сумму из одной валюты в другую
     *
     * @param userId идентификатор текущего пользователя
     * @param exchangeDto dto сущности Обмен валют
     * @return результат операции конвертирования
     */
    @PostMapping()
    @ApiOperation("Обмен валюты")
    public Double doExchange(
            @RequestParam Long userId,
            @RequestBody @Valid ExchangeDto exchangeDto) throws NoSuchFieldException, IllegalAccessException {
        log.info("\nКонвертация из {} в {} \n Сумма: {} \nПользователь: {}",
                exchangeDto.getInputCurrency(), exchangeDto.getOutputCurrency(), exchangeDto.getAmount(), userId);
        return exchangeService.doExchange(userId, exchangeDto);
    }
}
