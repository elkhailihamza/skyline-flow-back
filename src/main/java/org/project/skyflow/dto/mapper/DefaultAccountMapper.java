package org.project.skyflow.dto.mapper;

import org.project.skyflow.domain.entity.Account;
import org.project.skyflow.dto.AccountDTO;

public interface DefaultAccountMapper {
    AccountDTO toAccountDTO(Account account);
    Account toAccount(AccountDTO accountDTO);
}
