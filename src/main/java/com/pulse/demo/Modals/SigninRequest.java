package com.pulse.demo.Modals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SigninRequest {

    private String username;
    private String password;

    public SigninRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
