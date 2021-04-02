Цели
====

-   Ознакомиться с жизненным циклом Activity;

-   Изучить основные возможности и свойства alternative resources.

Задачи
======

Задача 1. Activity
------------------

### Задание

Демонстрация жизненного цикла Activity на любом нетривиальном примере.

В качестве демонстрационного проекта взято приложение из урока по
ссылке:
<https://classroom.udacity.com/courses/ud9012/lessons/e487c600-ed68-4576-a35a-12f211cf032e/concepts/1732d980-5171-4d4c-beae-c569c41c5684>

Добавлено логирование смены состояний устройства:

![image](https://user-images.githubusercontent.com/43096732/113398955-17527200-93a8-11eb-89e0-2cc294b984ab.png)

Порядок воздействий:

-   открытие приложения,

-   имитация звонка на устройство,

-   ответ на входящий вызов,

-   выход из разговора в приложение,

-   закрытие приложения.

Полученные сообщения в логе:

![image](https://user-images.githubusercontent.com/43096732/113398975-1e798000-93a8-11eb-835a-89e399884eba.png)

Как мы видим, при запуске приложения вызваны методы onCreate(),
onStart() и onResume(). При ответе на входящий звонок вызываются методы
onPause(), onStop() и onSaveInstanceState(), затем (по завершении
звонка) приложение возвращается к работе: вызываются методы onRestart(),
onStart() и onResume(). При выходе из приложения последовательно
вызываются методы onPause(), onStop(), onDestroy().

Задача 2. Alternative Resources
-------------------------------

### Задание

Демонстрация работы альтернативного ресурса (Keyboard availability) на
примере.

Квалификатор доступности клавиатуры имеет три возможных состояния:

-   keysexposed – используется, когда имеется доступная клавиатура:

    -   Реализованная программно клавиатура и/или ее аппаратный аналог
        (доступный пользователю на текущий момент);

-   keyshidden – используется, когда на устройстве не установлена
    программно реализованная клавиатура, и аппаратная клавиатура в
    текущий момент недоступна (скрыта);

-   keyssoft – на устройстве доступна программно реализованная
    клавиатура (неважно, открыта или нет в данный момент).

Таким образом, данный квалификатор может быть использован для выведения
подсказок пользователю о том, что на текущий момент у него недоступна
физическая клавиатура (keyshidden), а также для скрытия программно
реализованной клавиатуры при подключении аппаратного аналога.

Задача 3. Best-matching resource
--------------------------------

### Задание

Объяснить, какой ресурс будет выбран для заданного набора альтернативных
ресурсов. Обосновать ответ.

Вариант задания: 11.

Конфигурация устройства:

-   LOCALE\_LANG: fr

-   LOCALE\_REGION: rFR

-   SCREEN\_SIZE: small

-   SCREEN\_ASPECT: long

-   ROUND\_SCREEN: notround

-   ORIENTATION: land

-   UI\_MODE: desk

-   NIGHT\_MODE: notnight

-   PIXEL\_DENSITY: mdpi

-   TOUCH: notouch

-   PRIMARY\_INPUT: 12key

-   NAV\_KEYS: nonav

-   PLATFORM\_VER: v25

Конфигурация ресурсов:

-   (default)

-   en-notlong-tvdpi-notouch-dpad-v27

-   small-appliance

-   rCA-mdpi

-   en

-   en-rCA-normal-long-port-vrheadset-v25

-   en-rCA-xlarge-notlong-land-vrheadset-12key-nonav

-   notlong-night-v25

-   rCA-long-finger-trackball

-   small-notlong-hdpi

-   fr-appliance-finger-12key-wheel

Рассмотрим порядок выбора альтернативного ресурса в системе Андроид:

![image](https://user-images.githubusercontent.com/43096732/113399004-2802e800-93a8-11eb-8ca7-235c542ba1ab.png)

Сначала система вычеркивает файлы ресурсов, противоречащие конфигурации
устройства (кроме screen pixel density):

-   (default)

-   ~~en-notlong-tvdpi-notouch-dpad-v27~~ (en|fr)

-   ~~small-appliance~~ (appliance|desk)

-   ~~rCA-mdpi~~ (rCA|rFR)

-   ~~en~~ (en|fr)

-   ~~en-rCA-normal-long-port-vrheadset-v25~~ (en|fr)

-   ~~en-rCA-xlarge-notlong-land-vrheadset-12key-nonav~~ (en|fr)

-   ~~notlong-night-v25~~ (notlong|long)

-   ~~rCA-long-finger-trackball~~ (rCA|rFR)

-   ~~small-notlong-hdpi~~ (notlong|long)

-   ~~fr-appliance-finger-12key-wheel~~ (nonav|wheel)

После вычеркивания неподходящих ресурсов будет выбрана конфигурация по
умолчанию.

Задача 4. Сохранение состояниЯ Activity
---------------------------------------

### Задание

Найти и исправить ошибки в предоставленном приложении.

Исправленный код (переписаны методы onCreate(), onStop(),
onSaveInstanceState() и onRestoreInstanceState() для сохранения значения
счетчика):

![image](https://user-images.githubusercontent.com/43096732/113399019-2fc28c80-93a8-11eb-9f4f-d5648c37e55a.png)

Выводы
======

В данной лабораторной работе рассмотрены основные приемы сохранения
состояния Activity, разобран жизненный цикл приложения, проработан
алгоритм поиска best match ресурсов для приложения, а также рассмотрены
возможности применения альтернативного ресурса (по варианту задания).
