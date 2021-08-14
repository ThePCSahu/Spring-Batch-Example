package io.github.thepcsahu.springbatchexample.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;
import lombok.extern.java.Log;

@Component("accountItemProcessor")
@Log
public class AccountItemProcessor implements ItemProcessor<AccountEntity, AccountEntity> {

	@Value("${interest-adder-batch.interest-rate}")
	private int interestRate;

	@Override
	public AccountEntity process(AccountEntity account) {
		log.fine("Processing :" + account);
		double newBalance = account.getBalance() + account.getBalance() * interestRate / 100;
		account.setBalance(newBalance);
		return account;
	}
}
