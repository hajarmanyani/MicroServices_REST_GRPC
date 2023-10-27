package org.sid.bankaccountservice.web;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.bankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.bankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private bankAccountRepository bnkAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    public AccountRestController(bankAccountRepository bnkAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bnkAccountRepository = bnkAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<bankAccount> bankAccounts(){
        return bnkAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public bankAccount bankAccount(@PathVariable String id){
        return bnkAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping ("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDT0){
        return accountService.addAccount(requestDT0);
    }
    @PutMapping("/bankAccounts/{id}")
    public bankAccount update(@PathVariable String id,@RequestBody bankAccount bnkAccount){
        bankAccount account=bnkAccountRepository.findById(id).orElseThrow();
        if(account.getBalance()!=null) account.setBalance(bnkAccount.getBalance());
        if(account.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(account.getCurrency()!=null) account.setCurrency(bnkAccount.getCurrency());
        if(account.getType()!=null) account.setType(bnkAccount.getType());
        return bnkAccountRepository.save(account);
    }
    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bnkAccountRepository.deleteById(id);
    }
}
