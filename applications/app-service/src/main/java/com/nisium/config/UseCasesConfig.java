package com.nisium.config;

import com.nisium.model.phone.gateways.PhoneRepository;
import com.nisium.model.user.gateways.UserRepository;
import com.nisium.usecase.phone.PhoneUseCase;
import com.nisium.usecase.user.UserUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.nisium.usecase", includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$") }, useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public UserUseCase getUserUseCase(UserRepository userRepository, PhoneUseCase phoneUseCase) {
        return new UserUseCase(userRepository, phoneUseCase);
    }

    @Bean
    public PhoneUseCase getPhoneUseCase(PhoneRepository phoneRepository) {
        return new PhoneUseCase(phoneRepository);
    }
}
