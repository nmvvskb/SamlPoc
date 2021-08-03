package com.taxilla.SamlPoc.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import com.taxilla.SamlPoc.entity.SAMLIdentityProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import io.micrometer.core.instrument.util.StringUtils;

public class SamlIdpValidationImpl implements  IIdpValidation{

    @Override
    public boolean validateIdp(IdentityProviderDetailDto providerDetail) {
        System.out.println("Inside validation SAML");
        SAMLIdentityProviderConfig samlConfig = (SAMLIdentityProviderConfig)providerDetail.getConfig();
        String trimmedLocation = samlConfig.getMetaDataLocation().trim();
        if (trimmedLocation.startsWith("<?xml") ||
                trimmedLocation.startsWith("<md:EntityDescriptor") ||
                trimmedLocation.startsWith("<EntityDescriptor")) {
            if(validateXml(trimmedLocation)) {
                return true;
            }
        }else if(trimmedLocation.startsWith("http")){
            if(!StringUtils.isBlank(trimmedLocation) && IIdpValidation.isValidUrl(trimmedLocation)){
                try{
                    System.out.println("URL "+ trimmedLocation);
                    ResponseEntity<String> xmlData =  new RestTemplate().exchange(trimmedLocation, HttpMethod.GET,
                            null,
                            String.class);
                    String metaData = xmlData.getBody();
                    if(validateXml(metaData)) {
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private boolean validateXml(String xml) {
        if (xml==null || xml.toUpperCase().contains("<!DOCTYPE")) {
            return false;
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setExpandEntityReferences(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return false;
        }

        return true;
    }


}
