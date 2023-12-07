package com.killiann.lynBack.payloads;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    public String token;
    public Long id;

    public LoginResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}