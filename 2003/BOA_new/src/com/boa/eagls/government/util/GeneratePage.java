package com.boa.eagls.government.util;

import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

/**
 * GeneratePage is the class that is used by the loadTemplate custom tog to render
 * the HTML page by transforming the XML file using the XSLT transformation. Both files
 * are specified in the custom tag, as well as the filtering criteria. The main reason for
 * using a XSLT transformation is to filter the HTML blocks based upon the filtering criteria
 *
 * @author Viorel Solomon
 */
public class GeneratePage
{
    private String contextPath;
    private String xmlFile;
    private String xsltFile;
    private String filterName;
    private String filterValue;

    /**
     * Constructs the page generator
     *
     * @param xmlFile The name of the XML file containing the HTML blocks
     * @param xsltFile The XSLT transformation that generates the HTML page
     * @param filterName The name of filter used as the criteria for filtering the HTML blocks
     * @param filterValue The value of the filter as the criteria for filtering the HTML blocks
     */

    public GeneratePage(String contextPath, String xmlFile, String xsltFile, String filterName, String filterValue)
    {
        this.xmlFile = xmlFile;
        this.xsltFile = xsltFile;
        this.filterName = filterName;
        this.filterValue = filterValue;
        this.contextPath = contextPath;
    }

    /**
     * Generates the HTML page
     * @throws TransformerException
     * @throws TransformerConfigurationException
     * @throws FileNotFoundException
     * @throws ParserConfigurationException
     * @throws SAXException
     *
     * @return Returns the string containing the HTML page
     */

    public String generate()
            throws TransformerException, TransformerConfigurationException, FileNotFoundException,
            ParserConfigurationException, SAXException, IOException
    {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        InputStream fileXSLT = new FileInputStream(getClass().getResource(xsltFile).getFile());
        InputStream fileXML = new FileInputStream(getClass().getResource(xmlFile).getFile());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Transformer transformer = tFactory.newTransformer(new StreamSource(fileXSLT));
        transformer.setParameter(filterName, filterValue);
        transformer.setParameter("contextPath", contextPath);
        try
        {

            // Use the Transformer to apply the associated xslt tranformation to an XML document
            // and write the output to a StreamResult.
            transformer.transform(new StreamSource(fileXML), new StreamResult(output));

        }
        catch (TransformerConfigurationException tce)
        {
        }
        return output.toString().trim();
    }
}
