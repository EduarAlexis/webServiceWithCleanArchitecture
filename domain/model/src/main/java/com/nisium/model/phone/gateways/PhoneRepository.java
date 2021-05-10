package com.nisium.model.phone.gateways;

import java.util.List;

import com.nisium.model.phone.Phone;
import com.nisium.model.user.User;

public interface PhoneRepository {

    List<Phone> findByUser(User user); 

    List<Phone> saveAll(List<Phone> phoneList);
}
