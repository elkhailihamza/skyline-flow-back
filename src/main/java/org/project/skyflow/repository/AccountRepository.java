package org.project.skyflow.repository;

import org.project.skyflow.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Account a WHERE a.user.email = :email")
    boolean existsAccountByUser_Email(@Param("email") String email);
    boolean existsAccountByUsername(String username);
}
