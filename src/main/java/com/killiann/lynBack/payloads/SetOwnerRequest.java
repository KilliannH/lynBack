package com.killiann.lynBack.payloads;

public class SetOwnerRequest {
    private Long owner;

    public SetOwnerRequest() {}

    public SetOwnerRequest(Long owner) {
        this.owner = owner;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
