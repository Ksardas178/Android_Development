Цели
====

-   Ознакомиться с принципами и получить практические навыки разработки
    UI тестов для Android-приложений.

Задача 1.  Простейший UI тест
-----------------------------

### Задание

Ознакомиться с Espresso Framework и разработать приложение, в котором
есть одна кнопка и текстовое поле. При (первом) нажатии на кнопку текст
на ней должен меняться.

Написать Espresso-тест, проверяющий, что при повороте экрана содержимое
текстового поля сохраняется, а надпись на кнопке сбрасывается в исходное
состояние.

### Решение

В ходе решения разработан следующий код MainActivity:

![image](https://user-images.githubusercontent.com/43096732/114264418-8a389a00-99f3-11eb-84a0-a4c9c6a9932f.png)

Рисунок 1 MainActivity.kt

Каждое нажатие на кнопку увеличивает значение счетчика н

Файл разметки выглядит следующим образом:

![image](https://user-images.githubusercontent.com/43096732/114264411-81e05f00-99f3-11eb-8a91-4f425af55d7c.png)

Рисунок 2 activity\_main.xml

Также написан класс тестов:

![image](https://user-images.githubusercontent.com/43096732/114264404-7725ca00-99f3-11eb-9437-3748ec650538.png)

Рисунок 3 MyTest.kt

При тестировании использовано правило для запуска приложения.
Непосредственно в onStateChangeTest() привязываем переменные к элементам
интерфейса и последовательно производим необходимые воздействия. Перед
проверкой содержимого полей после поворота экрана делается задержка,
необходимая для восстановления UI.

Задача 2. Тестирование навигации
--------------------------------

### Задание

Взять приложение из Лаб №3 о навигации (любое из решений). Написать UI
тесты, проверяющие навигацию между 4-мя исходными Activity/Fragment
(1-2-3-About). Описать, что проверяет каждый тест.

### Решение

В ходе решения были добавлены следующие тесты:

-   Тестирование навигации по кнопкам внутри приложения:

![image](https://user-images.githubusercontent.com/43096732/114264424-93296b80-99f3-11eb-8b8a-49dd5e819e8b.png)

Рисунок 4 startActivityTest

Для данного теста в коде сформирована последовательность действий,
обеспечивающая переход между Activity внутри приложения. При смене
Activity проверяется соответствие реальной Activity ожиданиям.

-   Тестирование навигации по клавише back, проверка глубины backstack
    (в т. ч. корректного завершения приложения):

![image](https://user-images.githubusercontent.com/43096732/114264427-97ee1f80-99f3-11eb-86d8-163573ebc7ef.png)

Рисунок 5 backStackTest

В приведенном тесте формируется последовательность взаимодействий с
приложением с целью проверки глубины backstack-а. Для этого произведены
множественные (в т. ч. дублирующиеся) переходы между Activity, после
чего воспроизводятся последовательные нажатия на кнопку back и проверка
отображаемых Activity. По последнему вызову pressBack() производится
проверка завершения приложения.

Также написана вспомогательная функция для проверки нахождения в
конкретной Activity:

![image](https://user-images.githubusercontent.com/43096732/114264429-9cb2d380-99f3-11eb-9efb-baaffb3e2271.png)

Рисунок 6 checkView function

Выводы
======

В ходе выполнения лабораторной работы произведено ознакомление с
принципами разработки UI-тестов для Android-приложений и получены
практические навыки в данном направлении. Исследованы и опробованы
основные компоненты и конструкции фреймворка Espresso для создания
UI-тестов (ViewActions, ViewMatches). Изучены объекты классов
ActivityScenario, позволяющие запускать Activity и выполнять различные
манипуляции над ними, и ActivityScenarioRule, являющиеся обёрткой над
ActivityScenario.

Кроме перечисленного, установлено, что для достижения большей
стабильности UI-тестов необходимо отключение анимации на используемых
для тестирования устройствах.
