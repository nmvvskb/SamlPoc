package com.taxilla.SamlPoc.factory;

import com.taxilla.SamlPoc.utils.SamlConstants;
import com.taxilla.SamlPoc.validation.IIdpValidation;
import com.taxilla.SamlPoc.validation.OidcIdpValidationImpl;
import com.taxilla.SamlPoc.validation.SamlIdpValidationImpl;

public class ValidationObjectFactory {

    public IIdpValidation getValidationObjectByType(String type){
        if(type==null){
            return null;
        }else if(type.equalsIgnoreCase(SamlConstants.SAML)){
            return new SamlIdpValidationImpl();
        }else if(type.equalsIgnoreCase(SamlConstants.OIDC10) || type.equalsIgnoreCase(SamlConstants.OAUTH20)){
            return  new OidcIdpValidationImpl();
        }
        return  null;
    }
}
