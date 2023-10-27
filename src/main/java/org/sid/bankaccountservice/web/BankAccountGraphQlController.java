package org.sid.bankaccountservice.web;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.entities.bankAccount;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.sid.bankaccountservice.repositories.bankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class BankAccountGraphQlController {
    @Autowired
    private bankAccountRepository bnkAccountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;

    public BankAccountGraphQlController(AccountService accountService) {
        this.accountService = accountService;
    }

    @QueryMapping
    public List<bankAccount> accountsList(){
        return bnkAccountRepository.findAll();
    }
    @QueryMapping
    public bankAccount bankAccountById(@Argument String id){
        return bnkAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bnkAccount){
        return accountService.addAccount(bnkAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bnkAccount){
        return accountService.updateAccount(id, bnkAccount);
    }
    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        bnkAccountRepository.deleteById(id);
        return true;
    }
    @QueryMapping
    public List<Customer> costumers(){
        return customerRepository.findAll();
    }
}
