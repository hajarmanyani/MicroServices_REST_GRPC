package org.sid.bankaccountservice.service;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.bankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.bankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service @Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private bankAccountRepository bnkAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        bankAccount bnkAccount= bankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(Double.valueOf(bankAccountDTO.getBalance()))
                .currency(bankAccountDTO.getCurrency())
                .type(bankAccountDTO.getType())
                .build();
        bankAccount savedBankAccount = bnkAccountRepository.save(bnkAccount);
        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String Id, BankAccountRequestDTO bankAccountDTO) {
        bankAccount bnkAccount= bankAccount.builder()
                .id(Id)
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .currency(bankAccountDTO.getCurrency())
                .type(bankAccountDTO.getType())
                .build();
        bankAccount savedBankAccount = bnkAccountRepository.save(bnkAccount);
        BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
}
