# Introduction 
Веб приложение для поиска и бронирования автомобилей.

# Getting Started
Сборка сервиса производится выполненением batch файла build.bat(Windows, необходим Maven Plugin установленный на компьютере).
За настройку каждого проекта отвечает файл application.properties.
Параметр server.port определяет порт для запуска сервиса. Используется встроеный сервлет контейнер, необходимый приложению.
В архиве images находятся картинки автомобилей, которые можно загрузить в "C:/temp/" чтобы отображались превью и изображения каждого автомобиля.


Чтобы зайти на главную страницу после запуска в браузере ввести host:port/, например localhost:8080/

Регистрация и авторизация реализована с использованием Spring Security.

Используется Liquibase и liquibase.properties для его настройки. Патчить базу через maven-plugins-liquibase:update. Автоматически база не патчится при старте, это настраивается в конфиге плагина в pom.xml.

В проекте подключен swagger, чтобы можно было вызывать api через интерфейс. Для пользования сваггера нужно зайти по адресу - http://localhost:8080/swagger-ui.html#/ (если порт 8080), выбрать из списка "api list" запрос, нажать на кнопку "try it out", ввести нужные параметры и кликнуть на кнопку execute для отправки запроса. В результате сваггер ниже выведет ответ от сервера (url, code, response body+response headers), переводенный текст располагается в response body.

# About views:
Переиспользуемые фрагменты страниц находятся в файле fragmets.html.
К ним в частности относятся навигационная панель (navbar) и футер (footer).

Для встраивания их в свою страницу необходимо:

Убедиться, что открывающий тег <html> выглядит следующим образом:
<html xmlns:th="https://www.thymeleaf.org" xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
Подключить бутстрап стили, размести cdn между тегами <head>:

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
Сразу после открывающего тега <body> разместить:

Перед закрывающим тегом </body> включить:
Примеры можно посмотреть в cars.html, login.html.

Личный кабинет пользователя/Администратора
Чтобы добавить меню на страницу нужно под навбаром добавить:
для пользователя:
<div th:replace="fragments :: nav-menu-user"></div>
для администратора:
<div th:replace="fragments :: nav-menu-admin"></div>

# Contribute
