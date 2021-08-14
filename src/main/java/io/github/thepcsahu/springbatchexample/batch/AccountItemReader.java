package io.github.thepcsahu.springbatchexample.batch;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;
import io.github.thepcsahu.springbatchexample.dataacess.repository.AccountRepository;
import lombok.extern.java.Log;

@Component("accountItemReader")
@Log
public class AccountItemReader implements ItemReader<AccountEntity> {

	@Autowired
	private AccountRepository respository;

	private Iterator<AccountEntity> accountsIterator;

	/*
	 * Read multiple records at once from input source
	 */
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		List<AccountEntity> list = respository.findAll();
		accountsIterator = list.iterator();
		log.fine("Read " + list.size() + " items from db");
	}

	@Override
	public AccountEntity read() {
		if (accountsIterator != null && accountsIterator.hasNext()) {
			return accountsIterator.next();
		} else {
			return null;
		}
	}
}