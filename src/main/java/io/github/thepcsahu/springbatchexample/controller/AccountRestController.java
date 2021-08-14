package io.github.thepcsahu.springbatchexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.thepcsahu.springbatchexample.batch.InterestAdderBatchScheduler;
import io.github.thepcsahu.springbatchexample.dataacess.dto.AccountDto;
import io.github.thepcsahu.springbatchexample.service.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private InterestAdderBatchScheduler interestAdderBatch;

	@GetMapping
	List<AccountDto> getAllAccounts() {
		return accountService.getAllAccounts();
	}

	@GetMapping("/run-batch")
	String runBatch() {
		interestAdderBatch.executeBatch();
		return "Batch Activated";
	}
}
