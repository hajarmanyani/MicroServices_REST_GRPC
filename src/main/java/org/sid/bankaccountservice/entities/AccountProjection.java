package org.sid.bankaccountservice.entities;
import org.sid.bankaccountservice.enums.AccountType;
@org.springframework.data.rest.core.config.Projection(types = bankAccount.class,name="p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();

}
