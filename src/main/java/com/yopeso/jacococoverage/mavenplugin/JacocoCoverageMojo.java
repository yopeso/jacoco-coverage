package com.yopeso.jacococoverage.mavenplugin;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.yopeso.jacococoverage.JacocoDomParser;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Mojo class for generate file with extracted value form jacoco xml report.
 * Usage like
 * mvn com.yopeso:jacococoverage-maven-plugin:0.3.0-SNAPSHOT:coverage -Dcoverage.report=jacoco_spring_boot.xml -Dcoverage.result=mvnres.txt
 * In case of report processing errors -1 may be written into the result file.
 */
@Mojo(name = "coverage", requiresProject = false)
public class JacocoCoverageMojo extends AbstractMojo {

    //path for the jacoxo.xml report
    @Parameter(property = "coverage.report")
    private String coverageReport;

    //path for the result file
    @Parameter(property = "coverage.result")
    private String coverageResult;


    /**
     * Creates file with the instructions coverage number for the jacoco report.
     *
     * @throws MojoExecutionException mojo exception if occurs
     *                                not handled processing report error or creating result file error.
     */
    public void execute()
            throws MojoExecutionException {
        File resultFile;
        FileWriter fileWriter = null;
        try {
            final JacocoDomParser jacocoParser = new JacocoDomParser(coverageReport);
            resultFile = new File(coverageResult);
            fileWriter = new FileWriter(resultFile);
            fileWriter.write(Integer.toString(jacocoParser.getInstructionCoverage()));
        } catch (Exception e) {
            final String errorStr = "Error processing the report " + coverageReport + " or creating the file " + coverageResult;
            getLog().error(errorStr);
            throw new MojoExecutionException(errorStr, e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    getLog().error("Error closing the file " + coverageResult);
                }
            }
        }
        getLog().info("Created result file " + coverageResult + " for report " + coverageReport);
    }

    /**
     * Setter for coverageReport, needed for te current version of the junit test.
     *
     * @param coverageReport path for jacoco report xml
     */
    public void setCoverageReport(String coverageReport) {
        this.coverageReport = coverageReport;
    }


    /**
     * Setter for coverageResult, needed for te current version of the junit test.
     *
     * @param coverageResult path for result file where will be put coverage
     */
    public void setCoverageResult(String coverageResult) {
        this.coverageResult = coverageResult;
    }
}

