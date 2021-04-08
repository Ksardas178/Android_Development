Цели
====

-   Ознакомиться с принципами работы adapter-based views;

-   Получить практические навыки разработки адаптеров для view.

Задачи
======

Задача 1. Знакомство с библиотекой (unit test)
----------------------------------------------

### Задание

Ознакомление со strict mode библиотеки, иллюстрация ее работы
unit-тестом.

### Решение

Библиотека, предоставляющая программный доступ к записям в формате
bibtex, имеет 2 режима работы: normal и strict. При этом в strict mode
введено ограничение на максимальное количество записей в памяти: не
более name.ank.lab4.BibConfig\#maxValid=20 одновременно. При попытке
извлечь 21-ую запись первая извлеченная запись становится недоступной.
Попытка доступа к ее полям приводит к исключениям. Данное ограничение
позволяет быстрее выявлять связанные с recycler view и адаптерами
ошибки.

Исходя из вышесказанного, приходим к следующему unit-тесту для проверки
работы strict mode:

![image](https://user-images.githubusercontent.com/43096732/114067912-01601800-98a6-11eb-89d1-b0dad3085109.png)

Рисунок 1 Strict mode unit test

В тесте создаем объект БД, переключаемся на режим strict, сохраняем
первую запись в first. Затем в цикле обращаемся 20 раз к нулевой записи
(превышаем лимит maxValid), после чего проверяем выбрасывание исключения
при попытке доступа к переменной first.

Тест shuffleFlag выглядит следующим образом:

![image](https://user-images.githubusercontent.com/43096732/114067968-0f159d80-98a6-11eb-9a43-10d24943903f.png)

Рисунок 2 Shuffle flag unit test

Так как по умолчанию флаг shuffle имеет значение true, создадим
config-объект, для которого значение флага будет установлено в false.
Далее создаются два экземпляра БД: mixed и notMixed. В цикле будем
создавать перемешанные базы данных и проверять ситуацию, когда поле
автора у первой записи отличается в обеих базах. Учитывая, что
количество записей в тестовом файле велико, проверка проходит успешно.

Собранный jar-файл находится по пути build/libs/biblib.jar.

Задача 2. Знакомство с recyclerView
-----------------------------------

### Задание

Разработать Android-приложение, которое выводит все записи из bibtex
файла на экран, используя предложенную библиотеку и RecyclerView.

Выбранная задача: однородный список (обычная сложность).

### Решение

В ходе решения разработан класс адаптера:

class Adapter(base: InputStream) :
RecyclerView.Adapter&lt;Adapter.ViewHolder&gt;() {\
\
private val reader = InputStreamReader(base)\
private val database = BibDatabase(reader)\
\
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
ViewHolder {\
val itemView = LayoutInflater.from(parent.*context*).\
inflate(R.layout.*item*, parent, false)\
return ViewHolder(itemView)\
}\
\
override fun onBindViewHolder(holder: ViewHolder, position: Int) {\
<span id="_Hlk68804192" class="anchor"></span>val currentItem =
database.getEntry(position % database.size())\
holder.author.*text* = "Author(s): " +
currentItem.getField(Keys.*AUTHOR*)\
holder.pages.*text* = "Pages: " + currentItem.getField(Keys.*PAGES*)\
holder.journal.*text* = "Journal: " +
currentItem.getField(Keys.*JOURNAL*)\
holder.title.*text* = "Title: " + currentItem.getField(Keys.*TITLE*)\
}\
\
override fun getItemCount() = database.size()

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {\
val author: TextView = itemView.author\
val pages: TextView = itemView.pages\
val title: TextView = itemView.title\
val journal: TextView = itemView.journal\
}\
}

Листинг 1 Adapter.kt

Класс данных для хранения записи о книге:

data class Item(val author: String, val pages: String,\
val title: String, val journal: String) {}

Листинг 2 Item.kt

MainActivity-класс имеет следующий вид:

class MainActivity : AppCompatActivity() {\
override fun onCreate(savedInstanceState: Bundle?) {\
super.onCreate(savedInstanceState)\
setContentView(R.layout.*activity\_main*)\
val exampleList = generateList(500)\
recycler\_view.*adapter* =
Adapter(*resources*.openRawResource(R.raw.*articles*))\
recycler\_view.*layoutManager* = LinearLayoutManager(this)\
recycler\_view.setHasFixedSize(true)\
}\
private fun generateList(size: Int): List&lt;Item&gt; {\
val list = ArrayList&lt;Item&gt;()\
for (i in 0 *until* size) {\
val item = Item( "Author","Item \$i", "title", "journal")\
list += item\
}\
return list\
}\
}

**Листинг 3 MainActivity.kt**

Файл разметки MainActivity:

&lt;?xml version="1.0" encoding="utf-8"?&gt;\
&lt;RelativeLayout\
xmlns:android="http://schemas.android.com/apk/res/android"\
xmlns:tools="http://schemas.android.com/tools"\
android:layout\_width="match\_parent"\
android:layout\_height="match\_parent"\
tools:context=".MainActivity"&gt;\
&lt;androidx.recyclerview.widget.RecyclerView\
android:id="@+id/recycler\_view"\
android:layout\_width="match\_parent"\
android:layout\_height="match\_parent"\
android:clipToPadding="false"\
android:padding="4dp"\
tools:listitem="@layout/item" /&gt;\
&lt;/RelativeLayout&gt;

**Листинг 4 main\_activity.xml**

Файл разметки элемента в RecyclerView:

&lt;?xml version="1.0" encoding="utf-8"?&gt;\
&lt;androidx.cardview.widget.CardView
xmlns:android="http://schemas.android.com/apk/res/android"\
android:layout\_width="match\_parent"\
android:layout\_height="wrap\_content"\
xmlns:app="http://schemas.android.com/apk/res-auto"\
android:layout\_margin="4dp"&gt;\
\
&lt;RelativeLayout\
android:layout\_width="match\_parent"\
android:layout\_height="match\_parent"&gt;\
\
&lt;TextView\
android:id="@+id/author"\
android:textStyle="bold"\
android:textColor="@color/black"\
android:layout\_width="match\_parent"\
android:layout\_height="wrap\_content"\
android:layout\_alignParentStart="true"\
android:layout\_marginEnd="8dp"\
android:text="@string/placeholder" /&gt;\
\
&lt;TextView\
android:id="@+id/title"\
android:layout\_width="match\_parent"\
android:layout\_height="wrap\_content"\
android:layout\_below="@+id/author"\
android:layout\_marginTop="5dp"\
android:layout\_marginEnd="8dp"\
android:text="@string/placeholder" /&gt;\
\
&lt;TextView\
android:id="@+id/pages"\
android:layout\_width="match\_parent"\
android:layout\_height="wrap\_content"\
android:layout\_below="@+id/title"\
android:layout\_marginTop="5dp"\
android:layout\_marginEnd="8dp"\
android:text="@string/placeholder" /&gt;\
\
&lt;TextView\
android:id="@+id/journal"\
android:layout\_width="match\_parent"\
android:layout\_height="wrap\_content"\
android:layout\_below="@+id/pages"\
android:layout\_marginTop="5dp"\
android:layout\_marginEnd="8dp"\
android:text="@string/placeholder" /&gt;\
&lt;/RelativeLayout&gt;\
&lt;/androidx.cardview.widget.CardView&gt;

**Листинг 5 item.xml**

Задача 3. Бесконечный список
----------------------------

### Задание

Создать бесконечный список из предыдущей задачи: после последнего
элемента повторить все записи, начиная с первой.

### Решение

Для создания бесконечного списка потребуется внести следующие изменения:

-   Заменить возвращаемое значение метода Item.getItemCount(): Int =
    Int.MAX\_VALUE

-   Удалить атрибут scrollbar в activity\_main

-   Проверить, что в методе database.getEntry реализовано деление по
    модулю:\
    val currentItem = database.getEntry(position % database.size())

Выводы
======

В ходе выполнения лабораторной работы исследована библиотека,
предоставляющая программный доступ к записям в формате bibtex, написаны
тесты для проверки ее корректной функциональности.

Во второй части работы изучены приемы работы с RecyclerView, паттерн
Adapter. Разработан класс адаптера со вложенным классом ViewHolder,
позволяющим установить данные из файла articles.bib в конкретные поля
View. Написан вывод записей из библиотеки в список прокрутки.

Решение третьей задачи предполагало незначительные изменения готового
кода с целью получить список с бесконечной прокруткой. Данная цель
успешно достигнута.
