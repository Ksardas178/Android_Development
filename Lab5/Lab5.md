Цели
====

-   Ознакомиться с принципами и получить практические навыки разработки
    UI тестов для Android-приложений.

Задача 1.  Простейший UI тест
-----------------------------

### Задание

Ознакомиться со Espresso Framework и разработать приложение, в котором
есть одна кнопка и текстовое поле. При (первом) нажатии на кнопку текст
на ней должен меняться.

Написать Espresso-тест, проверяющий, что при повороте экрана содержимое
текстового поля сохраняется, а надпись на кнопке сбрасывается в исходное
состояние.

### Решение

В ходе решения разработан следующий код MainActivity:

![](media/image1.png){width="6.157109580052493in"
height="2.7503838582677167in"}

Рисунок 1 MainActivity.kt

Каждое нажатие на кнопку увеличивает значение счетчика н

Файл разметки выглядит следующим образом:

![](media/image2.png){width="4.667318460192476in"
height="7.698990594925634in"}

Рисунок 2 activity\_main.xml

Также написан класс тестов:

![](media/image3.png){width="6.496527777777778in"
height="5.526388888888889in"}

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

![](media/image4.png){width="6.496527777777778in"
height="4.606944444444444in"}

Рисунок 4 startActivityTest

Для данного теста в коде сформирована последовательность действий,
обеспечивающая переход между Activity внутри приложения. При смене
Activity проверяется соответствие реальной Activity ожиданиям.

-   Тестирование навигации по клавише back, проверка глубины backstack
    (в т. ч. корректного завершения приложения):

![](media/image5.png){width="5.771639326334208in"
height="7.125994094488189in"}

Рисунок 5 backStackTest

В приведенном тесте формируется последовательность взаимодействий с
приложением с целью проверки глубины backstack-а. Для этого произведены
множественные (в т. ч. дублирующиеся) переходы между Activity, после
чего воспроизводятся последовательные нажатия на кнопку back и проверка
отображаемых Activity. По последнему вызову pressBack() производится
проверка завершения приложения.

Также написана вспомогательная функция для проверки нахождения в
конкретной Activity:

![](media/image6.png){width="6.496527777777778in"
height="2.1215277777777777in"}

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