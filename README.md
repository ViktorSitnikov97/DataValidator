# Проект "Валидатор данных"

### Tests and linter status:
[![Actions Status](https://github.com/ViktorSitnikov97/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-78/actions)[![example workflow](https://github.com/ViktorSitnikov97/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/ViktorSitnikov97/java-project-78/actions)[![Maintainability](https://api.codeclimate.com/v1/badges/a8aa1a1bec7629bffdf6/maintainability)](https://codeclimate.com/github/ViktorSitnikov97/java-project-78/maintainability)[![Test Coverage](https://api.codeclimate.com/v1/badges/a8aa1a1bec7629bffdf6/test_coverage)](https://codeclimate.com/github/ViktorSitnikov97/java-project-78/test_coverage)

## Описание
Валидатор данных - это библиотека, созданная для проверки корректности введенных данных. 
Поддерживается возможность валидация следующих объектов:
* String
* Integer
* Map
* Вложенные схемы валидации

## Требования
Иметь, либо установить:

> [Git installed](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
> 
> [Java](https://www.oracle.com/java/technologies/downloads/)
> 
> [Gradle](https://gradle.org/install/)

## Порядок подключения библиотеки

Склонируйте репозиторий локально:
```
git clone git@github.com:ViktorSitnikov97/java-project-78.git
make install
```
Создайте JAR-файл командой  `./gradlew jar`. Путь, по которому будет лежать файл: `"./build/libs"`. Скопируйте этот файл в свой проект в специально созданную директорию `libs` в корне проекта.
Укажите эту зависимость в своем конфигурационном файле:
* Для `Gradle-Kotlin` build system, добавьте в файл `build.gradle.kts`:
  
  ```  
  dependencies {
   implementation(files("libs/имя_файла.jar"))
  }
  ```
  
* Для `Gradle-Groovy` build system, добавьте в файл `build.gradle`:
  
  ```  
  dependencies {
    implementation files('libs/имя_файла.jar')
  }
  ```
  
* Для `Maven` build system, добавьте в файл `pom.xml` (итоговый JAR-файл должен называться `app-1.0-SNAPSHOT.jar` и находиться в папке `libs` в корне проекта):
  
  ``` 
      <dependencies>
        <dependency>
            <groupId>hexlet.code</groupId> 
            <artifactId>app</artifactId> 
            <version>1.0-SNAPSHOT</version> 
            <scope>system</scope>
            <systemPath>${project.basedir}/libs/app-1.0-SNAPSHOT.jar</systemPath>  <!-- Нужно указать абсолютный путь к подключенному JAR-файлу -->
        </dependency>
    </dependencies>
  ```
## Порядок использования библиотеки

* Создать объект `Validator v = new Validator()`;<br>
* Использовать один из трех методов для создания объекта валидации определённой схемы:<br>
>`var stringValidationObject = v.string()` - создает объект класса `StringSchema` для работы со строка `String`;<br>
>`var numberValidationObject = v.number()` - создает объект класса `NumberSchema` для работы с числами `Integer`;<br>
>`var mapValidationObject = v.map()` - создает объект класса `MapSchema` для работы с объектами типа `Map`;<br>
>`isValid(ваш_объект_валидации)` - метод, одинаковый для объектов всех классов, проверяет валидность переданных данных;<br>
* Для объектов раннее перечисленных классов использовать методы в качестве "флагов" для валидации значений:
 * Методы (флаги) для валидации строк:
>`stringValidationObject.required()` - устанавливает обязательность заполнения данных - пустая строка "" или null не могут быть переданы<br>
>`stringValidationObject.minLength(Integer ваше_число)` - устанавливает минимальную длину для проверяемой строки<br>
>`stringValidationObject.contains(String ваша_подстрока)` -  устанавливает подстроку для проверки ее наличия в объекте валидации<br>
 * Методы(флаги) для валидации чисел:<br>  
>`numberValidationObject.required()` - устанавливает обязательность заполнения данных -  null не может быть передан<br>
>`numberValidationObject.positive()` - устанавливает ограничение на знак числа - только положительные значения<br>
>`numberValidationObject.range(Integer нижняя_граница, Integer верхняя_граница)` - устанавливает ограничение на область допустимых значений числа - края границы включительно<br>
 * Методы (флаги) для валмдации обектов типа Map
>`mapValidationObject.required()` - устанавливает обязательность заполнения данных -  null не может быть передан<br>
>`mapValidationObject.sizeof(Integer ваш_размер)` - устанавливает ограничение на размер объекта типа Map - количество пар "ключ-значение" должно точно совпадать с установленным значением<br>
>`mapValidationObject.shape(Map<K, BaseSchema<V>> ваша_мапа_с_настроенными_схемами_проверки)` - метод позволяет описывать валидацию для значений каждого ключа объекта Map. В качестве аргумента методу передается объект типа Map, где значением может являться один из объектов классов-наследников класса BaseSchema<T> (StringSchema, NumberSchema, MapSchema), т.е. объект схемы валидации, а ключом - объект, соответсвующий "ключ + значение" значению, для которого будет произведена впоследствии проверка валидации.<br>
 * Ниже представлен пример работы данного метода:<br>
 
 ```java

 var v = new Validator();
 var schema = v.map();

 Map<String, BaseSchema<String>> schemas1 = new HashMap<>(); // создаем объект для наполнения схемами проверок
 schemas1.put("firstName", validator.string().required().minLength(5)); // добавляем проверки для строк по первому ключу
 schemas1.put("lastName", validator.string().required().minLength(9)); // добавляем проверки для строк по второму ключу

 schema.shape(schemas1); // загружаем конфигурацию проверок для схемы валидации

 Map<String, String> human1 = new HashMap<>(); // создаем обект Map, значения которого будем проверять на валидацию
 human1.put("firstName", "Django");
 human1.put("lastName", "Unchained");

 Assertions.assertTrue(schema.isValid(human1));

 Map<String, BaseSchema<String>> schemas2 = new HashMap<>();
 schemas2.put("firstName", validator.string().required().minLength(10));
 schemas2.put("lastName", validator.string().required().minLength(10));

 schema.shape(schemas2);

 Map<String, String> human2 = new HashMap<>();
 human1.put("firstName", "Quentin");
 human1.put("lastName", "Tarantino");

 Assertions.assertFalse(schema.isValid(human2));

 Map<String, BaseSchema<Integer>> schemas3 = new HashMap<>();
 schemas3.put("height", validator.number().required().positive().range(170, 180)); // добавляем проверки для целого числа по первому ключу
 schemas3.put("weight", validator.number().required().positive().range(70, 85)); // добавляем проверки для целого числа по второму ключу

 schema.shape(schemas3);

 Map<String, Integer> human3 = new HashMap<>();
 human3.put("height", 175);
 human3.put("weight", 75);

 Assertions.assertTrue(schema.isValid(human3));

```



