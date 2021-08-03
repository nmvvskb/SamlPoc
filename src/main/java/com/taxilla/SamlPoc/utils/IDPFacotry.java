package com.taxilla.SamlPoc.utils;

import com.taxilla.SamlPoc.componentVo.IdpTypeSelection;
import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.services.Auth0Service;
import com.taxilla.SamlPoc.services.IIdentityProviderService;
import com.taxilla.SamlPoc.services.OktaService;
import com.taxilla.SamlPoc.services.SSOCircleService;
import com.taxilla.SamlPoc.utils.IdenityProviderList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.util.Map;


public class IDPFacotry {

    @Autowired
    private IIdentityProviderService iIdentityProviderService;

    public IDPFacotry(){

    }
    /**
     * this idpType parameterized constructor will give respective instance of IDP
     * @param idpType
     */
    public IDPFacotry(String idpType) {
        if(idpType.equalsIgnoreCase(IdenityProviderList.OKTA.name())){
            this.iIdentityProviderService = new OktaService();
        }else if(idpType.equalsIgnoreCase(IdenityProviderList.AUTH0.name())){
            this.iIdentityProviderService = new Auth0Service();
        }else if(idpType.equalsIgnoreCase(IdenityProviderList.SSOCircle.name())){
            this.iIdentityProviderService = new SSOCircleService();
        }

        if(idpType.equalsIgnoreCase(IdpSelectionList.SAML.name())){
            this.iIdentityProviderService = new OktaService();
        }else if(idpType.equalsIgnoreCase(IdpSelectionList.OIDC.name()) || idpType.equalsIgnoreCase(IdpSelectionList.OAUTH.name())){
            this.iIdentityProviderService = new Auth0Service();
        }
    }

    /**
     * This method will provide the selected idp endipoint URL
     * @return
     */
//    public String getEndoPointURL(){
//        return  this.iIdentityProviderService.prepareEndPointURL();
//    }

    public String getMetaDataInfo(String providedIdp) {
        return this.iIdentityProviderService.getMetaDataInfo(providedIdp);
    }
}
