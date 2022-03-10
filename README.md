# Target monkey project
### Что это за проект
Данный проект должен позволить маркетологам и клиентам находить друг-друга и оперативно получать лиды или заказы.

###Как это запустить
Для запуска проекта нужны 2 команды:
+ `mvn compile package`
+ `docker up`

### Как это работает
Этот сервис использует JWT аутентификацию, а значит для его использования нужно получить JWT токен.

Использование сервиса:
+ Сервис авторизации и получение JWT токена:
  + `http://localhost:8082/api/v1/registration/create-customer` регистрация в Postman
  + `http://localhost:8082/api/v1/auth/login` авторизация и получения JWT токена в Postman

    Подробнее о формате JSON: http://localhost:8082/swagger-ui/index.html


+ Сервис пользователя или админа.
В этой программе существет 2 типа доступа Admin и Customer. При регистрации пользователь получает уровень доступа Customer.
Это значит, что управлять пользователь может только своими ресурсами и не может получить доступ к данным другого пользователя.
Пользователь может изменить свои учетные данные, создать, изменить или удалить свою компанию.
Что бы всё работало корректно необходимо добавить в header запроса свои данные:

  + `Authorization: Bearer_exampleJwtToken`
  + `Username: exampleUserName`

Теперь можно отправлять запросы к сервису пользователя.
Подробнее о запросах и JSON: http://localhost:8081/swagger-ui/index.html

*В программе могут быть и скорее всего есть ошибки, которые я исправляю. Огромное спасибо!!!*



