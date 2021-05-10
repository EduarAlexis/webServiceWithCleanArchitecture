package com.nisium.api.model.response;

import com.nisium.model.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp lastLogin;
    private UUID token;
    private boolean isActive;
}
