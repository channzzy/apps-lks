package com.example.lksapps.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    private String pesan;
    private Boolean status;

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPesan() {
        return pesan;
    }
}
