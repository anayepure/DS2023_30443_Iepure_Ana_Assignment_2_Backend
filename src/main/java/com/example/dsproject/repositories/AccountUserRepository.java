package com.example.dsproject.repositories;

import com.example.dsproject.entities.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Integer> {

    Optional<AccountUser> findByUsername(String username);

    Optional<AccountUser> findByUserId(Long id);

    void deleteByUserId(Long id);

}
