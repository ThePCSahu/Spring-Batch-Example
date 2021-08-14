package io.github.thepcsahu.springbatchexample.dataacess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class AccountEntity {
	@Id
	@GeneratedValue
	private Long accountNumber;
	private String accountHolder;
	private double balance;
}
