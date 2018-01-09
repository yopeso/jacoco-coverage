package com.yopeso.jacococoverage.mavenplugin;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JacocoCoverageMojoTest {
    private JacocoCoverageMojo jacocoCoverageMojo;
    private String xmlSpringBoot = "src/test/resources/jacoco_spring_boot.xml";
    private String resultFilePath = "src/test/resources/result.txt";
    private int coverageForJacocoSpringBoot = 33;

    @Before
    public void setUp() {
        jacocoCoverageMojo = new JacocoCoverageMojo();
        jacocoCoverageMojo.setCoverageReport(xmlSpringBoot);
        jacocoCoverageMojo.setCoverageResult(resultFilePath);
    }

    @Test
    public void execute() throws Exception {
        jacocoCoverageMojo.execute();

        File resultFile = null;
        FileReader fileReader;
        BufferedReader bR = null;
        int resultFromFile = -1;
        try {
            resultFile = new File(resultFilePath);
            fileReader = new FileReader(resultFile);
            bR = new BufferedReader(fileReader);
            resultFromFile = Integer.parseInt(bR.readLine());
        } catch (Exception e) {
            System.out.println("Some error in testing the mojo. " + e);
        } finally {
            if (bR != null) {
                try {
                    resultFile.deleteOnExit();
                    bR.close();
                } catch (IOException e) {
                    System.out.println("Closing error.");
                }
            }
        }
        assertEquals(coverageForJacocoSpringBoot, resultFromFile);

    }

}