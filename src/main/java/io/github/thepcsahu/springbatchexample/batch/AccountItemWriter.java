package io.github.thepcsahu.springbatchexample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;
import io.github.thepcsahu.springbatchexample.dataacess.repository.AccountRepository;
import lombok.extern.java.Log;

@Component("accountItemWriter")
@Log
public class AccountItemWriter implements ItemWriter<AccountEntity> {

	@Autowired
	private AccountRepository respository;

	@Override
	public void write(List<? extends AccountEntity> list) throws Exception {
		respository.saveAll(list);
		log.fine("Saved " + list.size() + " items in db");
	}
}