Родионов Алексей Алексеевич, БПИ208
### Домашнее задание №2

### Описание проекта
Библиотека для работы с ациклическими направленными графами (DAG)
Тесты покрывают 100% публичных методов основных классов.
Паттерн для сериализации/десериализации: "{letter};{ind};{x};{y};{child_indexes}", где:
*letter* - буква идентификатор (тип класса - p, o или s)
*ind* - индекс текущего элемента
*х* - координата позиции х
*y* - координата позиции y
*child_indexes* - список индексов потомков через запятую, если такие есть или "-" иначе.

### Классы
> #### Coord2D
> Класс математической точки. <br><br>
> Содержит поля x и y с ее координатами в пространстве

> #### BoundBox
> Класс ограничивающего прямоугольника. <br><br>
> Содержит информацию о побочной диагонали прямоугольника благодаря полям левого нижнего и 
> правого верхнего угла прямоугольника. У разных классов высчитывается по-разному.

> #### Point
> Класс физической точки. <br><br>
> Содержит позицию, совпадающую с координатами математической точки и ограничивающий прямоугольник bounds.
> Так как точка - вырожденный случай, углы прямоугольника совпадают с позицией точки в пространстве.

> > #### Origin
> Класс системы координат. Наследуется от класса Point <br><br>
> Ограничивающий прямоугольник системы - объединение всех прямоугольников его дочерних элементов.
> В случае, если дочерних элементов нет, метод получения прямоугольника выбрасывает ошибку (так как о том,
> что делать в этом случае в ТЗ ничего сказано не было).

> #### Space
> Класс мировой системы координат. Наследуется от Origin <br><br>
> Имеет все методы и поля системы координат и такую же логику.
> Теоретически, возможность добавления одного Space в другой есть, но корректность работы при этом 
> не гарантируется (так как об этом тоже ничего не было сказано и это не обрабатывалось)

> #### DAGUtils
> Класс утилиты для сериализации/десериализации графа.<br><br>
> В случае задания некорректной строки с сериализованным Space, корректная работа метода десериализации
> будет нарушена, так как она по ТЗ не предусматривает некорректный ввод. Аналогично в случае некорректной сериализации
