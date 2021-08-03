package com.taxilla.SamlPoc.validation;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import com.taxilla.SamlPoc.entity.OICDIdentityProviderConfig;
import com.taxilla.SamlPoc.utils.AppExceptions;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

import java.net.URL;

public class OidcIdpValidationImpl implements IIdpValidation{

    private static final String name_pattern = "^[0-9A-Za-z _-]+{1,40}$";
    private static final String id_pattern = "^[0-9A-Za-z_-]+{1,40}$";



    @Override
    public boolean validateIdp(IdentityProviderDetailDto identityProvider) {
        System.out.println("Inside validation OIDC");
        boolean isValid=true;
        if (StringUtils.isBlank(identityProvider.getName())) {
//            isValid=false;
            throw AppExceptions.badRequest("Identity Provider Name cannot be empty");
        }
        if(identityProvider.getName() != null) {
            identityProvider.setName(identityProvider.getName().trim());
        }
//        if(identityProvider.getId() != null) {
////            isValid=false;
//            identityProvider.setId(identityProvider.getId().trim());
//        }

        RegexValidator nameValidator = new RegexValidator(new String[] {name_pattern}, false);
        RegexValidator idValidator = new RegexValidator(new String[] {id_pattern}, false);

        if (StringUtils.isBlank(identityProvider.getType())) {
            isValid=false;
            throw AppExceptions.badRequest("Type cannot be empty");
        }
        identityProvider.setType(identityProvider.getType().trim());

        if (StringUtils.isBlank(identityProvider.getOriginKey())) {
            isValid=false;
            throw AppExceptions.badRequest("Origin Key cannot be empty");
        }

        OICDIdentityProviderConfig config = (OICDIdentityProviderConfig)identityProvider.getConfig();
        if (StringUtils.isBlank(config.getAuthUrl())) {
            isValid=false;
            throw AppExceptions.badRequest("Authorization URL cannot be empty");
        }
        else if (!IIdpValidation.isValidUrl(config.getAuthUrl())){
            isValid=false;
            throw AppExceptions.badRequest("Authorization URL is not a valid URL");
        }

        if (StringUtils.isBlank(config.getTokenUrl())) {
            isValid=false;
            throw AppExceptions.badRequest("Token URL cannot be empty");
        }
        else if (!IIdpValidation.isValidUrl(config.getTokenUrl())){
            isValid=false;
            throw AppExceptions.badRequest("Token URL is not a valid URL");
        }

        if (StringUtils.isBlank(config.getUserInfoUrl())) {
            isValid=false;
            throw AppExceptions.badRequest("User Info URL cannot be empty");
        }
        else if (!IIdpValidation.isValidUrl(config.getUserInfoUrl())){
            isValid=false;
            throw AppExceptions.badRequest("User Info URL is not a valid URL");
        }

        if(!StringUtils.isBlank(config.getTokenKeyUrl())){
            if (!IIdpValidation.isValidUrl(config.getTokenKeyUrl())){
                isValid=false;
                throw AppExceptions.badRequest("Token Key URL is not a valid URL");
            }
        }
        if (StringUtils.isBlank(config.getRelyingPartyId())) {
            isValid=false;
            throw AppExceptions.badRequest("Relying Party Id cannot be empty");
        }

        if (identityProvider.getName().length() > 255) {
            isValid=false;
            throw AppExceptions.badRequest("Name should be more than 0 and less than or equal to 255");
        }
        if (identityProvider.getOriginKey().length() > 255) {
            isValid=false;
            throw AppExceptions.badRequest("Origin Key should be more than 0 and less than or equal to 255");
        }
        if (identityProvider.getType().length() > 255) {
            isValid=false;
            throw AppExceptions.badRequest("Name should be more than 0 and less than or equal to 255");
        }
        return isValid;
    }
}
