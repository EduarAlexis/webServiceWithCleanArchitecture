package com.nisium.model.exception;

import lombok.Data;

@Data
public class EmailExistsException extends RuntimeException {
    private String msg = "El correo existing ya existe";

    public EmailExistsException(String email) {
        super();
        this.msg = this.msg.replace("existing", email);
    }
}
