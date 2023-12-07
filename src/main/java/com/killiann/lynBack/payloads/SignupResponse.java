package com.killiann.lynBack.payloads;

import java.io.Serializable;
import java.util.List;

public class SignupResponse implements Serializable {

    public String token;
    public Long id;


    public SignupResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}