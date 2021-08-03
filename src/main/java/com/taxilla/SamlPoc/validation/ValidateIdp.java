package com.taxilla.SamlPoc.validation;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;

public class ValidateIdp {

    private IIdpValidation iIdpValidation;

    public ValidateIdp(IIdpValidation iIdpValidation){
        this.iIdpValidation=iIdpValidation;
    }

    public boolean validate(IdentityProviderDetailDto providerDetail){
        return this.iIdpValidation.validateIdp(providerDetail);
    }
}
