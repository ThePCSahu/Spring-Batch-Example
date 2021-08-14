package io.github.thepcsahu.springbatchexample.service;

import java.util.List;

import io.github.thepcsahu.springbatchexample.dataacess.dto.AccountDto;
import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;

public interface AccountService {
	public List<AccountDto> getAllAccounts();
}
