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
* Для объектов раннее перечисленных классов использовать методы в качестве "флагов" для валидации значений
  



