package com.nisium.usecase.user;

import java.util.List;
import java.util.stream.Collectors;

import com.nisium.model.exception.EmailExistsException;
import com.nisium.model.phone.Phone;
import com.nisium.model.user.User;
import com.nisium.model.user.gateways.UserRepository;
import com.nisium.usecase.phone.PhoneUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final PhoneUseCase phoneUseCase;

    public List<User> getAll() {
        return this.userRepository.getAll().stream().map(user -> {
            user.setPhones(this.phoneUseCase.findByUser(user));
            return user;
        }).collect(Collectors.toList());
    }

    public User save(User user) {
        User existing = this.userRepository.findByEmail(user.getEmail());

        if (existing == null) {
            List<Phone> phones = this.phoneUseCase.saveAll(user.getPhones());
            user.setPhones(phones);
            user = this.userRepository.save(user);
             user.setPhones(phones);
            return user;
        } else {
            throw new EmailExistsException(user.getEmail());
        }
    }
}
