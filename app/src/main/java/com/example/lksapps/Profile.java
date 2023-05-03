package com.example.lksapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private TextView textViewName, textViewAddress, textViewPhone;
    private Button btnlogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewName = findViewById(R.id.tv_nama);
        textViewAddress = findViewById(R.id.tv_alamat);
        textViewPhone = findViewById(R.id.tv_telepon);
        btnlogout = findViewById(R.id.btn_logout);
        sharedPreferences = getSharedPreferences("DataUser", MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");
        String address = sharedPreferences.getString("address", "");
        String phone = sharedPreferences.getString("phone", "");

        textViewName.setText(name);
        textViewAddress.setText(address);
        textViewPhone.setText(phone);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().apply();
                System.exit(0);
            }
        });
    }
}