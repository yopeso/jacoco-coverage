package com.yopeso.jacococoverage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

/**
 * DOM Parser for jacoco report.
 * Used for extract the instructions coverage.
 */

public class JacocoDomParser {
    private static final String REPORT_NAME = "report";
    public static final String COUNTER_NAME = "counter";
    public static final String INSTRUCTION_NAME = "INSTRUCTION";
    public static final String TYPE_NAME = "type";
    public static final String COVERED_NAME = "covered";
    public static final String MISSED_NAME = "missed";

    private File xmlFile;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    //here the result
    private int instructionsCoverage = -1;

    /**
     * Constructor that creates DOM parser, parse the xml and extract the instruction coverage.
     *
     * @param xmlFilePath path for the xml report file
     */
    public JacocoDomParser(String xmlFilePath) {
        try {
            xmlFile = new File(xmlFilePath);
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            ignoreDtdReport(documentBuilderFactory);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();
            //nodes for report
            NodeList nodes = document.getElementsByTagName(REPORT_NAME).item(0).getChildNodes();
            Node currentNode = null;
            Element currentElement = null;
            for (int i = 0; i < nodes.getLength(); i++) {
                currentNode = nodes.item(i);
                //counter node
                if (COUNTER_NAME.equals(currentNode.getNodeName()) && currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    currentElement = (Element) currentNode;
                    //type == instruction
                    if (INSTRUCTION_NAME.equals(currentElement.getAttribute(TYPE_NAME))) {
                        break;
                    }
                }
            }
            int coveredInstructions = attributeToInt(currentElement.getAttribute(COVERED_NAME));
            int missedInstructions = attributeToInt(currentElement.getAttribute(MISSED_NAME));
            instructionsCoverage = coveredInstructions * 100 / (coveredInstructions + missedInstructions);
        } catch (Exception e) { //in case of error
            instructionsCoverage = -1;
            e.printStackTrace();
        }
    }

    /**
     * Returns the instruction coverage for the report.
     *
     * @return instruction coverage or -1 when error occurs
     */
    public int getInstructionCoverage() {
        return instructionsCoverage;
    }

    private void ignoreDtdReport(DocumentBuilderFactory dbf) throws ParserConfigurationException {

        dbf.setValidating(false);
        dbf.setNamespaceAware(true);
        dbf.setFeature("http://xml.org/sax/features/namespaces", false);
        dbf.setFeature("http://xml.org/sax/features/validation", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    }

    private int attributeToInt(String attributeValue) {
        return Integer.parseInt(attributeValue);
    }
}
