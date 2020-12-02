Свервис REST API конвертации валют и сбора статистики

Для запуска сервиса воспользуйтесь командой
mvn exec:java -Dexec.mainClass="e.kozlova.CurrencyConverter.Application"

Используемый стек:
Java 11
Maven
Spring Boot 2.4.0
Spring Data
Spring Web
Hibernate-Validator
Jackson-Databind
Lombok
H2-in memory data base
Swagger
В качестве внешнего API используется https://api.exchangerate.host

Описание Api

/exchange
Для конвертации валюты необходимо отправить POST запрос на endPoint /exchange
В параметрах запроса указать идентификатор текущего пользователя userId
В теле запроса передать JSON с указанием валюты из которой и в котороую производится конвертация, а также сумму.

Пример запроса:
POST "http://localhost:8080/exchange?userId=2"
JSON {\"amount\":1000,\"inputCurrency\":\"RUB\",\"outputCurrency\":\"EUR\"}

Пример ответа:
10.914

/stats
Поиск пользователей, которые совершили конвертацию USD на сумму больше лимита.
Для получения статистики необходимо отправить GET запрос на endPoint /stats/limit
В параметрах запроса указать сумму лимита.


Пример запроса:
GET "http://localhost:8080/stats/limit/70000"

Пример ответа:
[
  {
    "id": 1,
    "login": "user1"
  },
  {
    "id": 2,
    "login": "user2"
  }
]

Поиск пользователей, которые суммарно совершили конвертаций USD на сумму больше лимита.
Для получения статистики необходимо отправить GET запрос на endPoint /stats/sum
В параметрах запроса указать сумму лимита.

Пример запроса:
GET "http://localhost:8080/stats/sum/150000"

Пример ответа:
[
  {
    "id": 2,
    "login": "user2"
  }
]


Рейтинг направлений конвертаций по популярности
Для получения статистики необходимо отправить GET запрос на endPoint /stats/rating

Пример запроса:
GET "http://localhost:8080/stats/rating"

Пример ответа:
[
  "USD,RUB,4",
  "EUR,USD,3",
  "USD,EUR,2",
  "RUB,EUR,2",
  "GBP,BYN,1",
  "RUB,CHF,1",
  "CHF,CNY,1",
  "JPY,CNY,1"
]