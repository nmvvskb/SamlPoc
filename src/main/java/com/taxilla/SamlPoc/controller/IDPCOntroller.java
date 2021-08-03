package com.taxilla.SamlPoc.controller;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import com.taxilla.SamlPoc.entity.OICDIdentityProviderConfig;
import com.taxilla.SamlPoc.entity.SAMLIdentityProviderConfig;
import com.taxilla.SamlPoc.factory.ValidationObjectFactory;
import com.taxilla.SamlPoc.services.IIdentityProviderService;
import com.taxilla.SamlPoc.services.IdpDetailService;
import com.taxilla.SamlPoc.utils.ReactiveUtil;
import com.taxilla.SamlPoc.validation.IIdpValidation;
import com.taxilla.SamlPoc.validation.OidcIdpValidationImpl;
import com.taxilla.SamlPoc.validation.SamlIdpValidationImpl;
import com.taxilla.SamlPoc.validation.ValidateIdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/idm/identity-provider")
public class IDPCOntroller {

    @Autowired
    private IdpDetailService idpDetailService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<IdentityProviderDetailDto> create(@RequestBody IdentityProviderDetailDto entity) {
        return ReactiveUtil.processAsync(() -> {
            ValidateIdp validateIdp = null;
            ValidationObjectFactory objectFactory = null;
            IIdpValidation iIdpValidation = null;
            try {
                System.out.println("Inside of Create : " + entity.toString());
                objectFactory = new ValidationObjectFactory();
                if (entity.getType() != null) {
                    iIdpValidation = objectFactory.getValidationObjectByType(entity.getType());
                    validateIdp = new ValidateIdp(iIdpValidation);
                }

                boolean isValid = false;
                isValid = validateIdp.validate(entity);
                System.out.println("isValid :: "+isValid);
                if (isValid) {
                    System.out.println("Saving will start soon ........... !!!!!");
//                    Map<Integer,Map<String,IdentityProviderDetailDto>> data =  IIdentityProviderService.save(entity);
                    IdentityProviderDetailDto data =idpDetailService.save(entity);
                    System.out.println("Saving is Done......!" +data);
                }
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            } finally {
                validateIdp = null;
                objectFactory = null;
            }
          return entity;
        }, IdentityProviderDetailDto.class);
    }


}
