package com.nisium.jpa.phone;

import java.util.List;

import com.nisium.jpa.user.UserData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PhoneRepository extends CrudRepository<PhoneData, Long>, QueryByExampleExecutor<PhoneData> {
    List<PhoneData> findByUser(UserData user);
}
