package com.taxilla.SamlPoc.services;

import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdpDetailsRepository extends JpaRepository<IdentityProviderDetail, Long> {

}
