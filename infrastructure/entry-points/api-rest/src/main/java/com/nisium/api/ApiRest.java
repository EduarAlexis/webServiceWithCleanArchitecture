package com.nisium.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;

import com.nisium.api.model.request.UserRequest;
import com.nisium.api.model.response.UserResponse;
import com.nisium.model.phone.Phone;
import com.nisium.model.user.User;
import com.nisium.usecase.user.UserUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRest {

    private final UserUseCase userUseCase;

    @Autowired
    public ApiRest(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(this.userUseCase.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest user) {
        User userReturn = this.userUseCase.save(User.builder().name(user.getName())
                .email(user.getEmail()).password(user.getPassword())
                .phones(user.getPhones().stream()
                        .map(currentPhone -> Phone.builder().number(currentPhone.getNumber())
                                .countrycode(currentPhone.getCountrycode()).citycode(currentPhone.getCitycode())
                                .build())
                        .collect(Collectors.toList()))
                .build());

        return new ResponseEntity<UserResponse>(UserResponse.builder().email(userReturn.getEmail())
                .name(userReturn.getName()).password(userReturn.getPassword())
                .phones(userReturn.getPhones().stream()
                        .map(currentPhone -> Phone.builder().number(currentPhone.getNumber())
                                .citycode(currentPhone.getCitycode()).countrycode(currentPhone.getCountrycode()).build())
                        .collect(Collectors.toList()))
                .created(new Timestamp(new Date().getTime()))
                .modified(new Timestamp(new Date().getTime()))
                .lastLogin(new Timestamp(new Date().getTime()))
                .token(userReturn.getId())
                .isActive(userReturn!=null)
                .build(), HttpStatus.OK);
    }
}
