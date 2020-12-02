package e.kozlova.CurrencyConverter.repository;

import e.kozlova.CurrencyConverter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Поиск пользователей, которые совершили конвертацию USD на сумму больше лимита
     *
     * @param limit лимит по операции
     * @return список пользователей
     */
    @Query("SELECT DISTINCT e.user " +
            "FROM Exchange e " +
            "WHERE e.amount > :limit AND e.inputCurrency = 'USD'")
    public Collection<User> findUsersByLimit(@Param("limit") Long limit);

    /**
     * Поиск пользователей, которые суммарно совершили конвертаций USD на сумму больше лимита
     *
     * @param limit лимит по операции
     * @return список пользователей
     */
    @Query("SELECT e.user " +
            "FROM Exchange e " +
            "WHERE e.inputCurrency = 'USD' " +
            "GROUP BY e.user " +
            "HAVING SUM(e.amount) > :limit")
    public Collection<User> findUsersByTotalSumLimit(@Param("limit") Long limit);


    /**
     * Получает рейтинг направлений конвертаций по популярности
     *
     * @return список направлений конвертаций и их количество
     */
    @Query("SELECT e.inputCurrency, e.outputCurrency , COUNT(e.id) " +
            "FROM Exchange e " +
            "GROUP BY e.inputCurrency, e.outputCurrency " +
            "ORDER BY COUNT(e.id) DESC")
    public List<String> exchangeRating();
}
