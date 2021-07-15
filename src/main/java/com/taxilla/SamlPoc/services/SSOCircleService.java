package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.utils.SamlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Qualifier("ssoCircleService")
public class SSOCircleService implements IIdentityProviderService{

    @Override
    public File loadIDPMetaDataFile(String idpType) {
        File metaData = new File(SamlConstants.saml_ssocircle_metadata_file_path);
        return metaData;
    }

    @Override
    public String loadSSOEndPointURL(String idpType) {
        return SamlConstants.saml_ssocircle_idp_sso_url;
    }

    @Override
    public String prepareEndPointURL() {
        //for some time idp type is not requried
        String encodedInfo = SAMLEncoder(loadIDPMetaDataFile(""));
        return loadSSOEndPointURL("")+SamlConstants.APPENDER+encodedInfo;
    }
}
