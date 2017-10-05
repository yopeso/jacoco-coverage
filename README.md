# jacoco-coverage

DOM Parser for jacoco report. Currently is implemented extracting of the total instructions coverage from xml report.

### The library is hosted on Maven Central:

#### Maven
```xml
<dependency>
    <groupId>com.yopeso</groupId>
    <artifactId>jacoco-coverage</artifactId>
    <version>0.1.0</version>
</dependency>
```

### How to use it:

#### In the code

```java
JacocoDomParser jacocoParser = new JacocoDomParser("src/main/resources/jacoco_report.xml");
int instructionsCoverage = jacocoParser.getInstructionCoverage();
```

#### Cmd

```shell
java -jar target/jacoco-coverage-0.1.0.jar src/main/resources/jacoco_report.xml
```
