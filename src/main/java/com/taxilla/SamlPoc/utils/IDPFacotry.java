package com.taxilla.SamlPoc.utils;

import com.taxilla.SamlPoc.services.Auth0Service;
import com.taxilla.SamlPoc.services.IIdentityProviderService;
import com.taxilla.SamlPoc.services.OktaService;
import com.taxilla.SamlPoc.services.SSOCircleService;
import com.taxilla.SamlPoc.utils.IdenityProviderList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;


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
    }

    /**
     * This method will provide the selected idp endipoint URL
     * @return
     */
    public String getEndoPointURL(){
        return  this.iIdentityProviderService.prepareEndPointURL();
    }

    public String SAMLDecoder(String samlInfo){
        return this.iIdentityProviderService.SAMLDecoder(samlInfo);
    }

    public Document convertStringToXMLDocument(String decodeInfo){
        return  this.iIdentityProviderService.convertStringToXMLDocument(decodeInfo);
    }

    public String getRootTag(String nodeName) {
        return this.iIdentityProviderService.getRootTag(nodeName);
    }
}
