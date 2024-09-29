# Описание
Приложение погоды - позволяющее пользователю найти местоположение и узнать в нём прогноз погоды. Сохраняет просмотренные данные для возможности повторного открытия при отсутствии интернета.

# Стэк
Compose, Jetpack Navigation, Room, Retrofit, Hilt, Clean architecture, MVVM.

# Тесты
### Unit-тесты
* [ForecastApiModel](app/src/test/java/com/gbl/weather_app/ForecastApiModelUnitTest.kt)
  * Тестирование корректного преобразования данных, поступающих с сервера, в принятую сущность погоды.
* [ForecastLocalDataSource](app/src/test/java/com/gbl/weather_app/ForecastLocalDataSourceUnitTest.kt)
  * Тестирование корректного получения прогноза погоды по id из базы данных и его возврат в виде сущности погоды.
* [LocationsRepositoryImpl](app/src/test/java/com/gbl/weather_app/LocationsRepositoryImplUnitTest.kt)
  * Тестирование выдачи сохранённого локально списка погоды если пришёл пустой поисковый запрос.
  * Тестирование выдачи сохранённого локально списка погоды если сервер отвечает очень долго (не менее 10 секунд).
  * Тестирование выдачи сохранённого локально списка погоды если запрос к серверу возвращает ошибку.
### UI-тесты
* [ForecastScreen](app/src/androidTest/java/com/gbl/weather_app/ui/ForecastScreenUITest.kt)
  * Тестирование корректности работы кнопки удаления.
* [SearchLocationsScreen](app/src/androidTest/java/com/gbl/weather_app/ui/SearchLocationsScreenUITest.kt)
  * Тестирование показа заглушки, если поисковый запрос и список местоположений пустые.
  * Тестирование работы кнопки очистки поискового запроса
### Integration-тесты
* [SearchLocations](app/src/androidTest/java/com/gbl/weather_app/integration/SearchLocationsIntegrationTest.kt)
  * Тестирование взаимодействия между экранами приложения, вью-моделями у них и use-case – поиск пользователем местоположения и получение погоды для конкретного места.




