package org.project.skyflow.service;

import org.project.skyflow.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO fetchAccount(long id);
    AccountDTO updateAccount(long accountId, AccountDTO accountDTO);
}
