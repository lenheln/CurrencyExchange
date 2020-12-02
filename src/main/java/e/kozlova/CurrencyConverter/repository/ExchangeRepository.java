package e.kozlova.CurrencyConverter.repository;

import e.kozlova.CurrencyConverter.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Обмен валюты
 */
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
