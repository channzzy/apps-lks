package com.example.lksapps.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private int kode;
    @SerializedName("data")
    private User user;

    public int getKode() {
        return kode;
    }

    public User getUser() {
        return user;
    }

    public class User {
        private String nama_user;
        private String alamat;
        private String telepon;

        public String getFoto() {
            return foto;
        }

        private String foto;

        public String getNama_user() {
            return nama_user;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getTelepon() {
            return telepon;
        }
    }
}

