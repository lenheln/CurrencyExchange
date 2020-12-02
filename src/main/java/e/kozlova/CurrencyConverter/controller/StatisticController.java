package e.kozlova.CurrencyConverter.controller;

import e.kozlova.CurrencyConverter.entity.User;
import e.kozlova.CurrencyConverter.service.StatisticService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Сервисный слой работы со статистикой
 */
@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    /**
     * Поиск пользователей, которые совершили конвертацию USD на сумму больше лимита

     * @param limit лимит
     * @return коллекция пользователей
     */
    @ApiOperation("Поиск пользователей, которые совершили конвертацию USD на сумму больше лимита")
    @GetMapping("limit/{limit}")
    public Collection<User> getUsersByRequestLimit(@PathVariable Long limit){
        return statisticService.findUsersByLimit(limit);
    }

    /**
     * Поиск пользователей, которые суммарно совершили конвертаций USD на сумму больше лимита
     *
     * @param limit лимит
     * @return коллекция пользователей
     */
    @ApiOperation("Поиск пользователей, которые суммарно совершили конвертаций USD на сумму больше лимита")
    @GetMapping("/sum/{limit}")
    public Collection<User> getUsersByTotalSumLimit(@PathVariable Long limit){
        return statisticService.findUsersByTotalSumLimit(limit);
    }

    /**
     * Получает рейтинг направлений конвертаций по популярности
     * @return список направлений конвертаций и их количество
     */
    @ApiOperation("Рейтинг направлений конвертаций по популярности")
    @GetMapping("/rating")
    public List<String> exchangeRating(){
        return statisticService.exchangeRating();
    }

}
