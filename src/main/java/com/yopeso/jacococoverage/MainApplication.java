package com.yopeso.jacococoverage;

/**
 * Main class for jacoco-coverage.
 * Usage like
 * > java -jar target/jacoco-coverage-0.1.0.jar src/main/resources/jacoco_spring_boot.xml
 */
public class MainApplication {
    public static void main(String[] args) {
        JacocoDomParser jacocoParser = new JacocoDomParser(args[0]);
        System.out.println(jacocoParser.getInstructionCoverage());
    }
}