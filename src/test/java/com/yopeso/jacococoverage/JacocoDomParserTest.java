package com.yopeso.jacococoverage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JacocoDomParserTest {

    private JacocoDomParser jacocoDomParser;
    private String xmlSpringBoot = "src/main/resources/jacoco_spring_boot.xml";
    private int coverageForJacocoSpringBoot = 33;

    @Before
    public void setUp() throws Exception {
        jacocoDomParser = new JacocoDomParser(xmlSpringBoot);
    }

    @Test
    public void getInstructionCoverage() throws Exception {
        assertEquals(coverageForJacocoSpringBoot, jacocoDomParser.getInstructionCoverage());
    }
}