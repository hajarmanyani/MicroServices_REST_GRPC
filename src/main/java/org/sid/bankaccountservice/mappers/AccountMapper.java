package org.sid.bankaccountservice.mappers;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.bankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(bankAccount bnkAccount){
        BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(bnkAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
