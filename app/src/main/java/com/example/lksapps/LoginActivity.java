package com.example.lksapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lksapps.API.APIRequestData;
import com.example.lksapps.API.RetroServer;
import com.example.lksapps.Model.LoginResponse;
import com.example.lksapps.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnlogin,btndaftar;
    private EditText teusername,tepassword;
    private String username,password;

    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        teusername = findViewById(R.id.te_username);
        tepassword = findViewById(R.id.te_password);
        btnlogin = findViewById(R.id.btn_login);
        btndaftar = findViewById(R.id.btn_daftar);
        sp = getSharedPreferences("DataUser", MODE_PRIVATE);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proseslogin();
            }
        });
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    public void proseslogin(){
        username = teusername.getText().toString().trim();
        password = tepassword.getText().toString().trim();
        APIRequestData ardData = RetroServer.conRetro().create(APIRequestData.class);
        Call<LoginResponse> login = ardData.userLogin(username,password);

        if(username.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this, "Harap Isi Username Dan Password", Toast.LENGTH_SHORT).show();
            return;
        }

        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getKode() == 1) {
                        LoginResponse.User user = loginResponse.getUser();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("nama_user", user.getNama_user());
                        editor.putString("alamat", user.getAlamat());
                        editor.putString("telepon", user.getTelepon());
                        editor.putString("foto", user.getFoto());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Ada Sesuatu Yang Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal Karena"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}