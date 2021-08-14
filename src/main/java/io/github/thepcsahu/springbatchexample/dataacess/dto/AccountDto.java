package io.github.thepcsahu.springbatchexample.dataacess.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	private Long accountNumber;
	private String accountHolder;
	private double balance;
}