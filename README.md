# jacoco-coverage

DOM Parser for jacoco report. Currently is implemented extracting of the total instructions coverage from xml report.
The int number is returned.

### The library is hosted on Maven Central:

#### Maven
```xml
<dependency>
    <groupId>com.yopeso</groupId>
    <artifactId>jacoco-coverage</artifactId>
    <version>0.2.0</version>
</dependency>
```

#### Gradle
```groovy
compile 'com.yopeso:jacoco-coverage:0.2.0'
```


### How to use it:

#### In the Java code

```java
JacocoDomParser jacocoParser = new JacocoDomParser("src/main/resources/jacoco_report.xml");
int instructionsCoverage = jacocoParser.getInstructionCoverage();
```

#### Cmd - copying jar artifact from Maven Central and execute

```shell
> mvn dependency:copy -Dartifact=com.yopeso:jacoco-coverage:0.2.0:jar -DoutputDirectory=target
> java -jar target/jacoco-coverage-0.2.0.jar jacoco_spring_boot.xml
```

#### Gradle - using artifact jar end definig the task that executes it

build.gradle - snippet
```groovy
...
apply plugin: 'jacoco'
...
dependencies {
...
//extracting jacoco coverage - START
    compile 'com.yopeso:jacoco-coverage:0.2.0'
}
 
String jarPath
 
 
jacocoTestReport {
    reports {
        xml.enabled true
        html.enabled false
        xml.setDestination(new File('build/jacoco.xml'))
    }
}
 
task findJacocoCoverageJar {
    configurations.compile.each {
        if ('jacoco-coverage-0.2.0.jar'.equals(it.getName())) {
            jarPath = it.getAbsolutePath()
        }
    }
}
 
task execCoverage(type: JavaExec, dependsOn: ['findJacocoCoverageJar', 'jacocoTestReport', 'check']) {
    main "-jar"
    args = [jarPath, "build/jacoco.xml"]
}
//extracting jacoco coverage - END
...
```

cmd
```shell
> gradle execCoverage
```
