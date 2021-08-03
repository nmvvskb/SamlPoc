package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.entity.IdentityProviderDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service("idpDetailService")
public class IdpDetailService implements  IIdpDetailService{

    @Autowired
    private IdpDetailsRepository idpDetailsRepository;


    @Override
    public IdentityProviderDetailDto save(IdentityProviderDetailDto detailDto) {
        IdentityProviderDetail providerDetail = new IdentityProviderDetail();
        providerDetail.setConfig(detailDto.getConfig().toString());
        providerDetail.setType(detailDto.getType());
        providerDetail.setActive(detailDto.getActive());
        providerDetail.setName(detailDto.getName());
        providerDetail.setIdentityZoneId(detailDto.getIdentityZoneId());
        providerDetail.setOrganizationId(detailDto.getOrganizationId());
        providerDetail.setOriginKey(detailDto.getOriginKey());
        providerDetail.setCreatedDate(new Date());
        providerDetail.setCreatedByUserId("1");
        providerDetail.setLastModifiedDate(new Date());
        providerDetail.setLastModifiedByUserId("1");
        providerDetail = idpDetailsRepository.save(providerDetail);
        System.out.println("IN IdpDetailService : Save() "+ providerDetail.toString());
        detailDto.setId(String.valueOf(providerDetail.getId()));
        return detailDto;
    }

}
