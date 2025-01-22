package org.project.skyflow.controller;

import lombok.RequiredArgsConstructor;
import org.project.skyflow.dto.AccountDTO;
import org.project.skyflow.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Validated(AccountDTO.AccountCreate.class) AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> viewAccount(@PathVariable long accountId) {
        return ResponseEntity.ok(accountService.fetchAccount(accountId));
    }
}
