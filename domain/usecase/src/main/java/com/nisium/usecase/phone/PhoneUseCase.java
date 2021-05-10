package com.nisium.usecase.phone;

import java.util.List;

import com.nisium.model.phone.Phone;
import com.nisium.model.phone.gateways.PhoneRepository;
import com.nisium.model.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PhoneUseCase {

    private final PhoneRepository phoneRepository;

    public List<Phone> findByUser(User user) {
        return this.phoneRepository.findByUser(user);
    }

    public List<Phone> saveAll(List<Phone> phoneList) {
        return this.phoneRepository.saveAll(phoneList);
    }
}
