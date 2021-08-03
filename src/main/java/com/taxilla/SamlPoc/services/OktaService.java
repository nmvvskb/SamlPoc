package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import com.taxilla.SamlPoc.entity.SAMLIdentityProviderConfig;
import com.taxilla.SamlPoc.factory.ValidationObjectFactory;
import com.taxilla.SamlPoc.utils.CustomeException;
import com.taxilla.SamlPoc.utils.SamlConstants;
import com.taxilla.SamlPoc.validation.IIdpValidation;
import com.taxilla.SamlPoc.validation.ValidateIdp;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Locale;
import java.util.Map;

@Component
@Qualifier("oktaService")
public class OktaService implements IIdentityProviderService{

    @Override
    public String loadIDPMetaDataFile(IdentityProviderDetailDto providerDetail) {
//        File metaData = new File(SamlConstants.saml_okta_metadata_file_path);
        String metaData = null;
        String metaDataInfo = providerDetail.getConfig().toString();
        SAMLIdentityProviderConfig samlConfig = (SAMLIdentityProviderConfig)providerDetail.getConfig();
        String trimmedLocation = samlConfig.getMetaDataLocation().trim();
        if (trimmedLocation.startsWith("<?xml") ||
                trimmedLocation.startsWith("<md:EntityDescriptor") ||
                trimmedLocation.startsWith("<EntityDescriptor")) {
            metaData=trimmedLocation;
        }else if(trimmedLocation.startsWith("http")){
            if(!StringUtils.isBlank(trimmedLocation) && IIdpValidation.isValidUrl(trimmedLocation)){
                try{
                    System.out.println("URL "+ trimmedLocation);
                    ResponseEntity<String> xmlData =  new RestTemplate().exchange(trimmedLocation, HttpMethod.GET,
                            null,
                            String.class);
                    String urlMetaData = xmlData.getBody();
                    metaData=urlMetaData;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Okta Service loadIDPMetaDataFile :: " + metaData);
        String encodedInfo = SAMLEncoder(metaData);
        return loadSSOEndPointURL("")+SamlConstants.APPENDER+encodedInfo;
    }

    @Override
    public String loadSSOEndPointURL(String idpType) {
        return SamlConstants.saml_okta_idp_sso_url;
    }


    @Override
    public String getMetaDataInfo(String providedIdp) {
        String endPointUrl = null;
        boolean isRecordMatch=false;
        for (var letterEntry : IIdentityProviderService.dataMap.entrySet()) {
            int letter = letterEntry.getKey();
            for (var nameEntry : letterEntry.getValue().entrySet()) {
                String name = nameEntry.getKey();
                if(providedIdp.toLowerCase().trim().equalsIgnoreCase(name.toLowerCase())){
                    IdentityProviderDetailDto providerDetail = nameEntry.getValue();
                    endPointUrl= loadIDPMetaDataFile(providerDetail);
                    isRecordMatch=true;
                    break;
                }
            }
        }
        if(!isRecordMatch){
            throw new CustomeException("given Identity Provider does not exist. Please Provide valid one");
        }
        return  endPointUrl;
    }

}
