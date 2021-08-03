package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;

public interface IIdpDetailService {

    IdentityProviderDetailDto save(IdentityProviderDetailDto detailDto);
}
