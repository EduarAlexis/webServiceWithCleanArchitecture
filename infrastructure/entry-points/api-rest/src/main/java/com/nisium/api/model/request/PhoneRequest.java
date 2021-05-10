package com.nisium.api.model.request;

import com.nisium.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    private String number;
    private String citycode;
    private String countrycode;
    private UserRequest user;
}
