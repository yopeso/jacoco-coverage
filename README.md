# jacoco-coverage maven plugin

Maven plugin that extracts the total instructions coverage from jacoco xml report and put the int result into file.
*Mojo* class is used for define the plugin goal.

### The library is hosted on Maven Central:

#### Maven
```xml
<dependency>
    <groupId>com.yopeso</groupId>
    <artifactId>jacococoverage-maven-plugin</artifactId>
    <version>0.3.0</version>
</dependency>
```

#### Gradle
```groovy
compile 'com.yopeso:jacococoverage-maven-plugin:0.3.0'
```

### How to use it:

#### Cmd

```shell
> mvn com.yopeso:jacococoverage-maven-plugin:0.3.0:coverage -Dcoverage.report=target/site/jacoco/jacoco.xml -Dcoverage.result=target/site/jacoco/coverage_result.txt
```


*coverage:* the goal of the plugin defined in the *Mojo* class  
*Dcoverage.report:* path of the jacoco xml report   
*Dcoverage.result:* path of the file where will be written the jacoco coverage int number
