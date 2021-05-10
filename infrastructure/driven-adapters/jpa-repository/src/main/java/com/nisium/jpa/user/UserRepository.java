package com.nisium.jpa.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends CrudRepository<UserData, Long>, QueryByExampleExecutor<UserData> {
    UserData findByEmail(String email);
}
