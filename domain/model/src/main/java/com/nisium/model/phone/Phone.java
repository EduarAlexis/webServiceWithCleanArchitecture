package com.nisium.model.phone;
import java.util.UUID;

import com.nisium.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private UUID id;
    private String number;
    private String citycode;
    private String countrycode;
    private User user;
}
