package e.kozlova.CurrencyConverter.service;

import e.kozlova.CurrencyConverter.entity.User;
import e.kozlova.CurrencyConverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Поиск пользователей, которые совершили конвертацию USD на сумму больше лимита

     * @param limit лимит
     * @return коллекция пользователей
     */
    public Collection<User> findUsersByLimit(Long limit){
        return userRepository.findUsersByLimit(limit);
    }

    /**
     * Поиск пользователей, которые суммарно совершили конвертаций USD на сумму больше лимита
     *
     * @param limit лимит суммы
     * @return коллекция пользователей
     */
    public Collection<User> findUsersByTotalSumLimit(Long limit){
        return userRepository.findUsersByTotalSumLimit(limit);
    }

    /**
     * Получает рейтинг направлений конвертаций по популярности
     * @return список направлений конвертаций и их количество
     */
    public List<String> exchangeRating(){
        return userRepository.exchangeRating();
    }
}
