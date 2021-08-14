package io.github.thepcsahu.springbatchexample.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.thepcsahu.springbatchexample.dataacess.dto.AccountDto;
import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;
import io.github.thepcsahu.springbatchexample.dataacess.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountDto> getAllAccounts() {
		List<AccountEntity> accountEntites = accountRepository.findAll();
		return accountEntites.stream().map(accountEntity -> mapper.map(accountEntity, AccountDto.class))
				.collect(Collectors.toList());
	}

}
