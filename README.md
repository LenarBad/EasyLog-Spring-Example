<p align="center">
  <a href="https://lenar.io/easylog/">EasyLog Site</a> | 
  <a href="https://github.com/LenarBad/easylog">Source Code</a>
  <br>
  <a href="https://maven-badges.herokuapp.com/maven-central/io.lenar/easy-log">
    <image src="https://img.shields.io/maven-central/v/io.lenar/easy-log.svg" alt="Maven Central">
  </a>

</p>

# Easy Log for Spring projects - example
    
This is the example that demonstrates how to use **EasyLog** for Spring projects

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>io.lenar.examples</groupId>
    <artifactId>easy-log-spring-example</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.lenar</groupId>
            <artifactId>easy-log</artifactId>
            <version>1.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
            <version>2.0.3.RELEASE</version>
        </dependency>
    </dependencies>
</project>
``` 

If your project has another parent project (that has to be a Spring project as well) you might not need <code>spring-boot-starter-aop</code> - just check.

## Extend EasyLogger

```java
@Component
public class MyLogger extends EasyLogger {
}
```

## LogIt annotation

### LogIt annotation without parameters

```java
    @LogIt
    public Universe bigBang(int numberOfStars, int numberOfBlackHoles) {
        blackHoles = IntStream.range(0, numberOfBlackHoles).boxed()
                .map(item -> new BlackHole(randomName("BlackHole-")))
        ...
    }
```

Log output will look like this

```json
2018-06-29 14:40:47.139  INFO 12128 --- [           main] UneasyLogger                             : 
-> public Universe Universe.bigBang(int numberOfStars, int numberOfBlackHoles)
"numberOfStars": 3,
"numberOfBlackHoles": 3

2018-06-29 14:40:47.141  INFO 12128 --- [           main] UneasyLogger                             : 
Execution/Response time:  1ms
<- Universe Universe.bigBang(int numberOfStars, int numberOfBlackHoles)
{
  "stars": [
    {
      "name": "Star-095f0bae-5641-4222-a0ce-bc16ea8915c7",
      "type": "RED_GIANT",
      "planets": []
    },
    {
      "name": "Star-2d2f8a18-3f4e-412b-8c80-c5a888136b20",
      "type": "WHITE_DWARF",
      "planets": [
        {
          "name": "Planet-07fda21d-dd47-47ef-b5e0-77c7e0ad6a4f",
          "haveSatellites": true
        },
        {
          "name": "Planet-543a6616-e7f6-437d-883c-dd6612a69707",
          "haveSatellites": false
        }
      ]
    },
    {
      "name": "Star-bbd54023-6776-4f5d-b5a6-7f7bfa961b21",
      "type": "SUPERGIANT",
      "planets": [
        {
          "name": "Planet-cba33a76-a2b8-4994-a7d9-1e5f487f1b4c",
          "haveSatellites": false
        }
      ]
    }
  ],
  "blackHoles": [
    {
      "name": "BlackHole-a6c1c065-ec95-4f75-aa3e-fb42479696f0"
    },
    {
      "name": "BlackHole-3a462579-d83d-492e-9ddf-7bbe2fcd2b43"
    },
    {
      "name": "BlackHole-d204dba0-9655-4a97-a144-a8de9ddaadf8"
    }
  ],
  "dateOfCreation": "Jun 29, 2018 2:40:47 PM"
}
```

### LogIt annotation with parameters - example

NOTE: Make sure you have set the correct logging level. In the example it's <code>DEBUG</code> (see <code>application.properties</code>)

```java
    @LogIt(label = "DEBUG BIG BANG ISSUE",
            level = Level.DEBUG,
            ignoreParameters = {"numberOfBlackHoles"},
            maskFields = {"type"})
    public Universe bigBang(int numberOfStars, int numberOfBlackHoles) {
        blackHoles = IntStream.range(0, numberOfBlackHoles).boxed()
                .map(item -> new BlackHole(randomName("BlackHole-")))
        ...
    }
```

Log output

```json
2018-06-29 14:49:51.389 DEBUG 15704 --- [           main] UneasyLogger                             : 
DEBUG BIG BANG ISSUE
-> public Universe Universe.bigBang(int numberOfStars, int numberOfBlackHoles<NOT_LOGGED>)
"numberOfStars": 3

2018-06-29 14:49:51.467 DEBUG 15704 --- [           main] UneasyLogger                             : 
Execution/Response time:  36ms
DEBUG BIG BANG ISSUE
<- Universe Universe.bigBang(int numberOfStars, int numberOfBlackHoles<NOT_LOGGED>)
{
  "stars": [
    {
      "planets": [
        {
          "haveSatellites": false,
          "name": "Planet-c562aa9d-e021-4357-b5fa-ce93db8e1e06"
        }
      ],
      "name": "Star-d3290d9c-eefc-44e5-b2fc-c1273b37b0bf",
      "type": "XXXMASKEDXXX"
    },
    {
      "planets": [
        {
          "haveSatellites": true,
          "name": "Planet-0313f3ea-4285-44b0-99b0-8db9566957bb"
        },
        {
          "haveSatellites": true,
          "name": "Planet-79d893c6-74e2-4293-abae-60eb2d42b7ee"
        }
      ],
      "name": "Star-6240ba7a-3020-4452-b8e5-fc3756cf354b",
      "type": "XXXMASKEDXXX"
    },
    {
      "planets": [],
      "name": "Star-6250709e-0430-46c2-80b9-354516f06fa7",
      "type": "XXXMASKEDXXX"
    }
  ],
  "blackHoles": [
    {
      "name": "BlackHole-b68574ef-8660-4195-9cd0-879b9701d126"
    },
    {
      "name": "BlackHole-e3c20af0-75b1-401b-a5a6-c9b5062f4064"
    },
    {
      "name": "BlackHole-cabce906-103e-4c2b-a423-07a8461b393e"
    }
  ],
  "dateOfCreation": "Jun 29, 2018 2:49:51 PM"
}
```

