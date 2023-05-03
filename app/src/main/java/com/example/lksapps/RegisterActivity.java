package com.example.lksapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lksapps.API.APIRequestData;
import com.example.lksapps.API.RetroServer;
import com.example.lksapps.Model.RegisterResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class RegisterActivity extends AppCompatActivity {
    private EditText tenama,teusername,tealamat,tetelepon,tepassword,tekonfrim;
    private Button btndaftar,btnback,btnpilih;
    private String nama,username,telepon,alamat,password,konfirm,path;
    private Bitmap bitmap;
    private ImageView foto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Context ctx = RegisterActivity.this;
        path = RealPathUtil.getRealPath(ctx, uri);
        bitmap = BitmapFactory.decodeFile(path);
        foto.setImageURI(uri);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tenama = findViewById(R.id.te_nama);
        teusername = findViewById(R.id.te_username);
        tealamat = findViewById(R.id.te_alamat);
        tetelepon = findViewById(R.id.te_telepon);
        tepassword = findViewById(R.id.te_password);
        tekonfrim = findViewById(R.id.te_konfirmasi);
        foto = findViewById(R.id.foto_user);
        btndaftar = findViewById(R.id.btn_daftar);
        btnback = findViewById(R.id.btn_back);
        btnpilih = findViewById(R.id.btn_pilih);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnpilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent();
                    intent.setType("foto/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 10);
                }else{
                    ActivityCompat.requestPermissions(RegisterActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        });
    }
    public void register() {
        nama = tenama.getText().toString().trim();
        username = teusername.getText().toString().trim();
        alamat = tealamat.getText().toString().trim();
        telepon = tetelepon.getText().toString().trim();
        password = tepassword.getText().toString().trim();
        konfirm = tekonfrim.getText().toString().trim();

        if (nama.equals("") || username.equals("") || alamat.equals("") || telepon.equals("") || password.equals("") || konfirm.equals("")) {
            Toast.makeText(RegisterActivity.this, "Harap Mengisi Semua Filed", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(konfirm)) {
            Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
            return;
        }

        if(bitmap == null){
            Toast.makeText(RegisterActivity.this, "Harap Memilih Foto", Toast.LENGTH_SHORT).show();
            return;
        }
        MultipartBody.Part fotoPart = null;
        if (bitmap != null) {
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            fotoPart = MultipartBody.Part.createFormData("foto", file.getName(), requestBody);
        }

        RequestBody namaUserReq = RequestBody.create(MediaType.parse("multipart/form-data"), nama);
        RequestBody usernameReq = RequestBody.create(MediaType.parse("multipart/form-data"), username);
        RequestBody alamatReq = RequestBody.create(MediaType.parse("multipart/form-data"), alamat);
        RequestBody teleponReq = RequestBody.create(MediaType.parse("multipart/form-data"), telepon);
        RequestBody passwordReq = RequestBody.create(MediaType.parse("multipart/form-data"), password);

        // Membuat instance dari APIRequestData dengan menggunakan RetroServer
        APIRequestData ardData = RetroServer.conRetro().create(APIRequestData.class);

        // Melakukan request ke API untuk mendaftarkan pengguna
        Call<RegisterResponse> register = ardData.userRegistrasi(fotoPart, namaUserReq, usernameReq, alamatReq, teleponReq, passwordReq);
        register.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getPesan();
                    if(pesan.equals("Username Sudah Digunakan")){
                        Toast.makeText(RegisterActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                        return;
                    }else if (pesan.equals("Data Berhasil Disimpan")) {
                        Toast.makeText(RegisterActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Gagal Mendaftar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Ada Sesuatu Yang Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Mendaftar: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}