package io.github.thepcsahu.springbatchexample.dataacess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	@Query(value = "SELECT a.* FROM account_entity a LIMIT ?1", nativeQuery = true)
	List<AccountEntity> findAllAccounts(long limit);
}
