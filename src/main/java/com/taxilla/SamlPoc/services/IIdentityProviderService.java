package com.taxilla.SamlPoc.services;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;

@Service
@Qualifier("iIdentityProviderService")
public interface IIdentityProviderService {

    File loadIDPMetaDataFile(String idpType);

    String loadSSOEndPointURL(String idpType);

    String prepareEndPointURL();


    /**
     * This method will encode the given metaData FIle
     *
     * @param metaDataFile
     * @return
     */
    public default String SAMLEncoder(File metaDataFile) {
        System.out.println("Inside of SAMLEncoder In : " + this.getClass().getName());
        String encodedInfo = null;
        try {
            byte[] metaContent = FileUtils.readFileToByteArray(metaDataFile);
            Base64 base64 = new Base64();
            byte[] base64EncodeByteArray = base64.encode(metaContent);
            encodedInfo = new String(base64EncodeByteArray);
        } catch (Exception e) {
            System.out.println("Exception in " + this.getClass().getName() + "-SAMLEncoder() : " + e.getMessage());
            e.printStackTrace();
        }
        return encodedInfo;
    }

    /**
     * This method decode the Given encoded string information
     *
     * @param samlInfo
     * @return
     */
    public static String SAMLDecoder(String samlInfo) {
        System.out.println("Inside of SAMLDecoder In : ");
        String decodedInfo = null;
        try {
            Base64 base64 = new Base64();
            byte[] decodedBytes = base64.decode(samlInfo);
            decodedInfo = new String(decodedBytes);
        } catch (Exception e) {
            System.out.println("Exception in -SAMLDecoder() : " + e.getMessage());
            e.printStackTrace();
        }
        return decodedInfo;
    }

    /**
     * This method used for convertion of String to XML Document.
     *
     * @param xmlString
     * @return
     */
    public static Document convertStringToXMLDocument(String xmlString) {
        System.out.println("Inside of convertStringToXMLDocument In : ");
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            System.out.println("Exception in -convertStringToXMLDocument() : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getRootTag(String rootTag){
        System.out.println("Inside of getRootTag In : ");
        rootTag = rootTag.replace("p:Response","");
        System.out.println("root Tag in getRootTag : "+rootTag);
        return rootTag;
    }
}
