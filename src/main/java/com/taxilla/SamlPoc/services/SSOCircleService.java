package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import com.taxilla.SamlPoc.utils.SamlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component
@Qualifier("ssoCircleService")
public class SSOCircleService implements IIdentityProviderService{

    @Override
    public String loadIDPMetaDataFile(IdentityProviderDetailDto idpType) {
//        File metaData = new File(SamlConstants.saml_ssocircle_metadata_file_path);
        return null;
    }

    @Override
    public String loadSSOEndPointURL(String idpType) {
        return SamlConstants.saml_ssocircle_idp_sso_url;
    }


    @Override
    public String getMetaDataInfo(String providedIdp) {
        return null;
    }
}
