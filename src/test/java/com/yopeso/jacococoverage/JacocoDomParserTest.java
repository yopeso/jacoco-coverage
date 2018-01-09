package com.yopeso.jacococoverage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JacocoDomParserTest {

    private JacocoDomParser jacocoDomParser;
    private String xmlSpringBoot = "src/test/resources/jacoco_spring_boot.xml";
    private int coverageForJacocoSpringBoot = 33;

    @Before
    public void setUp() {
        jacocoDomParser = new JacocoDomParser(xmlSpringBoot);
    }

    @Test
    public void getInstructionCoverage() {
        assertEquals(coverageForJacocoSpringBoot, jacocoDomParser.getInstructionCoverage());
    }
}