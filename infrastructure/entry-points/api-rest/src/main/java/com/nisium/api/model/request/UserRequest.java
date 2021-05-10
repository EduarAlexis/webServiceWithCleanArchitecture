package com.nisium.api.model.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message="El nombre es requerido")
    private String name;
    @NotNull(message="El email es requerido")
    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message="Email incorrecto")
    private String email;
    @NotNull(message="El password es requerido")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d\\d)[a-zA-Z\\d]{6,}$", message="Password incorrecto")
    private String password;
    private List<PhoneRequest> phones;
}
