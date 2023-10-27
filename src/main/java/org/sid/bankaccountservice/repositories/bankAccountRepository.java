package org.sid.bankaccountservice.repositories;
import org.sid.bankaccountservice.entities.bankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface bankAccountRepository extends JpaRepository<bankAccount,String> {
    @RestResource(path = "/byType")
    List<bankAccount> findByType(@Param("t") AccountType type);
}
